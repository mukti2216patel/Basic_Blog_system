package servlets;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.*;

@ApplicationScoped
public class MongoService {
	
	private MongoDatabase getDB()
	{
			return MongoConnection.getClient().getDatabase("servlet_demo");
	}
	private MongoCollection<Document> getCollection() {
	    return getDB().getCollection("posts");
	}

	private Document getPostById(String id) {
	    return getCollection().find(new Document("_id", new ObjectId(id))).first();
	}

	private void updatePost(String id, String title, String content) {
	    Document updatedDoc = new Document("title", title).append("content", content);
	    getCollection().updateOne(new Document("_id", new ObjectId(id)), new Document("$set", updatedDoc));
	}
	
	public void addPost(String title , String content , String username , String visibility)
	{
		MongoCollection<Document> posts = getDB().getCollection("posts");

        Document doc = new Document("title", title)
                            .append("content", content)
                            .append("author", username)
                            .append("visibility", visibility);

        posts.insertOne(doc);
	}
	
	public List<Document> getUserPosts(String username) {
        MongoCollection<Document> posts = getDB().getCollection("posts");

        FindIterable<Document> cursor = posts.find(new Document("author", username));
        List<Document> result = new ArrayList<>();
        for (Document doc : cursor) {
            result.add(doc);
        }
        return result;
    }

}
