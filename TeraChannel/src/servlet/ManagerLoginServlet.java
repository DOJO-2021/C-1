package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import dao.ManagerDao;
import model.LoginUser;
import model.User;
import model.Manager;
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
		String manager_mail = request.getParameter("mail");
		String manager_pw = request.getParameter("pass");
		System.out.println(manager_mail + " / " + manager_pw );

		// ログイン処理を行う
		ManagerDao iDao = new ManagerDao();
		if (iDao.isLoginOK(manager_mail, manager_pw)) {
			// ログイン成功
			//ログイン後の画面に移動する
//編集中
			// セッションスコープにIDを格納する
			//Manager manager = new Manager();
			ManagerDao mDao = new ManagerDao();

			List<Manager> ManagerList = mDao.User(manager_mail, manager_pw);


			HttpSession session = request.getSession();

			for(Manager i:ManagerList) {
				//IDを格納
				session.setAttribute("manager_id", i.getManager_id());
			//int sampleid=i.getUser_id();
				//int sampletype=i.getUser_type();
				//String samplename=i.getUser_name();
				//mailを格納
				session.setAttribute("manager_mail",manager_mail);
				//pwを格納
				session.setAttribute("manager_pw",manager_pw);;

			}

		//	HttpSession session = request.getSession();
			session.setAttribute("manager_mail",manager_mail);

			// メニューサーブレットにリダイレクトする
			response.sendRedirect("/TeraChannel/ManagerMenuServlet");
		}
		else {
			// ログイン失敗
			//今の画面でエラーを表示する
			//エラーメッセージをjspに渡す
			request.setAttribute("errorMessage", "ログインに失敗しました");
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
