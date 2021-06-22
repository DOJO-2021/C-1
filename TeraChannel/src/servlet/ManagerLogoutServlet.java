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
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/ManagerLogoutServlet")
public class ManagerLogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// セッションスコープを破棄する
		HttpSession session = request.getSession();
		session.invalidate();

		// ログインページにリダイレクトする
		//三田のコード
		request.setAttribute("taikinMessage", "今日も一日お疲れ様でした。");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ManagerLogin.jsp");
		dispatcher.forward(request, response);

		//高橋さんのコード
		//response.sendRedirect("/TeraChannel/ManagerLoginServlet");
	}

}
