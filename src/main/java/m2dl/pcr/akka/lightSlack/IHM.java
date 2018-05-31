package m2dl.pcr.akka.lightSlack;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import m2dl.pcr.akka.cryptageRemote.LookupActor;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class IHM extends Application {

    private ActorRef client;

    private String serveurAddress = "akka.tcp://ServeurSystem@127.0.0.1:2560/user/serveur";

    private ObservableList<String> data = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        final ActorSystem actorSystem = ActorSystem.create("client" + UUID.randomUUID().toString(), ConfigFactory.load(("clients")));
        ActorRef serveurRef = actorSystem.actorOf(Props.create(LookupActor.class, serveurAddress), "serv");

        client = actorSystem.actorOf(Props.create(Client.class, serveurRef, this));

        final ListView<String> listView = new ListView<String>(data);
        listView.setPrefSize(200, 250);
        listView.setEditable(true);

        listView.setItems(data);

        StackPane root = new StackPane();
        root.getChildren().add(listView);

        final TextField textField = new TextField();
        Button button = new Button("Envoyer");
        button.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                send(textField.getText());
                textField.setText("");
            }
        });

        root.getChildren().addAll(textField,button);
        primaryStage.setScene(new Scene(root, 200, 250));

        primaryStage.show();
    }

    private void send(String message){
        client.tell(message, null);
    }

    public void setMessages(List<String> messages){
        data.setAll(messages);
    }

}
