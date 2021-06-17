package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ManagerDao;
//import dao.UserDao;
import model.User;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Login.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// リクエストパラメータを取得する
				request.setCharacterEncoding("UTF-8");
				String user_mail = request.getParameter("mail");
				String user_pw = request.getParameter("pass");
				System.out.println(user_mail + " / " + user_pw );

				// ログイン処理を行う
				ManagerDao iDao = new ManagerDao();
				if (iDao.isLoginOK(user_mail, user_pw)) {
					// ログイン成功
					//ログイン後の画面に移動する
		//編集中
					// セッションスコープにメールアドレスを格納する
					//HttpSession session = request.getSession();
					//session.setAttribute("mail", new User(mail));

					// メニューサーブレットにリダイレクトする
					response.sendRedirect("/TeraChannel/MenuServlet");
				}
				else {
					// ログイン失敗
					//今の画面でエラーを表示する
					//エラーメッセージをjspに渡す
					request.setAttribute("errorMessage", "ログインに失敗しました");
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Login.jsp");
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


