package m2dl.pcr.akka.agentMobileRemote;

import akka.actor.ActorSystem;
import com.typesafe.config.ConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LaunchLille {

    public static final Logger log = LoggerFactory.getLogger(LaunchLille.class);

    public static void main(String... args) throws Exception {

        final ActorSystem actorSystem = ActorSystem.create("Lille", ConfigFactory.load(("lille")));

        log.info("start Lille");
    }

}
