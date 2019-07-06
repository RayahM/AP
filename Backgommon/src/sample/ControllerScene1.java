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

    public String getUser1Name() {
        return user1Name;
    }

    public void setUser1Name(String user1Name) {
        this.user1Name = user1Name;
    }

    public String getUser2Name() {
        return user2Name;
    }

    public void setUser2Name(String user2Name) {
        this.user2Name = user2Name;
    }

    private String user1Name = "Player1";
    private String user2Name = "Player2";

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
        loader.setLocation(getClass().getResource("pieceSelector.fxml"));
        Parent newGame = loader.load() ;
        ControllerPieceSelector controllerPieceSelector = loader.getController();
        controllerPieceSelector.initValue(getUser1Name(),getUser2Name());
        Scene newScene = new Scene(newGame);
        Stage newStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        newStage.setScene(newScene);
        newStage.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
