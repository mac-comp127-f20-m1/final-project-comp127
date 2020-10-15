package GoldMiner;

import edu.macalester.graphics.CanvasWindow;

public class GoldMiner {

    private static final int CANVAS_WIDTH = 800;
    private static final int CANVAS_HEIGHT = 600;

    private CanvasWindow canvas;


    private boolean animating = true;


    public GoldMiner() {
        canvas = new CanvasWindow("Breakout!", CANVAS_WIDTH, CANVAS_HEIGHT);
    }

    public static void main(String[] args) {
        new GoldMiner();
    }
}

