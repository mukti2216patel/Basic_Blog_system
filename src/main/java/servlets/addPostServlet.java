package servlets;

import java.io.IOException;

import org.bson.Document;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import servlets.MongoConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/addpost")
public class addPostServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession(false);
		if(session == null || session.getAttribute("username")==null)
		{
			resp.sendRedirect(req.getContextPath() + "/");
            return;
		}
		req.getRequestDispatcher("addpost.jsp").forward(req, resp);
	}	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }

        
        String title = req.getParameter("title");
        String content = req.getParameter("content");

        
        if (title == null || title.trim().isEmpty() || content == null || content.trim().isEmpty()) {
            req.setAttribute("error", "Please fill required details.");
            req.getRequestDispatcher("addpost.jsp").forward(req, resp);
            return;
        }
		
       String username =(String) session.getAttribute("username");
        
        MongoClient mg = MongoConnection.getClient();
        MongoDatabase db = mg.getDatabase("servlet_demo");
        MongoCollection<Document> mgc = db.getCollection("posts");
        
        Document doc = new Document("title" , title).append("content" , content).append("author" , username);
        
        mgc.insertOne(doc);
        
        resp.sendRedirect(req.getContextPath() + "/dashboard");

	}

}
