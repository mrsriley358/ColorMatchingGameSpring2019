/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javamatchinggame;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author cb0806151
 */
public class JavaMatchingGame extends Application {
    Boolean paused = false;
    Color lastPanelClicked;
    ArrayList<Rectangle> isSelected;
    Integer panelsSelected = 0;
    @Override
    public void start(Stage stage) {
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        Button reset = new Button("Reset");
        pane.add(reset, 2, 5);
    }
    
    public void pauseAndResetPanels() {
        paused = true;
        Thread.sleep(1000);
        for (int i = 0; i<isSelected.size()-1; i++) {
            isSelected.get(i).setFill(Color.GRAY);
        }
        isSelected.clear();
        panelsSelected = 0;
        paused = false;
    }   
    
    public void clickOnPanel(Rectangle panel, Color theColor) {
        panel.setOnMouseClicked(e -> {
            if (paused == false) {
                panel.setFill(theColor);
                if (lastPanelClicked != theColor && lastPanelClicked != null) {
                    pauseAndResetPanels();
                } else {
                    lastPanelClicked = theColor;
                    panelsSelected += 1;
                    isSelected.add(panel);
                }
                
                if (panelsSelected == 3) {
                    panelsSelected = 0;
                    lastPanelClicked = null;
                    isSelected.clear();
                }
            }
        });
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
