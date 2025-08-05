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

	@Inject
	private MongoService mongoService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        String visibility = req.getParameter("visibility"); 
        
        if (title == null || title.trim().isEmpty() || content == null || content.trim().isEmpty() || visibility == null || visibility.trim().isEmpty()){
            req.setAttribute("error", "Please fill required details.");
            req.getRequestDispatcher("addpost.jsp").forward(req, resp);
            return;
        }
		
       String username =(String) session.getAttribute("username");
        
       mongoService.addPost(title, content, username, visibility);

        
        resp.sendRedirect(req.getContextPath() + "/dashboard");
	}

}
