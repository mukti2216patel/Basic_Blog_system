package servlets;

import java.io.IOException;

import org.bson.Document;
import org.mindrot.jbcrypt.BCrypt;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/login")
public class loginServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    HttpSession session = req.getSession(false); 
	    if (session == null || session.getAttribute("username") == null) {
	        req.getRequestDispatcher("login.jsp").forward(req, resp);
	    } else {
	        resp.sendRedirect(req.getContextPath() + "/dashboard");
	    }
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    String email = req.getParameter("email");
	    String pwd = req.getParameter("password");
	    
	    MongoClient mg = MongoConnection.getClient();
	    MongoDatabase db = mg.getDatabase("servlet_demo");
	    
	    MongoCollection<Document> mgc = db.getCollection("users");
	    Document Userdoc = mgc.find(new Document("email", email)).first();
	    
	    if (Userdoc == null || !BCrypt.checkpw(pwd, Userdoc.getString("password"))) {
	        req.setAttribute("error", "Invalid email or password.");
	        req.getRequestDispatcher("login.jsp").forward(req, resp);
	        return;
	    }
	    
	    HttpSession session = req.getSession();
	    session.setAttribute("username", Userdoc.getString("username"));
	    session.setAttribute("email", Userdoc.getString("email"));
	    resp.sendRedirect(req.getContextPath() + "/dashboard");

	}

		
}
