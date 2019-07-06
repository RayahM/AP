package sample;

import javafx.scene.image.Image;

public class Model {

    public int randomDice(){

        int max = 6;
        int min = 1;
        int diceRange = max - min +1;

        int rand = (int)(Math.random()*diceRange) + min;
        return rand;
    }

    public Image getImage(String address){
        return new Image(getClass().getResourceAsStream(address));
    }

}
