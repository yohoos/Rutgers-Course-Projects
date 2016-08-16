package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.InsertAnswerDao;

/**
 * Servlet implementation class InsertAnswerServlet
 */
@WebServlet("/InsertAnswerServlet")
public class InsertAnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertAnswerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String answer = request.getParameter("answer");
		String qid = request.getParameter("qid");
		String username = request.getSession().getAttribute("name").toString();
		boolean check = InsertAnswerDao.insertAnswer(qid, answer, username);
		if (check) {
			request.setAttribute("link", "lobby.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("redirect.jsp");
			rd.forward(request, response);
		} else {
			request.setAttribute("error", "Insert Answer Failed! Possibly invalid answer!");
			RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
			rd.forward(request, response);
		}
	}

}
