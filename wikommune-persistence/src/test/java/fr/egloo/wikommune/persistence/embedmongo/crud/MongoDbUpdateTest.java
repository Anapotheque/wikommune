package fr.egloo.wikommune.persistence.embedmongo.crud;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

import fr.egloo.wikommune.persistence.embedmongo.EmbedMongo;

//@formatter:off
/**
 * 
 * Différentes manières de mise à jour des 3 documents suivants dans une 
 * collection MongoDb.
 * 
 * { "hosting" : "hostA", "type" : "vps", "clients" : 1000 }, 
 * { "hosting" : "hostB", "type" : "dedicated server", "clients" : 100 }, 
 * { "hosting" : "hostC", "type" : "vps", "clients" : 900 }
 * 
 * @author cedric
 */
//@formatter:on
public class MongoDbUpdateTest extends EmbedMongo {

	/** Constante valeur test. */
	private static final int VALEUR_100 = 100;

	/** Constante valeur test. */
	private static final int VALEUR_110 = 110;

	/** Constante valeur test. */
	private static final int VALEUR_10 = 10;

	@Override
	protected final void insertData() {

		getDbCollection().insert(
				(DBObject) JSON
						.parse("{\"hosting\" : \"hostA\", \"type\" : \"vps\","
								+ "\"clients\" : 1000 }"));
		getDbCollection().insert(
				(DBObject) JSON.parse("{ \"hosting\" : \"hostB\", "
						+ "\"type\" : \"dedicated server\","
						+ "\"clients\" : 100 }"));
		getDbCollection().insert(
				(DBObject) JSON
						.parse("{ \"hosting\" : \"hostC\", \"type\" : \"vps\","
								+ "\"clients\" : 900 }"));
	}

	/**
	 * Test de mise à jour MongoDB avec $set. Mise à jour du document
	 * "hosting:hostB" en remplaçant la valeur clients:100 par clients:110.
	 */
	@Test
	public final void testUpdateSet() {

		BasicDBObject newDocument = new BasicDBObject().append("$set",
				new BasicDBObject().append("clients", VALEUR_110));

		BasicDBObject searchQuery = new BasicDBObject().append("hosting",
				"hostB");

		assertEquals(Integer.valueOf(VALEUR_100),
				getDbCollection().findOne(searchQuery).get("clients"));

		getDbCollection().update(searchQuery, newDocument);

		assertEquals(Integer.valueOf(VALEUR_110),
				getDbCollection().findOne(searchQuery).get("clients"));
	}

	/**
	 * Test de mise à jour MongoDB avec $inc. Mise à jour du document
	 * "hosting:hostB" en ajoutant 10 à la valeur clients:100 : clients:100 -->
	 * clients:110.
	 */
	@Test
	public final void testUpdateInc() {

		BasicDBObject newDocument = new BasicDBObject().append("$inc",
				new BasicDBObject().append("clients", VALEUR_10));

		BasicDBObject searchQuery = new BasicDBObject().append("hosting",
				"hostB");

		assertEquals(Integer.valueOf(VALEUR_100),
				getDbCollection().findOne(searchQuery).get("clients"));

		getDbCollection().update(searchQuery, newDocument);

		assertEquals(Integer.valueOf(VALEUR_110),
				getDbCollection().findOne(searchQuery).get("clients"));
	}

	/**
	 * Test de mise à jour MongoDB avec Multi. Mise à jour de tous les documents
	 * type:vps pour leur affecter la valeur clients:888.
	 */
	@Test
	public final void testUpdateMulti() {

		BasicDBObject updateQuery = new BasicDBObject().append("$set",
				new BasicDBObject().append("clients", "888"));

		BasicDBObject searchQuery = new BasicDBObject().append("type", "vps");

		assertNotEquals("888",
				getDbCollection().find(searchQuery).next().get("clients"));

		getDbCollection().updateMulti(searchQuery, updateQuery);

		assertEquals("888",
				getDbCollection().find(searchQuery).next().get("clients"));
	}
}
