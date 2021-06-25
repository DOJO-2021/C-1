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

import dao.BoardDao;
import dao.ReplyDao;
import dao.UserDao;
import model.Board;
import model.Reply;
import model.User;

/**
 * Servlet implementation class ViewBoardServlet
 */
@WebServlet("/ViewBoardServlet")
public class ViewBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		if (session.getAttribute("user_id") == null) {
			response.sendRedirect("/Terachannel/src/LoginServlet");
			return;
		}
		//この段階では、ViewMenuServlet.javaからrequestに投稿IDが格納されている状態
		//なので、ここで呼び出して入れる必要がない
		//フォワードをする前に返信の一覧を表示するためのデータを取ってくるメソッドで
		//リクエストスコープに格納する
		//int board_id = Integer.parseInt(request.getParameter("BOARD_ID"));

		int board_id=Integer.parseInt(request.getParameter("board_id"));

		BoardDao bDao=new BoardDao();
		ReplyDao rDao=new ReplyDao();
		UserDao uDao = new UserDao();
		//全ユーザーデータの取得
		List<User> userList = uDao.select(new User(0, "", "", 0, "", 0, 0,""));
		request.setAttribute("userList",userList);
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
		if (session.getAttribute("user_id") == null) {
			response.sendRedirect("/Terachannel/src/LoginServlet");
			return;
		}


		// リクエストパラメータを取得する
			request.setCharacterEncoding("UTF-8");

			//変数の宣言だけを行っているのは、jsp側で全てのformタグにhidden形式でデータを置くのを省略するため
			int board_id,board_smile,board_shock,board_tear,reply_id;
			String board_main,board_topic,board_update,reply_main,reply_date,search_reply;


			//共通のユーザーIDの取得(セッションスコープから)
			//下の行はセッションスコープに格納した仮置きのユーザーID
			//session.setAttribute("user_id", 6);
			int user_id=(int)session.getAttribute("user_id");

			request.setAttribute("fail", "");

			BoardDao bDao=new BoardDao();
			ReplyDao rDao=new ReplyDao();


			//投稿の編集ボタンが押されていた場合
			if (request.getParameter("submit").equals("投稿:編集")) {

				 board_id = Integer.parseInt(request.getParameter("board_id"));
				 board_main = request.getParameter("board_main");

				if (bDao.editBoard(board_main,board_id)) {// 更新成功

				}
				else {												// 更新失敗
					request.setAttribute("fail","投稿の編集" );
					//同じServletに再フォワードを行ってhtmlの<c:if>を使ってjavascriptでその場でエラーアラートを出すか
					//上の場合それを識別するようのパラメータを一つ作ってあげる必要がある

				}
			}//投稿の削除ボタンが押されていた場合
			else if(request.getParameter("submit").equals("投稿:削除")){

				board_id = Integer.parseInt(request.getParameter("board_id"));

				if (bDao.deleteBoard(board_id)) {	// 削除成功
						//投稿自体の削除に成功した場合は、メニューページにリダイレクトを行う
						response.sendRedirect("/TeraChannel/ViewMenuServlet");
						return;
				}
				else {						// 削除失敗

					request.setAttribute("fail","投稿の削除" );

				}
			}//返信の編集ボタンが押されていた場合
			else if(request.getParameter("submit").equals("返信:編集")) {
				board_id = Integer.parseInt(request.getParameter("board_id"));
				reply_id= Integer.parseInt(request.getParameter("reply_id"));
				reply_main= request.getParameter("reply_main");

				if (rDao.editReply(reply_main,reply_id)) {	// 更新成功

				}
				else {						// 更新失敗
					request.setAttribute("fail","返信の編集" );
				}
			}//返信の削除ボタンが押されていた場合
			else if(request.getParameter("submit").equals("返信:削除")) {
				board_id = Integer.parseInt(request.getParameter("board_id"));
				reply_id= Integer.parseInt(request.getParameter("reply_id"));

				if (rDao.deleteReply(reply_id)) {	// 削除成功

				}
				else {						// 削除失敗
					request.setAttribute("fail","返信の削除" );
				}

			}

			//返信ボタンが押されていた場合
			else if(request.getParameter("submit").equals("返信")) {

				board_id = Integer.parseInt(request.getParameter("board_id"));
				reply_main= request.getParameter("reply_main");

				if (rDao.insertReply(new Reply(0,reply_main,"",user_id,board_id))) {	// 返信成功

				}
				else {						// 返信失敗
					request.setAttribute("fail","返信登録" );
				}
			}

			//リアクションボタンが押されていた場合
			else if(request.getParameter("submit").equals("リアクション")) {

				board_id = Integer.parseInt(request.getParameter("board_id"));
				board_smile=Integer.parseInt(request.getParameter("smile"));
				board_shock = Integer.parseInt(request.getParameter("shock"));
				board_tear= Integer.parseInt(request.getParameter("tear"));

				if (bDao.registReaction(board_id,board_smile,board_shock,board_tear)) {// 更新成功
					request.setAttribute("fail","リアクション登録に成功！" );
				}
				else {												// 更新失敗
					request.setAttribute("fail","リアクション登録" );
				}
			}

			//検索ボタンが押されていた場合
			//検索で得られた返信一覧が返ってくるので、リクエストスコープにBoardインスタンスと
			//List型のReplyインスタンスを格納してjspにフォワードすればよい
			else if(request.getParameter("submit").equals("検索")) {

				search_reply=request.getParameter("search_reply");
				board_id = Integer.parseInt(request.getParameter("board_id"));

				//検索で得られた新しい返信配列を取得しリクエストスコープに格納
				List<Reply> replyList = rDao.searchReply(search_reply,request.getParameter("board_id"));
				request.setAttribute("replyList",replyList);
				//対応する投稿データを取得しリクエストスコープに格納
				Board bd=bDao.showBoard(board_id);
				request.setAttribute("bd",bd);

				UserDao uDao = new UserDao();
				//全ユーザーデータの取得
				List<User> userList = uDao.select(new User(0, "", "", 0, "", 0, 0,""));
				request.setAttribute("userList",userList);

				//検索で得られた新しい返信Listと対応する投稿データをリクエストスコープに格納しjspに再フォワードを行う
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ViewBoard.jsp");
				dispatcher.forward(request, response);
			}
			//同じページ(サーブレット)にフォワード
			//サーブレットへのフォワードでエラー発生中
			request.setAttribute("board_id",request.getParameter("board_id"));
			doGet(request,response);

	}

}
