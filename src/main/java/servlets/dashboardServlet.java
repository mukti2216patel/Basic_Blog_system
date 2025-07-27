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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }

        MongoClient mg = MongoConnection.getClient();
        MongoDatabase db = mg.getDatabase("servlet_demo");
        MongoCollection<Document> postsCollection = db.getCollection("posts");

        FindIterable<Document> postsCursor = postsCollection.find();

        List<Document> posts = new ArrayList<>();
        for (Document post : postsCursor) {
            posts.add(post);
        }

        req.setAttribute("posts", posts);

        req.getRequestDispatcher("dashboard.jsp").forward(req, resp);
    }
}
