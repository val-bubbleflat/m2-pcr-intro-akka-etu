package m2dl.pcr.akka.lightSlack;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Serveur extends UntypedActor {

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    private List<ActorRef> clients = new ArrayList<ActorRef>();

    private List<String> messages = new ArrayList<String>();

    public void onReceive(Object o) throws Exception {
        if(o instanceof SubscribeMessage){
            subscribe((SubscribeMessage)o);
        }else if(o instanceof NewMessage){
            addMessage((NewMessage)o);
        }else{
            unhandled(o);
        }
    }

    private void addMessage(NewMessage newMessage) {
        log.info("MESSAGE : " + getSender() + "  -  " + newMessage.getMessage());
        messages.add(newMessage.getMessage());
        MessageList list = new MessageList(messages);
        for (ActorRef client: clients) {
            client.tell(list, getSender());
        }
    }

    private void subscribe(SubscribeMessage subscribeMessage) {
        log.info("SUBSCRIBE : " + subscribeMessage.toString());
        clients.add(subscribeMessage.getActorRef());
        MessageList list = new MessageList(messages);
        subscribeMessage.getActorRef().tell(list, null);
    }

}
