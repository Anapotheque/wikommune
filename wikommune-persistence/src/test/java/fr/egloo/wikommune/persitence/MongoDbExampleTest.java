package fr.egloo.wikommune.persitence;

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
			DB xebiaDB = mongo.getDB("xebia");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}
