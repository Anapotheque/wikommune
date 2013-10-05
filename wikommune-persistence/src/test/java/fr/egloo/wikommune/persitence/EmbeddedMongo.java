package fr.egloo.wikommune.persitence;

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

public class EmbeddedMongo {

	protected static final String DATABASE_NAME = "embedded";

	private MongodExecutable mongodExe;
	protected Mongo mongo;

	@Before
	public void beforeEach() throws Exception {

		int port = 12345;
		IMongodConfig mongodConfig = new MongodConfigBuilder()
				.version(Version.Main.PRODUCTION)
				.net(new Net(port, Network.localhostIsIPv6())).build();

		MongodStarter runtime = MongodStarter.getDefaultInstance();

		MongodExecutable mongodExecutable = null;

		mongodExecutable = runtime.prepare(mongodConfig);
		mongodExecutable.start();
		mongo = new MongoClient("localhost", port);
	}

	@After
	public void afterEach() throws Exception {

		if (this.mongodExe != null) {
			this.mongodExe.stop();
		}
	}
}
