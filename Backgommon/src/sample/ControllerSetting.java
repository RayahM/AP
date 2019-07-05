package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerSetting implements Initializable {

    private String[] playerTimeNumbers = {"1","2","3","4","5"};
    private String[] roundsNumbers = {"1","3","5","7","11"};

    @FXML
    private ChoiceBox<String> playerTime;

    @FXML
    private ChoiceBox<String> rounds;



    @FXML
    void backToMain(ActionEvent event)throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("scene1.fxml"));
        Parent mainParent = loader.load() ;
        ControllerScene1 scene1 = loader.getController();
        scene1.setPlayerTime(this.playerTime.getValue());
        scene1.setRounds(this.rounds.getValue());
        Scene mainScene = new Scene(mainParent);
        Stage mainWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        mainWindow.setScene(mainScene);
        mainWindow.show();
    }







    @Override
    public void initialize(URL location, ResourceBundle resources) {

        playerTime.setItems(FXCollections.observableArrayList(playerTimeNumbers));
        rounds.setItems(FXCollections.observableArrayList(roundsNumbers));

    }



    public void initValues(String time,String round){

       this.playerTime.setValue(time);
       this.rounds.setValue(round);
    }
}
