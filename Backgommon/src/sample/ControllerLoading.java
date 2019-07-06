package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerLoading implements Initializable {

    @FXML
    private Stage primaryStage;

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    private ProgressBar progressBar;



    @Override
    public void initialize(URL location, ResourceBundle resources)  {

        progressBar.setProgress(0.0);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(progressBar.progressProperty(), 0)),
                new KeyFrame(Duration.seconds(2), e-> {
                    this.getPrimaryStage().close();
                    Parent root = null;
                    FXMLLoader loader =  new FXMLLoader();
                    try {

                        root = loader.load(getClass().getResource("scene1.fxml")) ;

                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    Stage stage= new Stage();
                    stage.setTitle("Backgommon");
                    stage.setScene(new Scene(root, 800, 600));

                    stage.show();

                }, new KeyValue(progressBar.progressProperty(), 1))
        );
        timeline.setCycleCount(1);
        timeline.play();

    }
}
