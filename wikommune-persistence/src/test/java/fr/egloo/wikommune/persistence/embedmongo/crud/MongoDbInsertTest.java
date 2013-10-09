package fr.egloo.wikommune.persistence.embedmongo.crud;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

import fr.egloo.wikommune.persistence.embedmongo.AbstractMongoDbTest;

/**
 * Différentes manières d'insertion des documents suivants dans une collection
 * MongoDb.
 * 
 * { "bibliothèque" : "google play", "titre" : "La malédiction de l'anneau",
 * "detail" : { pages : 509, auteur : "Edouard Brasey", bd : "true" } } }
 * 
 * @author cedric
 */
public class MongoDbInsertTest extends AbstractMongoDbTest {

	/** Nombre de pages du livre "La malédiction de l'anneau". */
	private static final int NB_PAGES_MALEDICTION_DE_L_ANNEAU = 509;

	/**
	 * Vérification que les données passées ont bien été persistées en fin de
	 * chaque tests.
	 */
	@After
	public final void verifyCollection() {

		DBCollection dbCollectionTest = getDbCollection();

		assertNotNull(dbCollectionTest);
		assertEquals(1L, dbCollectionTest.count());
	}

	/**
	 * Test d'insertion MongoDB avec BasicDBObject.
	 */
	@Test
	public final void testInsertBasicDBObject() {

		BasicDBObject document = new BasicDBObject();
		document.put("bibliothèque", "google play");
		document.put("titre", "La malédiction de l'anneau");

		BasicDBObject documentDetail = new BasicDBObject();
		documentDetail.put("pages", NB_PAGES_MALEDICTION_DE_L_ANNEAU);
		documentDetail.put("auteur", "Edouard Brasey");
		documentDetail.put("bd", "false");

		document.put("detail", documentDetail);

		getDbCollection().insert(document);
	}

	/**
	 * Test d'insertion MongoDB avec BasicDBObjectBuilder.
	 */
	@Test
	public final void testInsertBasicDBObjectBuilder() {

		BasicDBObjectBuilder documentBuilder = BasicDBObjectBuilder.start()
				.add("bibliothèque", "google play")
				.add("titre", "La malédiction de l'anneau");

		BasicDBObjectBuilder documentBuilderDetail = BasicDBObjectBuilder
				.start().add("pages", NB_PAGES_MALEDICTION_DE_L_ANNEAU)
				.add("auteur", "Edouard Brasey").add("bd", "false");

		documentBuilder.add("detail", documentBuilderDetail.get());

		getDbCollection().insert(documentBuilder.get());
	}

	/**
	 * Test d'insertion MongoDB avec java.util.Map.
	 */
	@Test
	public final void testInsertMap() {

		Map<String, Object> documentMap = new HashMap<String, Object>();
		documentMap.put("bibliothèque", "google play");
		documentMap.put("titre", "La malédiction de l'anneau");

		Map<String, Object> documentMapDetail = new HashMap<String, Object>();
		documentMapDetail.put("pages", NB_PAGES_MALEDICTION_DE_L_ANNEAU);
		documentMapDetail.put("auteur", "Edouard Brasey");
		documentMapDetail.put("bd", "false");

		documentMap.put("detail", documentMapDetail);

		getDbCollection().insert(new BasicDBObject(documentMap));
	}

	/**
	 * Test d'insertion MongoDB avec JSON.
	 */
	@Test
	public final void testInsertJson() {

		String json = "{\"bibliothèque\" : \"google play\","
				+ "\"titre\" : \"La malédiction de l'anneau\","
				+ "\"detail\" : {\"pages\" : 509,"
				+ "\"auteur\" : \"Edouard Brasey\", \"bd\" : \"false\"}}}";

		DBObject dbObject = (DBObject) JSON.parse(json);

		getDbCollection().insert(dbObject);
	}

	@Override
	protected final void insertData() {
		// Pas de données à insérer pour les tests.
	}
}
