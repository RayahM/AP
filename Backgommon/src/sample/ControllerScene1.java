package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerScene1 implements Initializable {

    @FXML
    private String playerTime="1";
    @FXML
    private String rounds="1";

    public String getPlayerTime() {
        return playerTime;
    }

    public void setPlayerTime(String playerTime) {
        this.playerTime = playerTime;
    }

    public String getRounds() {
        return rounds;
    }

    public void setRounds(String rounds) {
        this.rounds = rounds;
    }

    @FXML
    void settingAction(ActionEvent event)throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("settingScene.fxml"));
        Parent settingParent = loader.load() ;
        ControllerSetting setting = loader.getController();
        setting.initValues(getPlayerTime(),getRounds());
        Scene settingScene = new Scene(settingParent);
        Stage settingWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        settingWindow.setScene(settingScene);
        settingWindow.show();

    }

    @FXML
    void newGame(ActionEvent event)throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Board.fxml"));
        Parent newGame = loader.load() ;
        Scene newScene = new Scene(newGame);
        Stage newStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        newStage.setScene(newScene);
        newStage.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
