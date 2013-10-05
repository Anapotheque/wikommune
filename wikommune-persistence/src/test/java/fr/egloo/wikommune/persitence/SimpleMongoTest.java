package fr.egloo.wikommune.persitence;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

public class SimpleMongoTest extends EmbeddedMongo {
	
	@Test
	public void shouldCreateNewObjectInEmbeddedMongoDb() {

		DB db = mongo.getDB(DATABASE_NAME);
		DBCollection col = db.createCollection("testCollection",
				new BasicDBObject());
		col.save(new BasicDBObject("testDoc", new Date()));
		assertEquals(col.getCount(), 1L);
	}
}
