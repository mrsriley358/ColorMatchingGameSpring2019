/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javamatchinggame;

import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.Random;
import static javafx.application.Application.launch;
import javafx.geometry.HPos;

/**
 *
 * @author cb0806151
 */
public class resetButton extends Panels {
    
    protected GridPane addInResetButton() {
        GridPane pane = super.createPanelPane();
        
        // create and position the reset button
        Button reset = new Button("Reset");
        pane.setHalignment(reset, HPos.CENTER);
        reset.setTranslateY(10);
        
        // setting the button to reset the button on click
        reset.setOnAction(e -> {
            paused = false;
            lastPanelClicked = null;
            panelsSelected = 0;
            isSelected.clear();
            squarePanels.clear();
            squarePanelsColors.clear();
            rectangleArray.addAll(rectangles);
            setBoardColors(rectangleArray);
            
        });
        
        // adding the rectangles and the reset button to the grid pane
        pane.add(reset, 3, 5);    
        
        // set the colors of the panels
        setBoardColors(rectangleArray);
        
        return pane;
    }
    
    // sets the colors of the panels
    public void setBoardColors(List<Rectangle> rectangles) {
        Random rand = new Random();
        rectangles = setNumberOfColors(rand, 3, Color.YELLOW, rectangles);
        rectangles = setNumberOfColors(rand, 3, Color.GREEN, rectangles);
        setNumberOfColors(rand, 3, Color.BLUE, rectangles);
    }
    
    // sets a number of panels to a specific color
    public List<Rectangle> setNumberOfColors(Random rand, int numberOfColors, Color theColor, List<Rectangle> rectangles) {
        for (int i=0; i < numberOfColors; i++) {
            int setHiddenColor = rand.nextInt(rectangles.size());
            squarePanelsColors.add(theColor);
            squarePanels.add(rectangles.get(setHiddenColor));
            rectangles.get(setHiddenColor).setFill(Color.GRAY);
            rectangles.get(setHiddenColor).setStroke(Color.TRANSPARENT);    
            rectangles.remove(setHiddenColor);
        }     
        return rectangles;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
