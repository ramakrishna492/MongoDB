
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;

public class Mongo {

	public static void main(String args[]) throws Exception
	{
		try {
			
			MongoClient mongo = new MongoClient("localhost", 27017);
			MongoDatabase database = mongo.getDatabase("myDb");
			
			System.out.println("Connected to the database successfully");
		} catch (Exception e) {
		
			e.printStackTrace();
			System.out.println("error");
		}
		System.out.println("Done with the program");
	}
}
