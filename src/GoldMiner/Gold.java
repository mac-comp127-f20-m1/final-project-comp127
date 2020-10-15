package GoldMiner;

import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsObject;

/**
 *  This file create a group of Gold pieces with different size and position.
 */

public class Gold {

    private static final int CANVAS_WIDTH = 600;
    private static final int CANVAS_HEIGHT = 800;

    private double x;
    private double y; 
    // TODO: Add a random method for the position of Golds later.

    public GraphicsObject largeGold;
    public GraphicsObject middleGold;
    public GraphicsObject smallGold;

    public Gold(){

        // Make Gold pieces of three different sizes:

        largeGold = new Ellipse(x, y, 80, 80);

        middleGold = new Ellipse(x, y, 40, 40);

        smallGold = new Ellipse(x, y, 20, 20);

    }

    // TODO: create a method that let the mineral move with the hook as the hook touch it.
    
}
