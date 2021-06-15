package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ManagerDao;
/**
 * Servlet implementation class ManagerLoginServlet
 */
@WebServlet("/ManagerLoginServlet")
public class ManagerLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ManagerLogin.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String mail = request.getParameter("mail");
		String pw = request.getParameter("pass");
		System.out.println(mail + " / " + pw );

		// ログイン処理を行う
		ManagerDao iDao = new ManagerDao();
		if (iDao.isLoginOK(mail, pw)) {
			// ログイン成功
			//ログイン後の画面に移動する

			// セッションスコープにメールアドレスを格納する
			//HttpSession session = request.getSession();
			//session.setAttribute("mail", new Manager(mail));

			// メニューサーブレットにリダイレクトする
			response.sendRedirect("/TeraChannel/MenuServlet");
		}
		else {
			// ログイン失敗
			//今の画面でエラーを表示する
			//エラーメッセージをjspに渡す
			request.setAttribute("errorMessage", "メールアドレス・パスワードが間違っています");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ManagerLogin.jsp");
			dispatcher.forward(request, response);

			// リクエストスコープに、タイトル、メッセージ、戻り先を格納する
			//request.setAttribute("result",
			//new Result("ログイン失敗！", "IDまたはPWに間違いがあります。", "/simpleBC/LoginServlet"));

			// 結果ページにフォワードする
			//RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
			//dispatcher.forward(request, response);
		}
	}

}
