package m2dl.pcr.akka.lightSlack;

import java.io.Serializable;

public class NewMessage implements Serializable {

    private String message;

    public NewMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
