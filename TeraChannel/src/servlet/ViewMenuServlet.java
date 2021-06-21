package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Fukada.BoardfDAO;
import model.Board;

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
		/* if (session.getAttribute("user_id") == null ) {
			response.sendRedirect("/TeraChannel/LoginServlet");
			return;
		} */

		BoardfDAO bdao = new BoardfDAO();
		List<Board> topListMain = bdao.topList(0);

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
		/* if (session.getAttribute("user_id") == null ) {
			response.sendRedirect("/TeraChannel/LoginServlet");
			return;
		} */


		// 新着順か古い順か表示する
		BoardfDAO bdao = new BoardfDAO();


		if (request.getParameter("pulldown").equals("newevent")) {
			List<Board> topListMain = bdao.topList(0);
		} else if (request.getParameter("pulldown").equals("oldevant")) {
			List<Board> topListMain = bdao.topList(1);
		}
		// リアクションの多い順か少ない順か表示する
		if (request.getParameter("pulldown").equals("popular")) {
			List<Board> topListMain = bdao.topList(0);
		} else if (request.getParameter("pulldown").equals("notpopular")) {
			List<Board> topListMain = bdao.topList(1);
		}
		//クリックされた見出しのパラメータを取得する
//		request.setCharacterEncoding("UTF-8");
//		String[] main = request.getParameterValues("main");
//		if (main.equals(getParameterValues("main"))) {
//		//if (getParameterValues("main").equals("${b.board_topic}")) {
//
		//見出しに適した投稿IDをリクエストスコープに格納する
		request.setAttribute("board_id", request.getParameter("board_id"));


		//詳細ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/TeraChannel/ViewBoardServlet");
		dispatcher.forward(request, response);
	}
}
