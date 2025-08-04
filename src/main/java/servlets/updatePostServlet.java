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

@WebServlet("/updatepost")
public class updatePostServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String id = request.getParameter("id");
		ObjectId objectId = new ObjectId(id);

		MongoClient mg = MongoConnection.getClient();
		MongoDatabase db = mg.getDatabase("servlet_demo");
		MongoCollection<Document> mgc = db.getCollection("posts");
		
		 Document post = mgc.find(new Document("_id", objectId)).first();
		request.setAttribute("post", post);
		request.getRequestDispatcher("updatepost.jsp").forward(request , response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String id = request.getParameter("id");
	    String title = request.getParameter("title");
	    String content = request.getParameter("content");

	    if (id == null || id.isEmpty() || title == null || title.isEmpty() || content == null || content.isEmpty()) {
	        request.setAttribute("error", "All fields are required.");
	        doGet(request, response);  
	        return;
	    }

	    ObjectId objectId;
	    try {
	        objectId = new ObjectId(id);
	    } catch (IllegalArgumentException e) {
	        request.setAttribute("error", "Invalid post ID.");
	        doGet(request, response);
	        return;
	    }

	    MongoClient mg = MongoConnection.getClient();
	    MongoDatabase db = mg.getDatabase("servlet_demo");
	    MongoCollection<Document> mgc = db.getCollection("posts");

	    Document updateDoc = new Document()
	            .append("title", title)
	            .append("content", content);

	    Document query = new Document("_id", objectId);
	    Document update = new Document("$set", updateDoc);

	    mgc.updateOne(query, update);

	    response.sendRedirect(request.getContextPath() + "/dashboard");
	}

}
