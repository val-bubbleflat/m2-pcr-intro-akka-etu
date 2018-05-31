package m2dl.pcr.akka.agentMobile;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Procedure;

public class AgentMobile extends UntypedActor {

    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    private ActorRef forwardActor;

    private Procedure<Object> changePlace = new Procedure<Object>() {
        public void apply(Object msg) throws Exception {
            if (msg instanceof ActorSystem) {
                move((ActorSystem)msg);
                getContext().become(forward,true);
            } else {
                unhandled(msg);
            }
        }
    };

    private Procedure<Object> forward = new Procedure<Object>() {
        public void apply(Object msg) throws Exception {
            forwardActor.tell(msg, getSender());
        }
    };

    private void move(ActorSystem system){
        log.info("I move to new Place : " + system.name());
        forwardActor = system.actorOf(Props.create(AgentMobile.class));
    }

    public void onReceive(Object o) throws Exception {
        changePlace.apply(o);
    }
}
