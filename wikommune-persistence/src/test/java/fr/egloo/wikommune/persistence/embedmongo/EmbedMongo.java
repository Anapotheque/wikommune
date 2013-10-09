package fr.egloo.wikommune.persistence.embedmongo;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

/**
 * Super classe des tests nécessitants un accès aux données sur Mongo. Elle
 * lance le démon de démarrage d'un Mongo embarqué au début de chaques tests.
 * Elle termine le démon après chaque test.
 * 
 * @author cedric
 */
public abstract class EmbedMongo {

	/** Nom de la base de données servant pour les tests. */
	private static final String DATABASE_NAME = "testDB";

	/** Port du serveur Mongo embarqué lancé. */
	private static final int EMBEDDED_MONGO_PORT = 12345;

	/** Nom de la table servant pour les tests. */
	private static final String COLLECTION_NAME = "testCollection";

	/** Démon Mongo embarqué. */
	private static MongodExecutable mongodExe;

	/** Client d'accès à la base Mongo embarquée. */
	private static Mongo mongo;

	/** Objet représentant la table de test. */
	private DBCollection dbCollection;

	/**
	 * Démarrage du service Mongo et connexion à la base testDB. Exécuté au
	 * début de chaque classe de tests héritants de cette classe.
	 * 
	 * @throws IOException
	 *             Erreur d'accès à la base Mongo embarquée.
	 */
	@BeforeClass
	public static final void initTestClass() throws IOException {

		int port = EMBEDDED_MONGO_PORT;
		IMongodConfig mongodConfig = new MongodConfigBuilder()
				.version(Version.Main.PRODUCTION)
				.net(new Net(port, Network.localhostIsIPv6())).build();

		MongodStarter runtime = MongodStarter.getDefaultInstance();

		mongodExe = runtime.prepare(mongodConfig);
		mongodExe.start();
		mongo = new MongoClient("localhost", port);
	}

	/**
	 * Déconnexion à la base de testDB et arrêt du service Mongo. Exécuté à la
	 * fin de chaque tests héritants de cette classe.
	 */
	@AfterClass
	public static final void closeTestClass() {

		if (mongodExe != null) {
			mongo.close();
			mongodExe.stop();
		}
	}

	/**
	 * Réinitialisation de la collection servant pour les test au début de
	 * chacun d'eux afin de garantir une isolation de chacun d'entre eux.
	 */
	@Before
	public final void initCollection() {
		dbCollection = mongo.getDB(DATABASE_NAME)
				.getCollection(COLLECTION_NAME);
		if (dbCollection == null) {
			dbCollection = mongo.getDB(DATABASE_NAME).createCollection(
					COLLECTION_NAME, new BasicDBObject());
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
