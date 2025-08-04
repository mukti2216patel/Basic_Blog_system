package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@WebServlet("/deletepost")
public class deletePostServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		HttpSession session = request.getSession(false);
		String username = (String) session.getAttribute("username");
		MongoClient mg = MongoConnection.getClient();
		MongoDatabase db = mg.getDatabase("servlet_demo");
		MongoCollection mgc = db.getCollection("posts");
		Document query = new Document("_id" , new ObjectId(id));
		Document doc = (Document) mgc.findOneAndDelete(query);
		if (doc != null) {
            System.out.println("Post deleted successfully: " + doc.toJson());
        } else {
            System.out.println("No post found with the specified ID.");
        }
		response.sendRedirect(request.getContextPath() + "/dashboard");
	}

}
