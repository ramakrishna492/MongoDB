import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.Scanner;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongo.exception.DatabaseConnectivityFailedExcepion;
import com.mongo.exception.DeleteFailedException;
import com.mongo.exception.InsertFailedException;
import com.mongo.exception.UpdateFailedException;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;

public class Insert {
	final static Logger logger = Logger.getLogger(Insert.class);

	public static void main(String args[]) throws DatabaseConnectivityFailedExcepion, DeleteFailedException, InsertFailedException, UpdateFailedException
	{
		MongoCollection<Document> collection = null;
		MongoDatabase database = null;
		MongoClient mongo = null;
		try {
			mongo = new MongoClient("localhost", 27017);
			database = mongo.getDatabase("myDb");
			collection = database.getCollection("sampleCollection");
			logger.info("connected to db and selected collection ");
		} catch (Exception e) {
			throw new DatabaseConnectivityFailedExcepion("errror" +e);
		}

		logger.info("This is for adding data ");
		System.out.println("select one");
		System.out.println("1.insert, 2.Delete, 3.update");
		Scanner in = new Scanner(System.in);
		int num = in.nextInt();
		switch (num) {
		case 1:
			try {
				Document document = new Document("title", "MongoDB").append("id", 2)
						.append("description", "Mango db learning meterials one or two").append("likes", 100)
						.append("by", "tBusiness intelli");
				collection.insertOne(document);
				System.out.println("Document inserted successfully");
			} catch (Exception e1) {
				throw new InsertFailedException("Insertion Failed"+e1);
				
			}
			break;
		case 2:
			try {
				Document document1 = new Document("title", "MongoDB");
				collection.drop();
				logger.info("Deleted successfully ");
				System.out.println("Deleted successfully");
			} catch (Exception e) {
				throw new DeleteFailedException("Delete Failed"+e);
			}
			break;
		case 3:
			try {
				Document document3 = new Document("title", "MongoDB");
				MongoCollection<Document> collection1 = database.getCollection("sampleCollection");
				Bson filter = new Document("id", 2);
				Bson newValue = new Document("likes", 900);
				Bson updateOperationDocument = new Document("$set", newValue);
				collection1.updateOne(filter, updateOperationDocument);
				mongo.close();
				logger.info("Updated successfully ");
				System.out.println("update successfully");
			} catch (Exception e) {
				throw new UpdateFailedException("Update Failed"+e);
			}
			break;
		default:
			System.out.println("Select one amoung them");
		}

	}
}