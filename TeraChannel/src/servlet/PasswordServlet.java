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

/*
				// パスワードの更新を行う
				PasswordDao PDao = new PasswordDao();
				if (request.getParameter("SUBMIT").equals("更新")) {
					if (PDao.update(new Bc(id ))) {	// 更新成功
						request.setAttribute("result",
						new Result("更新成功！", "レコードを更新しました。", "/simpleBC/MenuServlet"));
					}
					else {												// 更新失敗
						request.setAttribute("result",
						new Result("更新失敗。。", "レコードを更新できませんでした。", "/simpleBC/MenuServlet"));
					}
				}
*/



		// パスワード変更ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Password.jsp");
		dispatcher.forward(request, response);
	}
}