package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/dashboard")
public class dashboardServlet extends HttpServlet {

	@Inject
	MongoService mongoService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     
        HttpSession session = req.getSession(false); 
        String username = null;
        if (session != null) {
            username = (String) session.getAttribute("username");
        }

        req.setAttribute("posts", mongoService.getUserPosts(username));
      
        req.setAttribute("username", username);

        req.getRequestDispatcher("dashboard.jsp").forward(req, resp);
    }
}
