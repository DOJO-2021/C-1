package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Takahashi.UserDao2;
import dao.UserDao;
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
		UserDao2 uDao = new UserDao2();
		List<User> userList = uDao.select(new User(0, "", "", 0, "", 0, 0));

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
		// TODO Auto-generated method stub
		doGet(request, response);

		//入力されたIDを検索
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String user_id = request.getParameter("USER_ID");

		// 検索処理を行う
		UserDao u2Dao = new UserDao();
		List<User> userList = u2Dao.select(new User(0, "", "", 0, "", 0, 0));
		if (request.getParameter("SEARCH").equals("ID検索"));

		// 検索結果をリクエストスコープに格納する
		request.setAttribute("userList", userList);

		// ユーザー管理ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ManagerUser.jsp");
		dispatcher.forward(request, response);

		//ドクロカウントの昇順降順で並べ替える
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String user_count = request.getParameter("USER_COUNT");

		//昇順押されたら昇順、降順押されたら降順
		UserDao u3Dao = new UserDao();
		if (request.getParameter("1").equals("key")) {
			request.setAttribute("key", u3Dao);
		} else if (request.getParameter("2").equals("key")) {
			request.setAttribute("key", u3Dao);
		}
		// ユーザー管理ページにフォワードする
		RequestDispatcher dispatcher3 = request.getRequestDispatcher("/WEB-INF/jsp/ManagerUser.jsp");
		dispatcher.forward(request, response);

		//ドクロカウントの数を更新する
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String user_count2 = request.getParameter("USER_COUNT");

		// 更新を行う
		UserDao u4Dao = new UserDao();
		if (request.getParameter("SUBMIT").equals("更新")) {
			request.setAttribute("result", "/C-1/ManagerUserServlet");

			// 結果ページにフォワードする
			RequestDispatcher dispatcher4 = request.getRequestDispatcher("/WEB-INF/jsp/ManagerUser.jsp");
			dispatcher.forward(request, response);
		}
	}
}