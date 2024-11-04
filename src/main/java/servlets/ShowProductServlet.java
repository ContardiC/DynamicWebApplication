package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DAO;

/**
 * Servlet implementation class ShowProductServlet
 */
@WebServlet("/ShowProductServlet")
public class ShowProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String message;
    private ResultSet products;
    private DAO dao;
    private Connection con;
   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init() {
        message = "Hello World!";
        dao = new DAO();
    }  

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try {
            dao.getConnection();
            products = dao.getProducts();
            // rendering tabella
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Tabella Prodotti</title>");
            out.println("<link rel=\"stylesheet\" href=\"css/style.css\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<table>");
            out.println("<thead>");
            out.println("<tr><th>Nome</th><th>Descrizione</th><th>Prezzo</th></tr>");
            out.println("</thead>");
            out.println("<tbody>");
            while (products.next()) {
                out.println("<tr>");
                out.println("<td>" + products.getString("name") + "</td>");
                out.println("<td>" + products.getString("description")+ "</td>");
                out.println("<td>" + products.getDouble("price")+ "</td>");
                out.println("</tr>");
            }
            out.println("</tbody>");
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}

}
