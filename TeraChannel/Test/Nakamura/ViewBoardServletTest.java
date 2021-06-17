package Nakamura;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ViewBoardServlet
 */
@WebServlet("/ViewBoardServletTest")
public class ViewBoardServletTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//この段階では、ViewMenuServlet.javaからrequestに投稿IDが格納されている状態
		//なので、ここで呼び出して入れる必要がない
		//フォワードをする前に返信の一覧を表示するためのデータを取ってくるメソッドで
		//リクエストスコープに格納する
		request.setCharacterEncoding("UTF-8");
		int board_id = Integer.parseInt(request.getParameter("BOARD_ID"));

		BoardDao bDao=new BoardDao();
		ReplyDao rDao=new ReplyDao();
		//投稿データ（Board型のインスタンス）の取得/リクエストスコープへの格納
		Board bd=bDao.showBoard(board_id);
		request.setAttribute("bd",bd);
		//返信データ（Reply型のインスタンス）の取得/リクエストスコープへの格納
		List<Reply> replyList = rDao.showReply(board_id);
		request.setAttribute("replyList",replyList);

		//それぞれのデータを格納できた段階で詳細ページjspにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ViewBoard.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		//まだリダイレクト機能は使いたくないのでコメントアウト
		HttpSession session = request.getSession();
		/*if (session.getAttribute("id") == null) {
			response.sendRedirect("/Terachannel/src/LoginServlet");
			return;
		}
		 */

		// リクエストパラメータを取得する
			request.setCharacterEncoding("UTF-8");
			int    board_id = Integer.parseInt(request.getParameter("BOARD_ID"));
			String board_main = request.getParameter("BOARD_MAIN");
			String board_topic= request.getParameter("BOARD_TOPIC");
			int	   board_smile=Integer.parseInt(request.getParameter("SMILE"));
			int    board_shock = Integer.parseInt(request.getParameter("SHOCK"));
			int    board_tear= Integer.parseInt(request.getParameter("TEAR"));
			String board_update= request.getParameter("BOARD_UPDATE");
			int    reply_id= Integer.parseInt(request.getParameter("REPLY_ID"));
			String reply_main= request.getParameter("REPLY_MAIN");
			String reply_date = request.getParameter("REPLY_DATE");

			//ユーザーIDの取得(セッションスコープから)
			int user_id=(int)session.getAttribute("USER_ID");

			BoardDao bDao=new BoardDao();
			ReplyDao rDao=new ReplyDao();

			/*
			//投稿の編集ボタンが押されていた場合
			if (request.getParameter("SUBMIT").equals("編集:投稿")) {
				if (bDao.editBoard(new Board(board_id,board_topic,board_main,board_smile,
						board_shock,board_tear,board_update,user_id))) {// 更新成功

				}
				else {												// 更新失敗

				}
			}//投稿の削除ボタンが押されていた場合
			else if(request.getParameter("SUBMIT").equals("削除:投稿")){
				if (bDao.deleteBoard(board_id)) {	// 削除成功

				}
				else {						// 削除失敗

				}
			}//返信の編集ボタンが押されていた場合
			else if(request.getParameter("SUBMIT").equals("編集:返信")) {
				if (rDao.editReply(new Reply(reply_id,reply_main,reply_date,user_id,board_id))) {	// 更新成功

				}
				else {						// 更新失敗

				}
			}//返信の削除ボタンが押されていた場合
			else if(request.getParameter("SUBMIT").equals("削除:返信")) {
				if (rDao.deleteReply(reply_id)) {	// 削除成功

				}
				else {						// 削除失敗

				}
			}//返信ボタンが押されていた場合
			else if(request.getParameter("SUBMIT").equals("返信")) {
				if (rDao.insertReply(new Reply(reply_id,reply_main,reply_date,user_id,board_id))) {	// 削除成功

				}
				else {						// 削除失敗

				}
			}//リアクションボタンが押されていた場合
			else if(request.getParameter("SUBMIT").equals("リアクション")) {
				if (bDao.editBoard(new Board(board_id,board_topic,board_main,board_smile,
						board_shock,board_tear,board_update,user_id))) {// 更新成功

				}
				else {												// 更新失敗

				}
			}//検索ボタンが押されていた場合
			else if(request.getParameter("SUBMIT").equals("検索")) {
				if (rDao.searchReply(new Reply(reply_id,reply_main,reply_date,user_id,board_id))) {// 更新成功

				}
				else {												// 更新失敗

				}
			}
			//同じページにフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ViewBoardServlet.java");
			dispatcher.forward(request, response);
			*/
	}

}
