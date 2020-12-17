package PoseidonMajordome;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

// add MongoDB attachement template => copy mongoDB access Sample
// with basic connect/read/add/remove management
public class addMongoDB {
    public static void connectDbNoAuthentication() {
        MongoClient mongoClient = new MongoClient();
    }
 
    public static void connectDbWithCredentials(String ServerName, int address, String user, String source, String password) {
        char[] passwordCharArray = password.toCharArray(); // the password as a character array
        
        MongoCredential credential = MongoCredential.createCredential(user, source, passwordCharArray);
        MongoClientOptions options = MongoClientOptions.builder().build();
        
        MongoClient mongoClient = new MongoClient( new ServerAddress(ServerName, address), credential, options);
    }
}
