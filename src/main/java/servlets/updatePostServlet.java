import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.bson.Document;

@WebServlet("/updatepost")
public class updatePostServlet extends HttpServlet {

    @Inject
    private MongoService mongoService;  
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id == null || id.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/dashboard");
            return;
        }

        Document post = mongoService.getPostById(id);
        if (post == null) {
            request.setAttribute("error", "Post not found.");
        } else {
            request.setAttribute("post", post);
        }
        request.getRequestDispatcher("updatepost.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        if (id == null || id.isEmpty() || title == null || title.isEmpty() || content == null || content.isEmpty()) {
            request.setAttribute("error", "All fields are required.");
            doGet(request, response);
            return;
        }

        try {
            mongoService.updatePost(id, title, content);
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", "Invalid post ID.");
            doGet(request, response);
            return;
        }

        response.sendRedirect(request.getContextPath() + "/dashboard");
    }
}
