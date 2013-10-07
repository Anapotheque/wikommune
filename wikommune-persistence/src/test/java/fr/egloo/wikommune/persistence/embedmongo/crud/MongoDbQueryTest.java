package fr.egloo.wikommune.persistence.embedmongo.crud;

import static org.junit.Assert.assertEquals;

import org.bson.types.ObjectId;
import org.junit.Ignore;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import fr.egloo.wikommune.persistence.embedmongo.EmbedMongo;

//@formatter:off
/**
 * 
 * Différentes manières de recherche dans les enregistrements suivants dans
 * MongoDb.
 * 
 * { "_id" : { "$oid" : "id"} , "number" : 1 , "name" : "mkyong-1"} 
 * { "_id" : { "$oid" : "id"} , "number" : 2 , "name" : "mkyong-2"} 
 * { "_id" : { "$oid" : "id"} , "number" : 3 , "name" : "mkyong-3"} 
 * { "_id" : { "$oid" : "id"} , "number" : 4 , "name" : "mkyong-4"} 
 * { "_id" : { "$oid" : "id"} , "number" : 5 , "name" : "mkyong-5"}
 * 
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

	/**
	 * Récupération de tous les enregistrements de la table.
	 */
	@Test
	public final void testQueryFindAll() {

		initCollection();

		DBCursor cursor = dbCollection.find();

		assertEquals(COUNT_ENREGS, cursor.count());

		int cpt = 0;
		while (cursor.hasNext()) {
			assertEquals("mkyong-" + ++cpt, cursor.next().get("name"));
		}
	}

	/**
	 * Filtrage du résultat d'une requête
	 */
	@Test
	@Ignore
	public final void testQueryFind() {

		initCollection();

		// Quand vide ramène tous les objets
		BasicDBObject allQuery = new BasicDBObject();

		// Aucun champs "name" n'a cette valeur
		BasicDBObject fields1 = new BasicDBObject();
		fields1.put("name", 1);

		DBCursor cursor = dbCollection.find(allQuery, fields1);
		assertEquals(5, cursor.count());

		// 1 champs champs corresponds à cette valeur
		BasicDBObject fields2 = new BasicDBObject();
		fields2.put("number", 1);

		cursor = dbCollection.find(allQuery, fields2);
		assertEquals(1, cursor.count());
	}
}
