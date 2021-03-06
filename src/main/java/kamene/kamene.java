package kamene;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/stones")
public class kamene extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		HttpSession session = req.getSession();
		Fieldk fieldk = (Fieldk) session.getAttribute("fieldk");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>");
		out.println("Stones");
		out.println("</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<center>");
		if (fieldk == null || "new".equals(req.getParameter("move"))) {
			fieldk = new Fieldk(4, 4);
			session.setAttribute("fieldk", fieldk);
		} else {
			if (req.getParameter("move") != null) {
				fieldk.move(req.getParameter("move").toUpperCase());
			}
		}
		if(fieldk.getState()==GameState.SOLVED){
			session.setAttribute("fieldk", null);
			out.println("Vyhral si!<br>");
		}
		for (int row = 0; row < fieldk.getRowCount(); row++) {
			for (int column = 0; column < fieldk.getColumnCount(); column++) {
				Number number = fieldk.getNumber(row, column);
					out.print("<img src=\"resources/npuzzle/" + number.getValue() + ".png\" alt=\"sdf\"/>");
			}
			out.println("<br>");
		}

		out.println("<br>");
		out.println("<br>");
		out.println("<table border=\"0\">");
		out.println("<tr>");
		out.println("<td>");
		out.println("</td>");
		out.println("<td>");
		out.print("<a href=\"?move=up&path=stones\"><img src=\"resources/npuzzle/up.png\" alt=\"sdf\"/></a>");
		out.println("</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>");
		out.print("<a href=\"?move=left&path=stones\"><img src=\"resources/npuzzle/left.png\" alt=\"sdf\"/></a>");
		out.println("</td>");
		out.println("<td>");
		out.print("<a href=\"?move=down&path=stones\"><img src=\"resources/npuzzle/down.png\" alt=\"sdf\"/></a>");
		out.println("</td>");
		out.println("<td>");
		out.print("<a href=\"?move=right&path=stones\"><img src=\"resources/npuzzle/right.png\" alt=\"sdf\"/></a>");
		out.println("</td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("<a href=\"?move=new&path=stones\">New Game</a>");
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
	}
}
