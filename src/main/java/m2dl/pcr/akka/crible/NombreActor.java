package m2dl.pcr.akka.crible;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Procedure;

import static scala.collection.concurrent.Debug.log;

public class NombreActor extends UntypedActor {

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    private int nombre;

    private ActorRef next;

    public NombreActor(int nombre) {
        log.info(nombre + " est un entier");
        this.nombre = nombre;
    }

    Procedure<Object> isLast = new Procedure<Object>() {
        public void apply(Object msg) throws Exception {
            if (msg instanceof Integer) {
                int nb = (Integer)msg;
                if(nb % nombre != 0){
                    next = getContext().actorOf(Props.create(NombreActor.class, nb), "actor-" + nb);
                    getContext().become(isNotLast,false);
                }
            } else {
                unhandled(msg);
            }
        }
    };

    Procedure<Object> isNotLast = new Procedure<Object>() {
        public void apply(Object msg) throws Exception {
            if (msg instanceof Integer) {
                int nb = (Integer)msg;
                if(nb % nombre != 0){
                    next.tell(nb, null);
                }
            } else {
                unhandled(msg);
            }
        }
    };

    public void onReceive(Object o) throws Exception {
        isLast.apply(o);
    }
}
