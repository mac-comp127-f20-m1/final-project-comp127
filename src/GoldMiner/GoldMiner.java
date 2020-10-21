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
    private GraphicsText scoreText;
    private String currentScore = "Your Score : 0";

    private boolean collectingMinerals = false;

    private double angle;


    public GoldMiner() {
        canvas = new CanvasWindow("GoldMiner", CANVAS_WIDTH, CANVAS_HEIGHT);

        gold = new Gold(canvas);
        hook = new Hook(canvas, 800, 600, gold);

        scoreText = new GraphicsText(currentScore);
        scoreText.setPosition(100, 50);
        scoreText.setFontSize(18);
        canvas.add(scoreText);

        createPlayerImage();

        updateAngle();


        /**
         * 一个完整的get是：if start get click, hook.updatePosition for infinite times, if hook y < initial y,
         * stop.
         */
    }

    public void updateAngle() {
        canvas.animate(() -> {
            if (!collectingMinerals) {
                hook.updateAiming(angle);

                angle += 1;
                if (angle >= 70) {
                    angle = -70;
                }
            } else {
                hook.updatePosition(angle);
                if (hook.getCenterY() <= 50) {
                    collectingMinerals = false;

                    canvas.remove(scoreText);
                    currentScore = "Your Score : " + hook.score;
                    scoreText = new GraphicsText(currentScore);
                    scoreText.setPosition(100, 50);
                    scoreText.setFontSize(18);
                    scoreText.setFillColor(Color.MAGENTA);
                    canvas.add(scoreText);
                }
            }
        });

        canvas.onClick(event -> {
            if (!collectingMinerals) {
                collectingMinerals = true;
                hook.updateDirection(10);
            }
        });
    }


    public void createPlayerImage() {
        Ellipse head = new Ellipse(395, 20, 22, 22);
        head.setStrokeColor(Color.PINK);
        head.setStrokeWidth(2);
        Line body = new Line(406, 44, 406, 55);
        body.setStrokeColor(Color.PINK);
        body.setStrokeWidth(3);
        GraphicsText text = new GraphicsText("YOU");
        text.setCenter(412, 28);
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

