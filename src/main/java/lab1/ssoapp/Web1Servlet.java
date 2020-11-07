package lab1.ssoapp;

import net.minidev.json.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet(urlPatterns="/ssoapp/webapp1",loadOnStartup=1)
public class Web1Servlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("web1 post");
		RequestDispatcher rd=request.getRequestDispatcher("/web1.jsp");
		response.setContentType("text/html;charset=UTF-8;");
		rd.forward(request, response);
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("web1 get");

		request.setAttribute("success", true);
		request.setAttribute("msg", "web1登录成功");
		RequestDispatcher rd=request.getRequestDispatcher("/web1.jsp");
		response.setContentType("text/html;charset=UTF-8;");
		rd.forward(request, response);
	}
	
	public void output(String jsonStr,HttpServletResponse response) throws IOException{
		response.setContentType("text/html;charset=UTF-8;");
		PrintWriter out = response.getWriter();
		out.println(jsonStr);
		out.flush();
		out.close();
		
	}
	
}
