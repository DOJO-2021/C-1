package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Takahashi.UserDao;
//import dao.UserDao;
import model.User;

/**
 * Servlet implementation class ManagerUserServlet
 */
@WebServlet("/ManagerUserServlet")
public class ManagerUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		//DAOからデータを呼び出し、userテーブルを出力する。
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");

		// 検索処理を行う
		UserDao uDao = new UserDao();
		List<User> userList = uDao.select(new User(0, "", "", 0, "", 0, 0,更新日時));

		// 検索結果をリクエストスコープに格納する
		request.setAttribute("userList", userList);

		// ユーザー管理ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ManagerUser.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//入力されたIDを検索
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		UserDao uDao = new UserDao();


		if(request.getParameter("search") != null) {
			String user_id = request.getParameter("text");
			// 検索処理を行う

			if (request.getParameter("search").equals("ID検索")) {
				List<User> userList = uDao.selectById(Integer.parseInt(user_id));

				// 検索結果をリクエストスコープに格納する
				request.setAttribute("userList", userList);
			}

			// ユーザー管理ページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ManagerUser.jsp");
			dispatcher.forward(request, response);

			return;
		}



		//ドクロカウント昇順降順メソッド
		if(request.getParameter("pulldown") != null) {
	    	//pulldownがnullじゃなない場合、新着順の処理する
			if (request.getParameter("pulldown").equals("newevent")) {
				List<User> userList = uDao.selectByCount(1);
				//取得結果をリクエストスコープに格納する
				request.setAttribute("userList", userList);
			} else if (request.getParameter("pulldown").equals("oldevent")) {
				List<User> userList = uDao.selectByCount(0);
				request.setAttribute("userList", userList);
			}


			// ユーザー管理ページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ManagerUser.jsp");
			dispatcher.forward(request, response);

		}





		//更新日時昇順降順メソッド
		if(request.getParameter("pulldown") != null) {
	    	//pulldownがnullじゃなない場合、新着順の処理する
			if (request.getParameter("pulldown").equals("newdate")) {
				List<User> userList = uDao.selectByCount(1);
				//取得結果をリクエストスコープに格納する
				request.setAttribute("userList", userList);
			} else if (request.getParameter("pulldown").equals("olddate")) {
				List<User> userList = uDao.selectByCount(0);
				request.setAttribute("userList", userList);
			}

			// ユーザー管理ページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ManagerUser.jsp");
			dispatcher.forward(request, response);
		}











		//ドクロカウントを反映させるメソッド
		if (request.getParameter("user_count") != null) {
			  if (request.getParameter("submit").equals("更新")) {

				 //実名化カウントの値を持ってきて、更新に反映
				int user_id=Integer.parseInt(request.getParameter("user_id"));
				int user_count=Integer.parseInt(request.getParameter("user_countHidden"));
				int user_nameCount=Integer.parseInt(request.getParameter("user_nameCountHidden"));

				if(uDao.update(new User(user_id, "", "", 0, "", user_count, 実名化カウント,更新日時))) {
					//成功
				}else {
					//失敗
				}

				// 結果ページにフォワードする
				doGet(request,response);
			  }
			}
		}
}