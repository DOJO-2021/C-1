package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ManagerMenuServlet
 */
@WebServlet("/ManagerMenuServlet")
public class ManagerMenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//
//

	HttpSession session = request.getSession();
	/*if (session.getAttribute("manager_mail") == null) {
		response.sendRedirect("/Terachannel/src/ManagerLoginServlet");
		return;
	}
*/
	// メニューページにフォワードする
	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ManagerMenu.jsp");
	dispatcher.forward(request, response);

}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		/*if (session.getAttribute("manager_mail") == null) {
			response.sendRedirect("/Terachannel/src/ManagerLoginServlet");
			return;
		}*/
		doGet(request, response);
	}

}
