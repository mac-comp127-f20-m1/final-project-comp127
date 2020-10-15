package GoldMiner;

import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsObject;

public class Diamond {

    private static final int CANVAS_WIDTH = 600;
    private static final int CANVAS_HEIGHT = 800;
    
    private double x;
    private double y;

    private GraphicsObject diamond;

    public Diamond() {

        diamond = new Ellipse(x, y, 15, 15);

    }

    // TODO: create a method that let the mineral move with the hook as the hook touch it.
}
