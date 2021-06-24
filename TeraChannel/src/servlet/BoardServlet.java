package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BoardDao;
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
		//int user_id = (int)session.getAttribute("user_id");

		//投稿処理を行う	user_idはセッションスコープに格納されているものを使用する
		BoardDao bDao = new BoardDao();
		//if文はtrue/falseで判定するのでinsert結果(board_idの値)を変数に格納
		int board_id = bDao.insert(new Board(0,board_topic,board_main,0,0,0,"current_date",6));//最後user_id
		if (board_id != 0) {
			//board_idをinsertしたデータからもってくる必要がある
			//int board_id=bDao.select(new Board(0,board_topic,board_main,0,0,0,"current_date",user_id));
			//投稿できた場合は投稿IDをリクエストスコープに格納
			request.setAttribute("board_id",board_id);
			//int board_id = Integer.parseInt(request.getParameter("board_id"));

			//仮でuser_id(値は1)をセッションスコープに入れておく
			//session.setAttribute("user_id",1 );

			//詳細ページへフォワードする
			//RequestDispatcher dispatcher = request.getRequestDispatcher("/ViewBoardServlet");
			//dispatcher.forward(request, response);
			//詳細ページへboard_idを保持させてリダイレクトする（フォワードだとdoPostに飛んでしまうので）
			response.sendRedirect("/TeraChannel/ViewBoardServlet?board_id=" + board_id);
		}
		else {
			//投稿できなかった場合はエラーメッセージ表示の上、同じページへフォワード
			request.setAttribute("errorMessage", "に失敗しました");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Board.jsp");
			dispatcher.forward(request, response);
			//投稿ページへリダイレクトする
			//response.sendRedirect("/TeraChannel/BoardServlet");
		}


	}

}
