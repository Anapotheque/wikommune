package fr.egloo.wikommune.persistence;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

/**
 * Classe simple de test permettant de vérifier l'accès aux données sur la base
 * Mongo embarquée.
 * 
 * @author cedric
 * 
 */
public class SimpleMongoTest extends EmbeddedMongo {

	/**
	 * Méthode simple de test d'utilisation de Mongo : création d'une table,
	 * ajout d'un enregistrement dans celle-ci et vérification que le nombre
	 * d'enregistrements dans la table vaut 1.
	 */
	@Test
	public final void shouldCreateNewObjectInEmbeddedMongoDb() {

		DB db = getMongo().getDB(DATABASE_NAME);
		DBCollection col = db.createCollection("testCollection",
				new BasicDBObject());
		col.save(new BasicDBObject("testDoc", new Date()));
		assertEquals(col.getCount(), 1L);
	}
}
