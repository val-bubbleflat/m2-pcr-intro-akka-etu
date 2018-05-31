package m2dl.pcr.akka.agentMobileRemote;

import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Procedure;
import akka.remote.RemoteScope;

public class AgentMobileRemote extends UntypedActor {

    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    private ActorRef forwardActor;

    private Procedure<Object> changePlace = new Procedure<Object>() {
        public void apply(Object msg) throws Exception {
            if (msg instanceof Address) {
                move((Address) msg);
                getContext().become(forward,true);
            } else if(msg instanceof String){
                log.info("I'm in " + getContext().system().name());
            }else{
                unhandled(msg);
            }
        }
    };

    private Procedure<Object> forward = new Procedure<Object>() {
        public void apply(Object msg) throws Exception {
            forwardActor.tell(msg, getSender());
        }
    };

    private void move(Address address){
        log.info("I move to new Place : " + address);
        forwardActor = getContext().actorOf(Props.create(AgentMobileRemote.class).withDeploy(new Deploy(new RemoteScope(address))));
    }

    public void onReceive(Object o) throws Exception {
        changePlace.apply(o);
    }
}
