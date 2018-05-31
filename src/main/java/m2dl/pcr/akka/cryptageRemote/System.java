package m2dl.pcr.akka.cryptageRemote;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;
import m2dl.pcr.akka.cryptage.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class System {

    public static final Logger log = LoggerFactory.getLogger(LaunchCryptage.class);

    public static void main(String... args) throws Exception {

        final ActorSystem actorSystem = ActorSystem.create("actor-system", ConfigFactory.load(("receiver")));

        //remote
        String cryptagePath = "akka.tcp://CryptageSystem@127.0.0.1:2553/user/cryptage";
        final ActorRef cryptage = actorSystem.actorOf(Props.create(LookupActor.class, cryptagePath), "cryptage");

        String erreurPath = "akka.tcp://ErreurControlleurSystem@127.0.0.1:2554/user/erreur-controlleur";
        final ActorRef erreurControlleur = actorSystem.actorOf(Props.create(LookupActor.class, erreurPath), "erreur-controlleur");

        //local
        final ActorRef receiver = actorSystem.actorOf(Props.create(Recepteur.class), "recepteur");
        final ActorRef passePlat = actorSystem.actorOf(Props.create(PassePlat.class, receiver, erreurControlleur), "passe-plat");

        //Scenario 3
        MessageWithSender messageWithSender3 = new MessageWithSender(passePlat, "Message 3");
        cryptage.tell(messageWithSender3, null);

    }

}
