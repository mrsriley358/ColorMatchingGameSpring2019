/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javamatchinggame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author cb0806151
 */
public class JavaMatchingGame extends Application {
    @Override
    public void start(Stage stage) {
        
        
        Button reset = new Button("Reset");
        
        pane.add(reset, 1, 4);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
