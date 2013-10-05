package fr.egloo.wikommune.persistence;

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
public class EmbeddedMongo {

	/** Nom de la base de donnée servant pour les tests. */
	protected static final String DATABASE_NAME = "embedded";
	/** Port du serveur Mongo embarqué lancé. */
	private static final int EMBEDDED_MONGO_PORT = 12345;
	/** Démon Mongo embarqué. */
	private MongodExecutable mongodExe;
	/** Client d'accès à la base Mongo embarquée. */
	private Mongo mongo;

	/**
	 * Exécutée avant chaque tests des classes héritants de celle-ci.
	 * @throws Exception Exception lors du démarrage du démon.
	 */
	@Before
	public final void beforeEach() throws Exception {

		int port = EMBEDDED_MONGO_PORT;
		IMongodConfig mongodConfig = new MongodConfigBuilder()
				.version(Version.Main.PRODUCTION)
				.net(new Net(port, Network.localhostIsIPv6())).build();

		MongodStarter runtime = MongodStarter.getDefaultInstance();

		MongodExecutable mongodExecutable = null;

		mongodExecutable = runtime.prepare(mongodConfig);
		mongodExecutable.start();
		mongo = new MongoClient("localhost", port);
	}

	/**
	 * Exécutée après chaque tests des classes héritants de celle-ci.
	 * @throws Exception Exception lors de la fermeture du démon.
	 */
	@After
	public final void afterEach() throws Exception {

		if (this.mongodExe != null) {
			this.mongodExe.stop();
			this.mongo.close();
		}
	}

	/**
	 * @return Client d'accès à la base Mongo embarquée.
	 */
	public final Mongo getMongo() {
		return mongo;
	}
}
