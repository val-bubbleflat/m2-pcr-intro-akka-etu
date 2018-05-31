package m2dl.pcr.akka.lightSlack;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LaunchServeur {

    public static final Logger log = LoggerFactory.getLogger(LaunchServeur.class);

    public static void main(String... args) throws Exception {

        final ActorSystem actorSystem = ActorSystem.create("ServeurSystem", ConfigFactory.load(("serveur")));
        final ActorRef serveur = actorSystem.actorOf(Props.create(Serveur.class), "serveur");

        log.info("start Serveur");
    }

}
