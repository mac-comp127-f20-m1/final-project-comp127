package GoldMiner;

import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Line;

public class GoldMiner {

    public static final int CANVAS_WIDTH = 800;
    public static final int CANVAS_HEIGHT = 600;// public ?

    private CanvasWindow canvas;
    private Hook hook;
    private Gold gold;


    private boolean animating = true;


    public GoldMiner() {
        canvas = new CanvasWindow("GoldMiner", CANVAS_WIDTH, CANVAS_HEIGHT);

        gold = new Gold(canvas);
        hook = new Hook(canvas, 800, 600, gold);

        createPlayerImage();

        while (true) {
            hook.updatePosition();
            // Line line = new Line(hook.INITIAL_X, hook.INITIAL_Y, hook.getCenterX(), hook.getCenterY());
            // canvas.add(line);
            canvas.draw();

            if (hook.getCenterY() <= hook.INITIAL_Y) {
                hook.posite(hook.INITIAL_X, hook.INITIAL_Y);
                // canvas.remove(line);
                canvas.draw();
                hook.updateDirection();
            }
        }

    }

    public void createPlayerImage(){
        Ellipse head = new Ellipse(400, 20, 22, 22);
        Line body = new Line(411,44,411,55);
        GraphicsText text = new GraphicsText("YOU");
        text.setCenter(417, 28);
        text.setFontSize(8);
        canvas.add(head);
        canvas.add(body);
        canvas.add(text);
        canvas.draw();
    }

    public static void main(String[] args) {
        new GoldMiner();
    }
}

