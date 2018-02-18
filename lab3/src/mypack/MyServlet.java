package mypack;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet (
			HttpServletRequest request,
			HttpServletResponse response
			)
			throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost (
			HttpServletRequest request, 
			HttpServletResponse response
			) 
			throws ServletException, IOException {
		//doGet(request, response);
		processRequest(request, response);
	}

	protected void processRequest (
			HttpServletRequest request, 
			HttpServletResponse response
			)
            throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title></title>"); 
        out.println("</head>");
        out.println("<body bgcolor='#aaccff'");
        out.println("<form>");
        out.println("<h2> Привет клиенту!!!</h2><br><br>");
        out.println("<b>");
        out.println("<h1>");
        out.println(
        	new Map_Class().translate(
        		decode(request.getParameter("txt").trim())
        	)
        );
        out.println("</h1>");
        out.println("<b>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
        out.close();
	}
	
	public static String decode(String parameter) throws UnsupportedEncodingException {
        return new String(parameter.getBytes("ISO-8859-1"),"UTF8");
    }
}
