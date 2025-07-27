package servlets;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class MongoConnection {
    private static MongoClient mg = null;

    public static MongoClient getClient() {
        if (mg == null) {
           
            String uri = System.getenv("MONGO_URL");
            System.out.println("MONGO_URL: " + System.getenv("MONGO_URL"));

            if (uri == null) {
                throw new RuntimeException("MONGO_URI environment variable is not set!");
            }
            mg = new MongoClient(new MongoClientURI(uri));
        }
        return mg;
    }
}
