package servlets;

import com.mongodb.*;


public class MongoConnection {
		private static MongoClient mg= null;
		public static MongoClient getClient()
		{
			if(mg == null)
			{
				mg = new MongoClient(new MongoClientURI("mongodb+srv://mukti2216patel:2P*8m%401973@cluster0.dhhmh6h.mongodb.net/servlet_demo"));
			}
			return mg;
		}
}
