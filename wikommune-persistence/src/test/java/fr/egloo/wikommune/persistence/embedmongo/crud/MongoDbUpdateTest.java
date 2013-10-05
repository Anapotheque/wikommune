package fr.egloo.wikommune.persistence.embedmongo.crud;

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

	/** Objet représentant la table de test. */
	private DBCollection dbCollection;

	/**
	 * Collection associée à la table private DBCollection dbCollection.
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
	 * Test de mise à jour MongoDB avec $set.
	 */
	@Test
	public final void testUpdateSet() {
		initCollection();
		// TODO
	}

	/**
	 * Test de mise à jour MongoDB avec $inc.
	 */
	@Test
	public final void testUpdateInc() {
		initCollection();
		// TODO
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
