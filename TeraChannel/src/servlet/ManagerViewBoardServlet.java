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

import Takahashi.UserDao;
import dao.BoardDao;
import dao.ReplyDao;
import model.Board;
import model.Reply;
import model.User;



/**
 * Servlet implementation class ViewBoardServlet
 */
@WebServlet("/ManagerViewBoardServlet")
public class ManagerViewBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();


		if (session.getAttribute("manager_mail") == null) {
			response.sendRedirect("/Terachannel/src/ManagerLoginServlet");
			return;
		}
		//この段階では、ViewMenuServlet.javaからrequestに投稿IDが格納されている状態
		//なので、ここで呼び出して入れる必要がない
		//フォワードをする前に返信の一覧を表示するためのデータを取ってくるメソッドで
		//リクエストスコープに格納する

		int board_id = Integer.parseInt(request.getParameter("board_id"));

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
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ManagerViewBoard.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		//まだリダイレクト機能は使いたくないのでコメントアウト
		HttpSession session = request.getSession();
		if (session.getAttribute("manager_mail") == null) {
			response.sendRedirect("/Terachannel/src/ManagerLoginServlet");
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


			//投稿の削除ボタンが押されていた場合
			if(request.getParameter("submit").equals("投稿:削除")){

				board_id = Integer.parseInt(request.getParameter("board_id"));

				if (bDao.deleteBoard(board_id)) {	// 削除成功

				}
				else {						// 削除失敗

					request.setAttribute("fail","投稿の削除" );

				}
			}//返信の削除ボタンが押されていた場合
			else if(request.getParameter("submit").equals("返信:削除")) {

				reply_id= Integer.parseInt(request.getParameter("reply_id"));

				if (rDao.deleteReply(reply_id)) {	// 削除成功

				}
				else {						// 削除失敗
					request.setAttribute("fail","返信の削除" );
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

				//検索で得られた新しい返信Listと対応する投稿データをリクエストスコープに格納しjspに再フォワードを行う
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ManagerViewBoard.jsp");
				dispatcher.forward(request, response);
			}
			//同じページ(サーブレット)にフォワード
			//サーブレットへのフォワードでエラー発生中
			request.setAttribute("board_id",request.getParameter("board_id"));
			doGet(request,response);

	}

}