package m2dl.pcr.akka.lightSlack;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MessageList implements Serializable {

    private List<String> messages = new ArrayList<String>();

    public MessageList(List<String> messages) {
        this.messages = messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public List<String> getMessages() {
        return messages;
    }
}
