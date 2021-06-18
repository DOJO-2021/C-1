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
		//int board_id = Integer.parseInt(request.getParameter("BOARD_ID"));
		int board_id = 1;

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
			//投稿テーブルパラメータ
			int    board_id = Integer.parseInt(request.getParameter("BOARD_ID"));
			String board_main = request.getParameter("BOARD_MAIN");
			String board_topic= request.getParameter("BOARD_TOPIC");
			int	   board_smile=Integer.parseInt(request.getParameter("SMILE"));
			int    board_shock = Integer.parseInt(request.getParameter("SHOCK"));
			int    board_tear= Integer.parseInt(request.getParameter("TEAR"));
			String board_update= request.getParameter("BOARD_UPDATE");
			//返信テーブルパラメータ
			int    reply_id= Integer.parseInt(request.getParameter("REPLY_ID"));
			String reply_main= request.getParameter("REPLY_MAIN");
			String reply_date = request.getParameter("REPLY_DATE");

			//検索用パラメータ
			String search_reply=request.getParameter("SEARCH_REPLY");

			//共通のユーザーIDの取得(セッションスコープから)
			//下の行はセッションスコープに格納した仮置きのユーザーID
			session.setAttribute("USER_ID", 1);
			int user_id=(int)session.getAttribute("USER_ID");

			BoardDao bDao=new BoardDao();
			ReplyDao rDao=new ReplyDao();


			//投稿の編集ボタンが押されていた場合
			if (request.getParameter("SUBMIT").equals("編集:投稿")) {
				if (bDao.editBoard(board_main,board_id)) {// 更新成功

				}
				else {												// 更新失敗
					//失敗したときの処理の案として、新しいjsp(失敗をお知らせする)に遷移するか

					//同じServletに再フォワードを行ってhtmlの<c:if>を使ってjavascriptでその場でエラーアラートを出すか
					//上の場合それを識別するようのパラメータを一つ作ってあげる必要がある

				}
			}//投稿の削除ボタンが押されていた場合
			else if(request.getParameter("SUBMIT").equals("削除:投稿")){
				if (bDao.deleteBoard(board_id)) {	// 削除成功

				}
				else {						// 削除失敗

				}
			}//返信の編集ボタンが押されていた場合
			else if(request.getParameter("SUBMIT").equals("編集:返信")) {
				if (rDao.editReply(reply_main,reply_id)) {	// 更新成功

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
				if (bDao.registReaction(board_id,board_smile,board_shock,board_tear)) {// 更新成功

				}
				else {												// 更新失敗

				}
			}//検索ボタンが押されていた場合
			//検索で得られた返信一覧が返ってくるので、リクエストスコープにBoardインスタンスと
			//List型のReplyインスタンスを格納してjspにフォワードすればよい
			else if(request.getParameter("SUBMIT").equals("検索")) {

				//検索で得られた新しい返信配列を取得しリクエストスコープに格納
				List<Reply> replyList = rDao.searchReply(search_reply);
				request.setAttribute("replyList",replyList);
				//対応する投稿データを取得しリクエストスコープに格納
				Board bd=bDao.showBoard(board_id);
				request.setAttribute("bd",bd);

				//検索で得られた新しい返信Listと対応する投稿データをリクエストスコープに格納しjspに再フォワードを行う
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ViewBoard.jsp");
				dispatcher.forward(request, response);
			}
			//同じページ(サーブレット)にフォワード
			//サーブレットへのフォワードでエラー発生中
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Test/ViewBoardServletTest.java");
			dispatcher.forward(request, response);

	}

}
