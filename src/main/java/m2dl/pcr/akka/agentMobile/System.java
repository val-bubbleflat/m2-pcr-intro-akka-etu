package m2dl.pcr.akka.agentMobile;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class System {

    public static final Logger log = LoggerFactory.getLogger(System.class);

    public static void main(String... args) throws Exception {

        final ActorSystem paris = ActorSystem.create("Paris");
        final ActorSystem lille = ActorSystem.create("Lille");
        final ActorSystem marseille = ActorSystem.create("Marseille");

        ActorRef actorRef = marseille.actorOf(Props.create(AgentMobile.class));

        actorRef.tell(paris, null);
        actorRef.tell(lille, null);

        Thread.sleep(5000);
        log.debug("Actor System Shutdown Starting...");

        paris.terminate();
        marseille.terminate();
        lille.terminate();

    }

}
