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
		 if (session.getAttribute("user_id") == null ) {
			response.sendRedirect("/TeraChannel/LoginServlet");
			return;
		}

		//リストを取得する
		BoardfDAO bdao = new BoardfDAO();
		List<Board> topListMain = bdao.topList(0);

		//取得結果をリクエストスコープに格納する
		request.setAttribute("topListMain", topListMain);

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

		//リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");

	    BoardfDAO bdao = new BoardfDAO();

		// 新着順か古い順か表示する
	    if(request.getParameter("refresh") != null) {
	    	request.getParameter("refresh");
	    	//pulldownがnullじゃなない場合、新着順の処理する
			if (request.getParameter("refresh").equals("newevent")) {
				//メソッド呼び出し
				List<Board> topListMain = bdao.topList(0);
				//取得結果をリクエストスコープに格納する
				request.setAttribute("topListMain", topListMain);
				//JSPのoptionタグのselectedを使用して選択されたもの、されていないものをリクエストスコープに格納する
				request.setAttribute("refresh1", "selected");
				request.setAttribute("refresh2", "");
			} else if (request.getParameter("refresh").equals("oldevant")) {
				List<Board> topListMain = bdao.topList(1);
				request.setAttribute("topListMain", topListMain);
				request.setAttribute("refresh1", "");
				request.setAttribute("refresh2", "selected");
			}
	    }

		// リアクションの多い順か少ない順か表示する
		if(request.getParameter("reaction") != null) {
			request.getParameter("reaction");
		    //pulldownがnullじゃなない場合、リアクション順の処理する
			if (request.getParameter("reaction").equals("popular")) {
				List<Board> topListMain = bdao.topList(0);
				request.setAttribute("topListMain", topListMain);
				//JSPのoptionタグのselectedを使用して選択されたものをリクエストスコープに格納する
				request.setAttribute("reaction1", "selected");
				request.setAttribute("reaction2", "");
			} else if (request.getParameter("reaction").equals("notpopular")) {
				List<Board> topListMain = bdao.topList(1);
				request.setAttribute("topListMain", topListMain);
				//JSPのoptionタグのselectedを使用して選択されたものをリクエストスコープに格納する
				request.setAttribute("reaction1", "");
				request.setAttribute("reaction2", "selected");
			}

	    }



		//検索処理を行う
    	String error = "";
	    if(request.getParameter("submit") != null) {
	    	if (request.getParameter("submit").equals("検索")) {
	    			//パラメータを取得
	    			String word = request.getParameter("search");
	    			request.getParameter("board_topic");
	    			request.getParameter("board_main");
	    			request.getParameter("reply_main");
	    			//メソッド呼び出し
	    			List<Board> topListMain = bdao.select(word);
	    			if (topListMain.size() != 0) {
	    				//取得結果をリクエストスコープに格納する
	    				request.setAttribute("topListMain", topListMain);
	    			} else {
	    				error ="キーワードが見つかりませんでした。ぜひ投稿してください。";
	    				request.setAttribute("error", error);
	    			}
	    	}
	    }

		//見出しに適した投稿IDをリクエストスコープに格納する
		request.setAttribute("board_id", request.getParameter("board_id"));

		//詳細ページにフォワードする//
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ViewMenu.jsp");
		dispatcher.forward(request, response);
	}
}
