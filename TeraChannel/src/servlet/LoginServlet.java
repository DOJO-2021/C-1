package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


//import dao.UserDao;
import Mita.UserDao;
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
				UserDao iDao = new UserDao();
				if (iDao.isLoginOK(user_mail, user_pw)) {
					// ログイン成功
					//ログイン後の画面に移動する


					// セッションスコープにIDを格納する
					//HttpSession session = request.getSession();
					//session.setAttribute("id",us.getUser_id());
					User us=new User();
					HttpSession session = request.getSession();
					session.setAttribute("user_id",us.getUser_id());
					// メニューサーブレットにリダイレクトする
					response.sendRedirect("/TeraChannel/MenuServlet");
				}
				else {
					// ログイン失敗
					//今の画面でエラーを表示する
					//エラーメッセージをjspに渡す
					//repuest.setAttribute();
					//RepuestDispatcher dispatcher = repuest.getRepuestDispatcher
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


