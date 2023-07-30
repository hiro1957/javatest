package jp.co.sample;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Sampleapp2")
public class Sampleapp2 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<style>table,th,td,tr{border: 1px solid black};</style>");
        out.println("<body>");
        out.println("<h1>DB接続確認</h1>");
        out.println("</body>");
        out.println("</html>");

        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "test";
        String password = "test";

        try{
            Class.forName("org.postgresql.Driver");
            System.out.println("データベースの接続に成功しました");
        } catch(Exception e) {
            e.printStackTrace();
        }


        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM customer";
            ResultSet rset = stmt.executeQuery(sql);

            out.println("<table>");
            out.println("<tr><th>ID</th><th>UserName</th><th>Email</th></tr>");

            while(rset.next()) {
                out.println("<tr><td>" + rset.getString("id") + "</td><td>" + rset.getString("name") + "</td><td>" + rset.getString("email") + "</td></tr>");
            }

            out.println("</table>");

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }


    }
}
