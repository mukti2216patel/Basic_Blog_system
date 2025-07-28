package servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.bson.Document;
import org.mindrot.jbcrypt.BCrypt;

import com.mongodb.MongoClient;
import com.mongodb.client.*;

@WebServlet("/")
public class registerServlet extends HttpServlet{
	
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	
    	    HttpSession session = req.getSession(false); 
    	    System.out.println(session);
    	    if (session == null || session.getAttribute("username") == null) {
    	    		req.getRequestDispatcher("index.jsp").forward(req, resp);
    	    }
    	    else
    	    {
    	    	resp.sendRedirect(req.getContextPath() + "/dashboard");
    	    }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       
        System.out.println("Comeee");

        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (username == null || email == null || password == null || username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            req.setAttribute("error", "All fields are required.");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
            return;
        }

        // Check if the email is already registered
        MongoClient mg = MongoConnection.getClient();
        MongoDatabase db = mg.getDatabase("servlet_demo");
        MongoCollection<Document> mgc = db.getCollection("users");

        long count = mgc.countDocuments(new Document("email", email));
        if (count > 0) {
            req.setAttribute("error", "Email is already registered.");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
            return;
        }
        
        String hashpwd = BCrypt.hashpw(password, BCrypt.gensalt(12));
        System.out.print(hashpwd);
        Document newUser = new Document("username", username).append("email", email).append("password", hashpwd);
        mgc.insertOne(newUser);
        System.out.println(newUser);
        resp.sendRedirect(req.getContextPath() + "/login");
    }


   
	
	
}
