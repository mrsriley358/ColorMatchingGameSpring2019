/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javamatchinggame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;

/**
 *
 * @author cb0806151
 */
public class Panels extends Application {
    
    GridPane pane = new GridPane();
    
    // initialize variables for use in the program
    Boolean paused = false;
    Color lastPanelClicked;
    Integer panelsSelected = 0;
    ArrayList<Rectangle> isSelected = new ArrayList();
    ArrayList<Rectangle> squarePanels = new ArrayList();
    ArrayList<Color> squarePanelsColors = new ArrayList();
    
    List<Rectangle> rectangles;
    ArrayList<Rectangle> rectangleArray = new ArrayList();;
    
    protected GridPane createPanelPane() {
        pane.setAlignment(Pos.CENTER);
        
        // create the rectangles
        Rectangle a1 = new Rectangle(0 ,0 ,96, 96);         // the size of rectange's will be 96 by 96
        Rectangle a2 = new Rectangle(0 ,0 ,96, 96);
        Rectangle a3 = new Rectangle(0 ,0 ,96, 96);
        Rectangle b1 = new Rectangle(0 ,0 ,96, 96);
        Rectangle b2 = new Rectangle(0 ,0 ,96, 96);
        Rectangle b3 = new Rectangle(0 ,0 ,96, 96);
        Rectangle c1 = new Rectangle(0 ,0 ,96, 96);
        Rectangle c2 = new Rectangle(0 ,0 ,96, 96);
        Rectangle c3 = new Rectangle(0 ,0 ,96, 96);
        
        // create a list of the rectangles for use in functions
        rectangles = Arrays.asList(a1, a2, a3, b1, b2, b3, c1, c2, c3);
        rectangleArray = new ArrayList();
        rectangleArray.addAll(rectangles);
        
        // set the click handlers for the buttons
        setActionHandlersForPanels(rectangles);       
            
        pane.add(a1, 2, 2);
        pane.add(a2, 3, 2);
        pane.add(a3, 4, 2);
        pane.add(b1, 2, 3);
        pane.add(b2, 3, 3);
        pane.add(b3, 4, 3);
        pane.add(c1, 2, 4);
        pane.add(c2, 3, 4);
        pane.add(c3, 4, 4);
        
        return pane;
    }
    
    @Override
    public void start(Stage stage) {     
        
        // setting the stage
        Scene scene = new Scene(createPanelPane(), 480, 480);
        stage.setScene(scene);
        stage.setTitle("Memory Matching Game");
        stage.show(); 
    }
    
    // sets the action handlers for the panels
    public void setActionHandlersForPanels(List<Rectangle> thePanels) {
        for (Rectangle panel: thePanels) {
            panel.setOnMouseClicked(e -> {
                Color panelsColor = squarePanelsColors.get(squarePanels.indexOf(panel));
                clickOnPanel(panel, panelsColor);

            });
        }
    }
    
    // resets the flipped panels when the user clicks on one that isn't in the sequence
    public void pauseAndResetPanels() {
        pane.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        for (int i = 0; i<isSelected.size(); i++) {
            isSelected.get(i).setFill(Color.GRAY);
            isSelected.get(i).setStroke(Color.TRANSPARENT);
        }
        isSelected.clear();
        panelsSelected = 0;
        lastPanelClicked = null;
        paused = false;
    }   
    
    // runs through the logic when the user clicks on a panel
    public void clickOnPanel(Rectangle panel, Color theColor) {
        if (!isSelected.contains(panel) && paused == false && panel.getFill() == Color.GRAY) {
            isSelected.add(panel);
            panel.setFill(theColor);
            panel.setStroke(Color.BLACK);
            if (lastPanelClicked != theColor && lastPanelClicked != null) {
                // reset the squares
                paused = true;
                panel.setStroke(Color.RED);
                final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
                executorService.schedule(new Runnable() {
                    @Override
                    public void run() {
                       pauseAndResetPanels();
                    }
                }, 1, TimeUnit.SECONDS);
                
            } else {
                panelsSelected += 1;
                lastPanelClicked = theColor;

                if (panelsSelected == 3) {
                    // runs when the user selects three squares of the same type
                    panelsSelected = 0;
                    lastPanelClicked = null;
                    isSelected.clear();
                }
            }
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
