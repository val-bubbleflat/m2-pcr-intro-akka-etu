package m2dl.pcr.akka.lightSlack;

import akka.actor.ActorRef;

import java.io.Serializable;

public class SubscribeMessage implements Serializable {

    private ActorRef actorRef;

    public SubscribeMessage(ActorRef actorRef) {
        this.actorRef = actorRef;
    }

    public ActorRef getActorRef() {
        return actorRef;
    }
}
