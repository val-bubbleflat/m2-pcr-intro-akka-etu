package m2dl.pcr.akka.lightSlack;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import java.util.ArrayList;
import java.util.List;

public class Client extends UntypedActor {

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    private ActorRef serverRef;

    private List<String> messages = new ArrayList<String>();

    private IHM ihm;

    public Client(ActorRef serverRef, IHM ihm) {
        this.serverRef = serverRef;
        this.ihm = ihm;
        serverRef.tell(new SubscribeMessage(getSelf()), null);
    }

    public void onReceive(Object o) throws Exception {
        if(o instanceof String){
            log.info("SEND MESSAGE : " + o);
            serverRef.tell(new NewMessage((String)o), null);
        }else if(o instanceof MessageList){
            log.info("RECEIVE MESSAGE : " + ((MessageList)o).getMessages());
            messages = ((MessageList)o).getMessages();
            ihm.setMessages(messages);
        }else{
            unhandled(o);
        }
    }
}
