package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


/**
 * Servlet implementation class publicPostServlet
 */
@WebServlet("/publicpost")
public class publicPostServlet extends HttpServlet {
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MongoClient mg = MongoConnection.getClient();
		MongoDatabase db = mg.getDatabase("servlet_demo");
		MongoCollection mgc = db.getCollection("posts");
		Document query = new Document().append("visibility", "public");
		FindIterable<Document> posts = mgc.find(query);
		List<Document> l = new ArrayList<>();
		for(Document d : posts)
		{
			l.add(d);
		}
		request.setAttribute("posts", l);
		request.getRequestDispatcher("/publicpost.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
