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
@WebFilter(urlPatterns="/ssoapp/*")
public class Filter1_CheckToken  implements Filter {


	@Override
	public void doFilter(ServletRequest argo, ServletResponse arg1,
			FilterChain chain ) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest) argo;
		HttpServletResponse response=(HttpServletResponse) arg1;
		System.out.println("token校验过滤器，地址："+request.getRequestURI());
		if(request.getRequestURI().endsWith("/ssoapp/login")||request.getRequestURI().endsWith("jquery-2.1.0.js")){
			//登陆接口或请求文件不校验token，直接放行
			chain.doFilter(request, response);
			return;
		}
		System.out.println("开始校验token");
		//从cookie中获取token
		String token="";
		Cookie[] cookies=request.getCookies();
		for(Cookie cookie:cookies ){
			if(cookie.getName().equals("token")){
				token= cookie.getValue();
			}
		}
		Map<String, Object> resultMap=JWTToken.validToken(token);
		TokenState state=TokenState.getTokenState((String)resultMap.get("state"));
		switch (state) {
		case VALID:
			JSONObject idAndTime= (JSONObject) resultMap.get("data");
			System.out.println("token有效，从token中读取数据添加在request属性");
			Date date=new Date();
			long timeout=Long.parseLong(idAndTime.get("ext").toString())-date.getTime();
			String timeoutString=timeout/(60*1000)+"分"+((timeout/1000)%60)+"秒";
			request.setAttribute("userNameFromToken", idAndTime.get("uid"));
			request.setAttribute("timeRemainForToken", timeoutString);
			//放行
			chain.doFilter(request, response);
			break;
		case EXPIRED:
			request.setAttribute("success", false);
			request.setAttribute("msg", "来自"+request.getRequestURI()+"，token超时，请先登录");
			request.setAttribute("preurl", request.getRequestURI());
			RequestDispatcher rd1=request.getRequestDispatcher("/ssoapp/login");
			response.setContentType("text/html;charset=UTF-8;");
			rd1.forward(request, response);
			break;
		case INVALID:
			request.setAttribute("success", false);
			request.setAttribute("msg", "来自"+request.getRequestURI()+"，token无效，请先登录");
			request.setAttribute("preurl", request.getRequestURI());
			RequestDispatcher rd2=request.getRequestDispatcher("/ssoapp/login");
			response.setContentType("text/html;charset=UTF-8;");
			rd2.forward(request, response);
			break;
		}
	}


	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("token过滤器初始化了");
	}

	@Override
	public void destroy() {
		
	}

}
