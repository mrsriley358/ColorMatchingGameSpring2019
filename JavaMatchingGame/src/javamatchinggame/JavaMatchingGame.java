/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javamatchinggame;

import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 *
 * @author cb0806151
 */
public class JavaMatchingGame extends resetButton {
    
    @Override
    public void start(Stage stage) {
        // setting the stage
        Scene scene = new Scene(addInResetButton(), 480, 480);
        stage.setScene(scene);
        stage.setTitle("Memory Matching Game");
        stage.show(); 
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
