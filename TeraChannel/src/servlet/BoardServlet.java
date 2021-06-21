package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Fukuda.BoardDAO;
import model.Board;

/**
 * Servlet implementation class BoardServlet
 */
@WebServlet("/BoardServlet")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//もしもログインしていなかったらログインサーブレットに移動する
		HttpSession session = request.getSession();
	/*	if (session.getAttribute("user_id") == null) {
			response.sendRedirect("/TeraChannel/LoginServlet");
			return;
		}
	*/
		//投稿ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Board.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//もしもログインしていなかったらログインサーブレットに移動する
		HttpSession session = request.getSession();
	/*	if (session.getAttribute("user_id") == null) {
			response.sendRedirect("/TeraChannel/LoginServlet");
			return;
		}
	*/

		//リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String board_topic = request.getParameter("board_topic");
		String board_main = request.getParameter("board_main");


		//user_idをセッションスコープから取ってくる
		int user_id = (int)session.getAttribute("user_id");

		//投稿処理を行う	user_idはセッションスコープに格納されているものを使用する
		BoardDAO bDao = new BoardDAO();
		if (bDao.insert(new Board(0,board_topic,board_main,0,0,0,"current_date",user_id))) {

			//投稿できた場合は投稿IDをリクエストスコープに格納
			request.setAttribute("board_id",request.getParameter("board_id"));

			//詳細ページへフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/TeraChannel/ViewBoardServlet");
			dispatcher.forward(request, response);
		}
		else {
			//投稿できなかった場合は投稿ページへリダイレクトする
			response.sendRedirect("/TeraChannel/BoardServlet");
		}


	}

}
