package fr.egloo.wikommune.persistence.embedmongo.crud;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.bson.types.ObjectId;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import fr.egloo.wikommune.persistence.embedmongo.AbstractMongoDbTest;

//@formatter:off
/**
 * 
 * Différentes manières de recherche dans les documents suivants dans
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
public class MongoDbQueryTest extends AbstractMongoDbTest {

	/**
	 * Valeur des données de tests.
	 * 
	 * @author cedric
	 */
	private enum NumberDoc {
		/** Valeur 1. */
		VAL_1(1),
		/** Valeur 2. */
		VAL_2(2),
		/** Valeur 3. */
		VAL_3(3),
		/** Valeur 4. */
		VAL_4(4),
		/** Valeur 5. */
		VAL_5(5);

		/** Valeur entière de l'enum. */
		private final int valeur;

		/**
		 * Constucteur de l'enum.
		 * 
		 * @param val
		 *            Valeur entière de l'enum.
		 */
		private NumberDoc(final int val) {
			this.valeur = val;
		}

		/**
		 * Retourne la valeur entière de l'enum.
		 * 
		 * @return Valeur entière de l'enum.
		 */
		public int getValeur() {
			return valeur;
		}
	}

	@Override
	protected final void insertData() {

		for (int i = 1; i <= NumberDoc.values().length; i++) {
			//@formatter:off
			getDbCollection().insert(BasicDBObjectBuilder.start()
				.add("_id",	new BasicDBObject().put("$oid",	ObjectId.get()))
				.add("number", i)
				.add("name", "mkyong-" + i).get());
			// @formatter:on
		}
	}

	/**
	 * Récupération du premier document de la collection.
	 */
	@Test
	public final void testQueryFindOneDocument() {

		DBObject doc = getDbCollection().findOne();
		assertEquals("mkyong-1", doc.get("name"));
	}

	/**
	 * Récupération de tous les documents de la collection.
	 */
	@Test
	public final void testQueryFindAllDocuments() {

		DBCursor cursor = getDbCollection().find();
		assertEquals(NumberDoc.values().length, cursor.count());

		int cpt = 0;
		while (cursor.hasNext()) {
			assertEquals("mkyong-" + ++cpt, cursor.next().get("name"));
			assertEquals(cpt, cursor.curr().get("number"));
		}
	}

	/**
	 * Récupération seulement du champs demandé dans le produit de la requête au
	 * lieu de l'intégralité du document. Ici on ramène seulement le champs
	 * "name" pour tous les documents obtenus dans la requête.
	 */
	@Test
	public final void testQueryFindSingleField() {

		// Quand vide ramène tous les documents
		BasicDBObject allQuery = new BasicDBObject();

		// On veut uniquement le champs "name", pas l'intégralité du document.
		BasicDBObject fields = new BasicDBObject();
		fields.put("name", 1);

		DBCursor cursor = getDbCollection().find(allQuery, fields);
		assertEquals(NumberDoc.values().length, cursor.count());

		int cpt = 0;
		while (cursor.hasNext()) {
			assertEquals("mkyong-" + ++cpt, cursor.next().get("name"));
			assertNull(cursor.curr().get("number"));
		}
	}

	/**
	 * Récupération de tous les documents 'where' "number":4 .
	 */
	@Test
	public final void testQueryFindWhere() {

		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("number", NumberDoc.VAL_4.getValeur());
		DBCursor cursor = getDbCollection().find(whereQuery);

		assertEquals(1, cursor.count());
		assertEquals("mkyong-4", cursor.next().get("name"));
	}

	/**
	 * Récupération de tous les documents 'in' "number":2,"number":4,"number":5.
	 * 
	 */
	@Test
	public final void testQueryFindIn() {

		BasicDBObject inQuery = new BasicDBObject();
		List<Integer> list = Arrays.asList(NumberDoc.VAL_2.getValeur(),
				NumberDoc.VAL_4.getValeur(), NumberDoc.VAL_5.getValeur());
		inQuery.put("number", new BasicDBObject("$in", list));
		DBCursor cursor = getDbCollection().find(inQuery);

		assertEquals(NumberDoc.VAL_3.getValeur(), cursor.count());
		while (cursor.hasNext()) {
			assertTrue(list.contains(cursor.next().get("number")));
		}
	}

	/**
	 * Recherche avec champs plus petit que, plus grand que (lt, gt). Dans notre
	 * test, on ramène tous les documents dont le champs : 5 > number > 2.
	 */
	@Test
	public final void testQueryFindLtGt() {

		DBObject ltgtQuery = BasicDBObjectBuilder
				.start()
				.add("number",
						new BasicDBObject("$gt", NumberDoc.VAL_2.getValeur())
								.append("$lt", NumberDoc.VAL_5.getValeur()))
				.get();
		DBCursor cursor = getDbCollection().find(ltgtQuery);

		assertEquals(NumberDoc.VAL_2.getValeur(), cursor.count());
		while (cursor.hasNext()) {
			assertTrue(Arrays.asList(NumberDoc.VAL_3.getValeur(),
					NumberDoc.VAL_4.getValeur()).contains(
					cursor.next().get("number")));
		}
	}

	/**
	 * Recherche avec champs différent de (not equals). Dans notre test, on
	 * ramène tous les documents dont le champs number est différent de 4.
	 */
	@Test
	public final void testQueryFindNe() {

		BasicDBObject neQuery = new BasicDBObject();
		neQuery.put("number",
				new BasicDBObject("$ne", NumberDoc.VAL_4.getValeur()));
		DBCursor cursor = getDbCollection().find(neQuery);

		assertEquals(NumberDoc.VAL_4.getValeur(), cursor.count());
		while (cursor.hasNext()) {
			assertNotEquals(NumberDoc.VAL_4, cursor.next());
			assertTrue(Arrays.asList(NumberDoc.VAL_1.getValeur(),
					NumberDoc.VAL_2.getValeur(), NumberDoc.VAL_3.getValeur(),
					NumberDoc.VAL_5.getValeur()).contains(
					cursor.curr().get("number")));
		}
	}
}
