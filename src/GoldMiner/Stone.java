package GoldMiner;

import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsObject;

public class Stone {

    private static final int CANVAS_WIDTH = 600;
    private static final int CANVAS_HEIGHT = 800;
    
    private double x;
    private double y; 
    // TODO: Add a random method for the position of Stone later.

    public GraphicsObject largeStone;
    public GraphicsObject smallStone;

    public Stone(){

        // Make Stone of three different sizes:

        largeStone = new Ellipse(x, y, 100, 70);

        smallStone = new Ellipse(x, y, 50, 35);

    }

    // TODO: create a method that let the mineral move with the hook as the hook touch it.
    
    
}
