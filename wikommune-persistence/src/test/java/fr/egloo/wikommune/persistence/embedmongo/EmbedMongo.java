package fr.egloo.wikommune.persistence.embedmongo;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;

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
public class EmbedMongo {

	/** Nom de la base de données servant pour les tests. */
	protected static final String DATABASE_NAME = "testDB";

	/** Port du serveur Mongo embarqué lancé. */
	private static final int EMBEDDED_MONGO_PORT = 12345;

	/** Démon Mongo embarqué. */
	private MongodExecutable mongodExe;

	/** Client d'accès à la base Mongo embarquée. */
	private Mongo mongo;

	/**
	 * Exécutée avant chaque tests des classes héritants de celle-ci.
	 * 
	 * @throws IOException
	 *             Erreur d'accès à la base Mongo embarquée.
	 */
	@Before
	public final void beforeEach() throws IOException {

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
	 * Exécutée après chaque tests des classes héritants de celle-ci.
	 */
	@After
	public final void afterEach() {

		if (this.mongodExe != null) {
			this.mongo.close();
			this.mongodExe.stop();
		}
	}

	/**
	 * @return Client d'accès à la base Mongo embarquée.
	 */
	public final Mongo getMongo() {
		return mongo;
	}
}
