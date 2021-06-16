package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UpdateDeleteServlet
 */
@WebServlet("/PasswordServlet")
public class PasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/Terachannel/LoginServlet");
			return;
		}
		// リクエストパラメータを取得する
				request.setCharacterEncoding("UTF-8");
				String user_pw = request.getParameter("user_pw");
/*
				// パスワードの更新を行う
				PasswordDao PDao = new PasswordDao();
				if (request.getParameter("change").equals("変更")) {
					if (PDao.update(new User(user_pw))) {	// 変更
						request.setAttribute("result",
					    ("パスワードを更新しました。", "/TeraChannel/MenuServlet"));
					}
					else {												// 更新失敗
						request.setAttribute("result",
						("更新失敗。。", "レコードを更新できませんでした。", "/TeraChannel/MenuServlet"));
					}
				}

*/


		// パスワード変更ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Menu.jsp");
		dispatcher.forward(request, response);
	}
}