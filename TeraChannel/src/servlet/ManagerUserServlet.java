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

		//入力されたIDを検索
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");

		if(request.getParameter("SEARCH") != null) {
			String user_id = request.getParameter("text");

			// 検索処理を行う
			UserDao u2Dao = new UserDao();
			if (request.getParameter("SEARCH").equals("ID検索")) {
				List<User> userList = u2Dao.selectById(Integer.parseInt(user_id));

				// 検索結果をリクエストスコープに格納する
				request.setAttribute("userList", userList);
			}

			// ユーザー管理ページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ManagerUser.jsp");
			dispatcher.forward(request, response);

			return;
		}

		// 更新を行う
		UserDao u4Dao = new UserDao();
		if (request.getParameter("SUBMIT") != null) {
		  if (request.getParameter("SUBMIT").equals("更新")) {
			u4Dao.update(new User(0, "", "", 0, "", 0, 0));
			request.setAttribute("result", "/TeraChannel/ManagerUserServlet");

			// 結果ページにフォワードする
			RequestDispatcher dispatcher4 = request.getRequestDispatcher("/WEB-INF/jsp/ManagerUser.jsp");
			dispatcher4.forward(request, response);
		  }
		  return;
		}
	}
}