package m2dl.pcr.akka.agentMobileRemote;

import akka.actor.ActorSystem;
import com.typesafe.config.ConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LaunchMarseille {

    public static final Logger log = LoggerFactory.getLogger(LaunchMarseille.class);

    public static void main(String... args) throws Exception {

        final ActorSystem actorSystem = ActorSystem.create("Marseille", ConfigFactory.load(("marseille")));

        log.info("start Marseille");
    }

}
