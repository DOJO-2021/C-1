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
		/* if (session.getAttribute("user_id") == null ) {
			response.sendRedirect("/TeraChannel/LoginServlet");
			return;
		} */

		//リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");

	    BoardfDAO bdao = new BoardfDAO();

		// 新着順か古い順か表示する
	    if(request.getParameter("pulldown") != null) {
	    	//pulldownがnullじゃなない場合、新着順の処理する
			if (request.getParameter("pulldown").equals("newevent")) {
				//メソッド呼び出し
				List<Board> topListMain = bdao.topList(0);
				//取得結果をリクエストスコープに格納する
				request.setAttribute("topListMain", topListMain);
			} else if (request.getParameter("pulldown").equals("oldevant")) {
				List<Board> topListMain = bdao.topList(1);
				request.setAttribute("topListMain", topListMain);
			}
	    }

		// リアクションの多い順か少ない順か表示する
		if(request.getParameter("pulldown") != null) {
		    //pulldownがnullじゃなない場合、新着順の処理する
			if (request.getParameter("pulldown").equals("popular")) {
				List<Board> topListMain = bdao.topList(0);
				request.setAttribute("topListMain", topListMain);
			} else if (request.getParameter("pulldown").equals("notpopular")) {
				List<Board> topListMain = bdao.topList(1);
				request.setAttribute("topListMain", topListMain);
			}
	    }


		//検索処理を行う
	    /*	    if(request.getParameter("submit") != null) {
	    	if (request.getParameter("submit").equals("検索")) {
	    		List<Board> topListMain = bdao.select("word");

	    		request.setAttribute("topListMain", topListMain);
	    	}
		}
		*/

    	String error = "";
	    if(request.getParameter("submit") != null) {
	    	if (request.getParameter("submit").equals("検索")) {
	    		try {
	    			//パラメータを取得
	    			String word = request.getParameter("search");
	    			request.getParameter("board_topic");
	    			request.getParameter("board_main");
	    			request.getParameter("reply_main");
	    			//メソッド呼び出し
	    			List<Board> topListMain = bdao.select(word);
	    			//取得結果をリクエストスコープに格納する
	    			request.setAttribute("topListMain", topListMain);
	    		} catch (Exception e) {
	    			error ="相当する値が見つかりませんでした";
	    		} finally {
	    			request.setAttribute("error", error);
	    			request.getRequestDispatcher("/WEB-INF/jsp/ViewMenu.jsp");
	    		}
	    	}

	    }


		//見出しに適した投稿IDをリクエストスコープに格納する
		request.setAttribute("board_id", request.getParameter("board_id"));

		//詳細ページにフォワードする//

		//RequestDispatcher dispatcher = request.getRequestDispatcher("/TeraChannel/ViewBoardServlet");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ViewMenu.jsp");
		dispatcher.forward(request, response);
	}
}
