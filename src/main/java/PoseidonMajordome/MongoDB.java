package PoseidonMajordome;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.MongoClientSettings;
import com.mongodb.ConnectionString;
import com.mongodb.ServerAddress;
import com.mongodb.MongoCredential;

import java.util.Arrays;

/**
 *
 * @author Laura Becognee aka Aqueuse
 */
// connect, read, write and search in local mongoDB server
public class MongoDB {
    public static void connectDbNoAuthentication() {
        MongoClient mongoClient = MongoClients.create();
    }

    public static void connectDbWithCredentials(String user, String source, String password) {
        char[] passwordCharArray = password.toCharArray(); // the password as a character array

        MongoCredential credential = MongoCredential.createCredential(user, source, passwordCharArray);

        MongoClient mongoClient = MongoClients.create(
                MongoClientSettings.builder()
                        .applyToClusterSettings(builder
                                -> builder.hosts(Arrays.asList(new ServerAddress("host1", 27017))))
                        .credential(credential)
                        .build());
    }
}
