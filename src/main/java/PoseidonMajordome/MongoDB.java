package PoseidonMajordome;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

public class MongoDB {
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
