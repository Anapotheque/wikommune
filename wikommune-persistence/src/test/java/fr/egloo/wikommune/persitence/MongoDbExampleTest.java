package fr.egloo.wikommune.persitence;

import static org.junit.Assert.assertNotNull;

import java.net.UnknownHostException;

import org.junit.Test;

import com.mongodb.DB;
import com.mongodb.Mongo;

public class MongoDbExampleTest {
	
	@Test
	public void testConnectionDB() {
		Mongo mongo;
		try {
			mongo = new Mongo();
			DB test = mongo.getDB("test");
			assertNotNull(test);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}
