package m2dl.pcr.akka.agentMobileRemote;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LaunchParis {

    public static final Logger log = LoggerFactory.getLogger(LaunchParis.class);

    public static void main(String... args) throws Exception {

        final ActorSystem actorSystem = ActorSystem.create("Paris", ConfigFactory.load(("paris")));

        log.info("start Paris");
    }

}
