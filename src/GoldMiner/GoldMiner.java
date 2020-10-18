package GoldMiner;

import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Line;
import edu.macalester.graphics.ui.Button;

public class GoldMiner {

    public static final int CANVAS_WIDTH = 800;
    public static final int CANVAS_HEIGHT = 600;// public ?

    private CanvasWindow canvas;
    private Hook hook;
    private Gold gold;

    private Button start;

    private boolean animating = true;

    private double angle;


    public GoldMiner() {
        canvas = new CanvasWindow("GoldMiner", CANVAS_WIDTH, CANVAS_HEIGHT);

        gold = new Gold(canvas);
        hook = new Hook(canvas, 800, 600, gold);

        start = new Button("START");
        start.setPosition(700, 550);
        canvas.add(start);

        createPlayerImage();

        updateAngle();

        /**
         * 一个完整的get是：if start get click, hook.updatePosition for infinite times, if hook y < initial y,
         * stop.
         */
    }

    public void updateAngle() {
        canvas.animate(() -> {
            if (animating) {
                hook.updateAiming(angle);
                angle += 1;
                canvas.onClick(event -> {
                    animating = false;
                    hook.updateDirection();
                    while (hook.getCenterY() >= 50) {
                        hook.updatePosition(angle);
                    }
                });
                if (angle >= 70) {
                    angle = -70;
                }
                start.onClick(() -> {
                    animating = true;
                    hook.posite(hook.INITIAL_X, hook.INITIAL_Y);
                });
            }
        });
    }

    public void createPlayerImage() {
        Ellipse head = new Ellipse(400, 20, 22, 22);
        Line body = new Line(411, 44, 411, 55);
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

