package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Takahashi.UserDao;
import model.User;

/**
 * Servlet implementation class MenuServlet
 */
@WebServlet("/MenuServlet")
public class MenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("user_id") == null) {
			response.sendRedirect("/TeraChannel/LoginServlet");
			return;
		}


		UserDao uDao=new UserDao();
		User user=uDao.dokuro((int)session.getAttribute("user_id"));
		int dokuro=user.getUser_count();

		request.setAttribute("dokuro",dokuro);

		// メニューページにフォワードする
        int user_type = (int)session.getAttribute("user_type");
        if(user_type == 0) {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Menu.jsp");
		dispatcher.forward(request, response);
        }else{
        	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/TeacherMenu.jsp");
    		dispatcher.forward(request, response);
        }
    }
}
/*
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	// メニューページにフォワードする
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/menu.jsp");
				dispatcher.forward(request, response);
	}
*/

