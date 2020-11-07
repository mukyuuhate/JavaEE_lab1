package lab1.filter;

import com.nimbusds.jose.shaded.json.JSONObject;
import lab1.jwt.JWTToken;
import lab1.jwt.TokenState;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.Map;

/**
 * toekn校验过滤器，所有的API接口请求都要经过该过滤器(除了登陆接口)
 * @author running@vip.163.com
 *
 */
@WebFilter(urlPatterns="/servlet/*")
public class Filter1_CheckToken  implements Filter {



	@Override
	public void doFilter(ServletRequest argo, ServletResponse arg1,
			FilterChain chain ) throws IOException, ServletException {

		HttpServletRequest request=(HttpServletRequest) argo;
		HttpServletResponse response=(HttpServletResponse) arg1;
		System.out.println("filter1 "+request.getRequestURI());
//		response.setHeader("Access-Control-Allow-Origin", "*");
		if(request.getRequestURI().endsWith("/servlet/login")||request.getRequestURI().endsWith("jquery-2.1.0.js")){
			//登陆接口不校验token，直接放行
			chain.doFilter(request, response);
			System.out.println("filter1 login page "+request.getRequestURI());
			return;
		}
		//其他API接口一律校验token
		System.out.println("开始校验token");
		//从请求头中获取token
		String token="";
		Cookie[] cookies=request.getCookies();
		for(Cookie cookie:cookies ){
			if(cookie.getName().equals("token")){
				token= cookie.getValue();
			}
		}
//		if(token==null){
//			//请求携带token
//			String urlPath = request.getScheme() //当前链接使用的协议
//					+"://" + request.getServerName()//服务器地址
//					+ ":" + request.getServerPort() //端口号
//					+ request.getContextPath() //应用名称，如果应用名称为
//					+ request.getServletPath(); //请求的相对url
//			String cookie = "token";
//			URL url = new URL(urlPath);
//			URLConnection conn = url.openConnection();
//			conn.setRequestProperty("Cookie", cookie);
//			conn.setDoInput(true);
//			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//			StringBuilder sb = new StringBuilder();
//			String line = null;
//			while ((line = br.readLine()) != null) {
//				sb.append(line);
//			}
//			System.out.println("请求响应结果："+sb);
//			token=sb.toString();
//		}
		Map<String, Object> resultMap=JWTToken.validToken(token);
		TokenState state=TokenState.getTokenState((String)resultMap.get("state"));
		//测试

		switch (state) {
		case VALID:
			JSONObject idAndTime= (JSONObject) resultMap.get("data");
			System.out.println(idAndTime.toJSONString());
			System.out.println(idAndTime.get("uid"));
			System.out.println(idAndTime.get("iat"));
			Date date=new Date();
			long timeout=Long.parseLong(idAndTime.get("ext").toString())-date.getTime();
			String timeoutString=timeout/(60*1000)+"分"+((timeout/1000)%60)+"秒";
			//取出payload中数据,放入到request作用域中
			request.setAttribute("userNameFromToken", idAndTime.get("uid"));
			request.setAttribute("timeRemainForToken", timeoutString);
			//放行
			chain.doFilter(request, response);
			break;
		case EXPIRED:
		case INVALID:
			System.out.println("无效token");
			//token过期或者无效，则输出错误信息返回给ajax
			JSONObject outputMSg=new JSONObject();
			outputMSg.put("success", false);
			outputMSg.put("msg", "您的token不合法或者过期了，请重新登陆");
			output(outputMSg.toJSONString(), response);
			break;
		}
	}


	public void output(String jsonStr,HttpServletResponse response) throws IOException{
		response.setContentType("text/html;charset=UTF-8;");
		PrintWriter out = response.getWriter();
//		out.println();
		out.write(jsonStr);
		out.flush();
		out.close();

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("token过滤器初始化了");
	}

	@Override
	public void destroy() {
		
	}

}
