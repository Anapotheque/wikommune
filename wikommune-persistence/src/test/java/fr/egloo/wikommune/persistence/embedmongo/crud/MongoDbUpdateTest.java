package fr.egloo.wikommune.persistence.embedmongo.crud;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

import fr.egloo.wikommune.persistence.embedmongo.EmbedMongo;

/**
 * 
 * Différentes manières de mise à jour des 3 enregistrements suivants dans
 * MongoDb.
 * 
 * { "hosting" : "hostA", "type" : "vps", "clients" : 1000 }, { "hosting" :
 * "hostB", "type" : "dedicated server", "clients" : 100 }, { "hosting" :
 * "hostC", "type" : "vps", "clients" : 900 }
 * 
 * @author cedric
 */
public class MongoDbUpdateTest extends EmbedMongo {

	/** Nom de la table servant pour les tests. */
	private static final String TABLE_NAME = "testCollection";

	/** Constante valeur test. */
	private static final int VALEUR_100 = 100;

	/** Constante valeur test. */
	private static final int VALEUR_110 = 110;

	/** Constante valeur test. */
	private static final int VALEUR_10 = 10;

	/** Objet représentant la table de test. */
	private DBCollection dbCollection;

	/**
	 * Collection associée à la table.
	 */
	private void initCollection() {

		this.dbCollection = getMongo().getDB(DATABASE_NAME).createCollection(
				TABLE_NAME, new BasicDBObject());

		dbCollection.insert((DBObject) JSON
				.parse("{\"hosting\" : \"hostA\", \"type\" : \"vps\","
						+ "\"clients\" : 1000 }"));
		dbCollection.insert((DBObject) JSON.parse("{ \"hosting\" : \"hostB\", "
				+ "\"type\" : \"dedicated server\"," + "\"clients\" : 100 }"));
		dbCollection.insert((DBObject) JSON
				.parse("{ \"hosting\" : \"hostC\", \"type\" : \"vps\","
						+ "\"clients\" : 900 }"));
	}

	/**
	 * Test de mise à jour MongoDB avec $set. Mise à jour du document
	 * "hosting:hostB" en remplaçant la valeur clients:100 par clients:110.
	 */
	@Test
	public final void testUpdateSet() {

		initCollection();

		BasicDBObject newDocument = new BasicDBObject().append("$set",
				new BasicDBObject().append("clients", VALEUR_110));

		BasicDBObject searchQuery = new BasicDBObject().append("hosting",
				"hostB");

		assertEquals(Integer.valueOf(VALEUR_100),
				dbCollection.findOne(searchQuery).get("clients"));

		dbCollection.update(searchQuery, newDocument);

		assertEquals(Integer.valueOf(VALEUR_110),
				dbCollection.findOne(searchQuery).get("clients"));
	}

	/**
	 * Test de mise à jour MongoDB avec $inc. Mise à jour du document
	 * "hosting:hostB" en ajoutant 10 à la valeur clients:100 ; clients:100 -->
	 * clients:110
	 */
	@Test
	public final void testUpdateInc() {

		initCollection();

		BasicDBObject newDocument = new BasicDBObject().append("$inc",
				new BasicDBObject().append("clients", VALEUR_10));

		BasicDBObject searchQuery = new BasicDBObject().append("hosting",
				"hostB");

		assertEquals(Integer.valueOf(VALEUR_100),
				dbCollection.findOne(searchQuery).get("clients"));

		dbCollection.update(searchQuery, newDocument);

		assertEquals(Integer.valueOf(VALEUR_110),
				dbCollection.findOne(searchQuery).get("clients"));
	}

	/**
	 * Test de mise à jour MongoDB avec Multi.
	 */
	@Test
	public final void testUpdateMulti() {
		initCollection();
		// TODO
	}
}
