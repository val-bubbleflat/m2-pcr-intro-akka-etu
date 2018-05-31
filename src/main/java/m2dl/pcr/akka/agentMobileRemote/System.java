package m2dl.pcr.akka.agentMobileRemote;

import akka.actor.*;
import akka.remote.RemoteScope;
import com.typesafe.config.ConfigFactory;
import m2dl.pcr.akka.agentMobile.AgentMobile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class System {

    public static final Logger log = LoggerFactory.getLogger(System.class);

    public static void main(String... args) throws Exception {

        final ActorSystem system = ActorSystem.create("fake");

        final Address paris = AddressFromURIString.parse("akka.tcp://sys@host:2556");
        final Address marseille = AddressFromURIString.parse("akka.tcp://sys@host:2557");
        final Address lille = AddressFromURIString.parse("akka.tcp://sys@host:2558");

        ActorRef actorRef = system.actorOf(Props.create(AgentMobileRemote.class).withDeploy(new Deploy(new RemoteScope(paris))));

        actorRef.tell(paris, null);
        actorRef.tell(lille, null);

        Thread.sleep(5000);
        log.debug("Actor System Shutdown Starting...");
    }

}
