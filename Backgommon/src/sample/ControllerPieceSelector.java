package sample;

import javafx.animation.Animation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

import java.io.IOException;


public class ControllerPieceSelector {

    private int spriteColumns = 3;
    private int spriteCounts = 6;
    private int spriteOffsetX = 0;
    private int spriteOffsetY = 0;
    private int spriteWidth = 67;
    private int spriteHeight = 70;
    private int dice1;
    private int dice2;
    private boolean diceRoll = false;
    private ImageView dice1K;
    private ImageView dice2K;
    Model model = new Model();
    private Stage lastStage;




    public Stage getLastStage() {
        return lastStage;
    }

    public void setLastStage(Stage lastStage) {
        this.lastStage = lastStage;
    }

    public int getDice1() {
        return dice1;
    }

    public void setDice1(int dice1) {
        this.dice1 = dice1;
    }

    public int getDice2() {
        return dice2;
    }

    public void setDice2(int dice2) {
        this.dice2 = dice2;
    }

    @FXML
    private AnchorPane selectorPane;

    @FXML
    private Text player1Name;

    @FXML
    private Text player2Name;

    @FXML
    void rollDice(MouseEvent event) {

        if(!diceRoll) {
            selectorPane.getChildren().remove(dice1K);
            selectorPane.getChildren().remove(dice2K);
            ImageView imageViewDice1 = new ImageView(model.getImage("spritesheet.png"));
            imageViewDice1.setTranslateX(150);
            imageViewDice1.setTranslateY(200);

            ImageView imageViewDice2 = new ImageView(model.getImage("spritesheetR.png"));
            imageViewDice2.setTranslateX(550);
            imageViewDice2.setTranslateY(200);
            imageViewDice1.setViewport(new Rectangle2D(spriteOffsetX, spriteOffsetY, spriteWidth, spriteHeight));
            imageViewDice2.setViewport(new Rectangle2D(spriteOffsetX, spriteOffsetY, spriteWidth, spriteHeight));
            Animation animationDice1 = new SpriteAnimation(
                    imageViewDice1,
                    Duration.millis(1500),
                    spriteCounts,
                    spriteColumns,
                    spriteOffsetX,
                    spriteOffsetY,
                    spriteWidth,
                    spriteHeight
            );

            Animation animationDice2 = new SpriteAnimation(
                    imageViewDice2,
                    Duration.millis(1500),
                    spriteCounts,
                    spriteColumns,
                    spriteOffsetX,
                    spriteOffsetY,
                    spriteWidth,
                    spriteHeight
            );

            animationDice1.setCycleCount(1);
            animationDice1.play();
            animationDice2.setCycleCount(1);
            animationDice2.play();
            animationDice1.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    selectorPane.getChildren().remove(imageViewDice1);
                    selectorPane.getChildren().remove(imageViewDice2);
                    setDice1(model.randomDice());
                    setDice2(model.randomDice());
                    ImageView diceI1 = new ImageView(model.getImage("Dice_" + getDice1() + ".png"));
                    dice1K = diceI1;
                    diceI1.setTranslateX(150);
                    diceI1.setTranslateY(200);
                    ImageView diceI2 = new ImageView(model.getImage("Dice_" + getDice2() + ".png"));
                    dice2K = diceI2;
                    diceI2.setTranslateX(550);
                    diceI2.setTranslateY(200);
                    selectorPane.getChildren().addAll(diceI1, diceI2);
                    if(getDice1()!=getDice2()) {
                        diceRoll = true;






                    }

                }
            });
            selectorPane.getChildren().addAll(imageViewDice1, imageViewDice2);
        }


    }

    public void initValue(String user1Name, String user2Name){


        this.player1Name.setText(user1Name);
        this.player2Name.setText(user2Name);

    }


    @FXML
    void darkMode(MouseEvent event) throws IOException {

        if(diceRoll){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Board.fxml"));
            Parent newGame = loader.load() ;
            Controller controller = loader.getController();
            controller.turnPlay(getDice1(),getDice2(),player1Name.toString(),player2Name.toString(),true);
            Scene newScene = new Scene(newGame);
            Stage newStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            newStage.setScene(newScene);
            newStage.show();
        }
    }

    @FXML
    void lightMode(MouseEvent event) {

    }


}
