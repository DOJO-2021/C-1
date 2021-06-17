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
 * Servlet implementation class ViewMenuServlet
 */
@WebServlet("/ViewMenuServlet")
public class ViewMenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("user_id") == null ) {
			response.sendRedirect("/TeraChannel/LoginServlet");
			return;
		}

		//見出しページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ViewMenu.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("user_id") == null ) {
			response.sendRedirect("/TeraChannel/LoginServlet");
			return;
		}

		//topicリストを取得する
		//BoardfDAO dao = new BoardfDAO

		//クリックされたURLの投稿IDを元に該当の行のデータを全てリクエストスコープに格納する


		//詳細ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ TeraChannel/VIewBoardServlet");
		dispatcher.forward(request, response);
	}

}
