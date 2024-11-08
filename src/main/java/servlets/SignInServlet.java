package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.DAO;

/**
 * Servlet implementation class SignInServlet
 */
@WebServlet("/SignInServlet")
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String message;
    private DAO dao;
    private ResultSet resultSet;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignInServlet() {
        super();
        dao = new DAO();
    }
    public void init(){
    	
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		if(!dao.isUserRegistered(email)) {
			// mail don't exist redirect to signup
			response.sendRedirect("signup.jsp"); 
		}else {
			if(dao.verifyCredentials(email, password)) {
				HttpSession session = request.getSession();
				session.setAttribute("email", email);
				response.sendRedirect("products.jsp");
			}else {
				out.println("Password errata");
			}
		}
	}
}
