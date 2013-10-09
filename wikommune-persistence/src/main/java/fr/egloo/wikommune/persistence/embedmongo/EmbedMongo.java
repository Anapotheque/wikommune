package fr.egloo.wikommune.persistence.embedmongo;

import java.io.IOException;

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
 * Singleton servant à invoquer le service d'accès à la base Mongo embarquée.
 * 
 * @author cedric
 */
public final class EmbedMongo {

	/** Instance de MongoDB. */
	private static final EmbedMongo INSTANCE = new EmbedMongo();

	/** Port du serveur Mongo embarqué lancé. */
	private static final int EMBEDDED_MONGO_PORT = 12345;

	/** Host du serveur Mongo embarqué lancé. */
	private static final String EMBEDDED_MONGO_HOST = "localhost";

	/** Démon Mongo embarqué. */
	private MongodExecutable mongodExe;

	/** Client d'accès à la base Mongo embarquée. */
	private Mongo mongoClient;

	/**
	 * Invoquation de l'instance à la base EmbedMongo.
	 * 
	 * @return Instance à la base EmbedMongo.
	 */
	public static Mongo getClient() {
		return INSTANCE.mongoClient;
	}

	/**
	 * Invocation de la méthode de fermeture du service et du client de la base
	 * Mongo à l'arrêt de la JVM.
	 * 
	 * @author cedric
	 * 
	 */
	private class ShutdownThread extends Thread {
		@Override
		public void run() {
			close();
		}
	}

	/**
	 * Initialisation du service d'écoute et du client d'accès à la base Mongo
	 * embarquée.
	 */
	private EmbedMongo() {

		try {
			IMongodConfig mongodConfig = new MongodConfigBuilder()
					.version(Version.Main.PRODUCTION)
					.net(new Net(EMBEDDED_MONGO_PORT, Network.localhostIsIPv6()))
					.build();

			MongodStarter runtime = MongodStarter.getDefaultInstance();

			mongodExe = runtime.prepare(mongodConfig);
			mongodExe.start();
			mongoClient = new MongoClient(EMBEDDED_MONGO_HOST,
					EMBEDDED_MONGO_PORT);

			// Ajout du shutdown hook.
			Runtime.getRuntime().addShutdownHook(new ShutdownThread());

		} catch (IOException e) {
			close();
		}
	}

	/**
	 * Fermeture des connexions au client et au service.
	 */
	private void close() {
		if (mongodExe != null) {
			mongoClient.close();
			mongodExe.stop();
		}
	}
}
