package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// ユーザー管理ページにフォワードする
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ManagerUser.jsp");
				dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
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



		//入力されたIDを検索
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String user_id = request.getParameter("USER_ID");

		// 検索処理を行う
		UserDao u2Dao = new UserDao();
		List<User> userList2 = u2Dao.select(new User(0, "", "", 0, "", 0, 0));

		// 検索結果をリクエストスコープに格納する
		request.setAttribute("userList", userList);

		// ユーザー管理ページにフォワードする
		RequestDispatcher dispatcher2 = request.getRequestDispatcher("/WEB-INF/jsp/ManagerUser.jsp");
		dispatcher.forward(request, response);



		//ドクロカウントの昇順降順で並べ替える
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String user_count = request.getParameter("USER_COUNT");

		// 検索処理を行う
		UserDao u3Dao = new UserDao();
		List<User> userList3 = u3Dao.select(new User(0, "", "", 0, "", 0, 0));

		// 検索結果をリクエストスコープに格納する
		request.setAttribute("userList", userList);

		// ユーザー管理ページにフォワードする
		RequestDispatcher dispatcher3 = request.getRequestDispatcher("/WEB-INF/jsp/ManagerUser.jsp");
		dispatcher.forward(request, response);


	}














//
//
//
//	//ドクロカウントの数を更新する
//	// リクエストパラメータを取得する
//	request.setCharacterEncoding("UTF-8");
//	String user_id = request.getParameter("USER_ID");
//	String user_name = request.getParameter("USER_NAME");
//	String user_pw = request.getParameter("USER_PW");
//	String user_type = request.getParameter("USER_TYPE");
//	String user_mail = request.getParameter("USER_MAIL");
//	String user_count= request.getParameter("USER_COUNT");
//	String user_nameCount= request.getParameter("USER_NAMECOUNT");
//
//	// 更新または削除を行う
//			UserDao uDao = new UserDao();
//			if (request.getParameter("SUBMIT").equals("更新")) {
//				if (uDao.update(new User(user_id, user_name, user_pw, user_type, user_mail, user_count, user_nameCount  ))) {	// 更新成功
//					request.setAttribute("result",
//					new Result("更新成功！", "レコードを更新しました。", "/C-1/ManagerUserServlet"));
//				}
//				else {												// 更新失敗
//					request.setAttribute("result",
//					new Result("更新失敗！", "レコードを更新できませんでした。", "/C-1/ManagerUserServlet"));
//				}
//			}
//
//			// 結果ページにフォワードする
//			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ManagerUser.jsp");
//			dispatcher.forward(request, response);
//		}


}
