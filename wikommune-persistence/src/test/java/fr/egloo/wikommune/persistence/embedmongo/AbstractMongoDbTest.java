package fr.egloo.wikommune.persistence.embedmongo;

import org.junit.Before;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;

/**
 * Super classe des tests nécessitants un accès aux données sur Mongo.
 * 
 * @author cedric
 */
public abstract class AbstractMongoDbTest {

	/** Nom de la base de données servant pour les tests. */
	private static final String DATABASE_NAME = "testDB";

	/** Nom de la table servant pour les tests. */
	private static final String COLLECTION_NAME = "testCollection";

	/** Objet représentant la table de test. */
	private DBCollection dbCollection;

	/**
	 * Réinitialisation de la collection servant pour les test au début de
	 * chacun d'eux afin de garantir une isolation de chacun d'entre eux.
	 */
	@Before
	public final void initCollection() {
		dbCollection = EmbedMongo.getClient().getDB(DATABASE_NAME)
				.getCollection(COLLECTION_NAME);
		if (dbCollection == null) {
			dbCollection = EmbedMongo.getClient().getDB(DATABASE_NAME)
					.createCollection(COLLECTION_NAME, new BasicDBObject());
		} else {
			dbCollection.drop();
		}
		insertData();
	}

	/**
	 * Ajout à la collection des données nécessaires aux tests.
	 */
	protected abstract void insertData();

	/**
	 * Accès à la collection de test.
	 * 
	 * @return Collection de test.
	 */
	protected final DBCollection getDbCollection() {
		return dbCollection;
	}
}
