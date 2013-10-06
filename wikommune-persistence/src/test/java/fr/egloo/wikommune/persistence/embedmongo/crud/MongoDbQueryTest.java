package fr.egloo.wikommune.persistence.embedmongo.crud;

import static org.junit.Assert.assertEquals;

import org.bson.types.ObjectId;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import fr.egloo.wikommune.persistence.embedmongo.EmbedMongo;

//@formatter:off
/**
 * 
 * Différentes manières de recherche dans les enregistrements suivants dans
 * MongoDb.
 * @formatter:off
 * { "_id" : { "$oid" : "id"} , "number" : 1 , "name" : "mkyong-1"} 
 * { "_id" : { "$oid" : "id"} , "number" : 2 , "name" : "mkyong-2"} 
 * { "_id" : { "$oid" : "id"} , "number" : 3 , "name" : "mkyong-3"} 
 * { "_id" : { "$oid" : "id"} , "number" : 4 , "name" : "mkyong-4"} 
 * { "_id" : { "$oid" : "id"} , "number" : 5 , "name" : "mkyong-5"}
 * @formatter:on
 * @author cedric
 */
// @formatter:on
public class MongoDbQueryTest extends EmbedMongo {

	/** Nom de la table servant pour les tests. */
	private static final String TABLE_NAME = "testCollection";

	/** Nombre d'enregistrement dans la table de test. */
	private static final int COUNT_ENREGS = 5;

	/** Objet représentant la table de test. */
	private DBCollection dbCollection;

	/**
	 * Collection associée à la table.
	 */
	private void initCollection() {

		this.dbCollection = getMongo().getDB(DATABASE_NAME).createCollection(
				TABLE_NAME, new BasicDBObject());

		for (int i = 1; i <= COUNT_ENREGS; i++) {
			//@formatter:off
			dbCollection.insert(BasicDBObjectBuilder.start()
				.add("_id",	new BasicDBObject().put("$oid",	ObjectId.get()))
				.add("number", i)
				.add("name", "mkyong-" + i).get());
			// @formatter:on
		}
	}

	/**
	 * Récupération du premier enregistrement de la table.
	 */
	@Test
	public final void testQueryFindOne() {
		initCollection();
		DBObject doc = dbCollection.findOne();
		assertEquals("mkyong-1", doc.get("name"));
	}
}
