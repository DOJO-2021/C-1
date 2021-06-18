package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PasswordDao;
import model.Password;

/**
 * Servlet implementation class UpdateDeleteServlet
 */
@WebServlet("/PasswordServlet")
public class PasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/Terachannel/LoginServlet");
			return;
		}
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");

		//前のパスワード、新しいパスワード、新しパスワード１取得
		String user_mail= request.getParameter("user_mail");
		String new_pw = request.getParameter("new_pw");



		//　一致しなければな、エラー


		// パスワードの更新を行う　ログインしている人のIDと新しいパスワードをDaoに渡す。

		PasswordDao PDao = new PasswordDao();
		if (request.getParameter("change").equals("変更")) {
			PDao.update(new Password(user_mail,new_pw));
		}

		// ログイン変更ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Login.jsp");
		dispatcher.forward(request, response);
	}
}