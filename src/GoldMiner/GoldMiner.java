package GoldMiner;

import java.awt.Color;
import java.util.List;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Line;

public class GoldMiner {

    public static final int CANVAS_WIDTH = 800;
    public static final int CANVAS_HEIGHT = 600;

    public List<GraphicsObject> mineralList;

    private static CanvasWindow canvas;
    private static Gold gold;
    private Hook hook;

    private GraphicsText scoreText;
    private String currentScore = "Your Score : 0";
    private GraphicsText winMessage;

    private Line hookLine;

    private boolean collectingMinerals = false;

    private double angle;


    public GoldMiner(CanvasWindow canvas) {

        gold.addToCanvas();
        hook = new Hook(canvas, 800, 600, gold);

        scoreText = new GraphicsText(currentScore);
        scoreText.setPosition(100, 50);
        scoreText.setFontSize(18);
        canvas.add(scoreText);

        // TODO: 加一个目标分数在页面的右上角。

        hookLine = new Line(hook.INITIAL_X, hook.INITIAL_Y, hook.getCenterX(), hook.getCenterY());
        canvas.add(hookLine);

        createPlayerImage();

        runGame();
    }

    public void runGame() {
        canvas.animate(() -> {
            if (!collectingMinerals) {
                hook.updateAiming(angle);
                angle += 1;

                if (angle >= 70) {
                    angle = -70;
                }

            } else {
                hook.updatePosition(angle);
                hookLine.setEndPosition(hook.getCenterX(), hook.getCenterY());

                if (hook.getCenterY() <= 50) {
                    collectingMinerals = false;

                    canvas.remove(scoreText);
                    currentScore = "Your Score : " + hook.score;
                    scoreText = new GraphicsText(currentScore);
                    scoreText.setPosition(100, 50);
                    scoreText.setFontSize(18);
                    scoreText.setFillColor(Color.MAGENTA);
                    canvas.add(scoreText);

                    if (hook.score > 300) {
                        collectingMinerals = false;

                        String win = "YOU WIN!!";
                        winMessage = new GraphicsText(win);
                        winMessage.setFillColor(Color.RED);
                        winMessage.setFontSize(45);
                        canvas.add(winMessage, 350, 250);
                        canvas.draw();
                        canvas.pause(3000);
                    }
                }
            }
        });

        canvas.onClick(event -> {
            if (!collectingMinerals) {
                collectingMinerals = true;
                hook.updateDirection(10);
                if (winMessage != null) {
                    canvas.remove(winMessage);
                }
            }
        });
    }


    /**
     * This method create the image of the player on the middle top of the canvas window.
     */
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

    /**
     * This method create the introduction page of the GoldMiner Game.
     * @param gold
     */
    public static void createIntroductionPage(Gold gold) {

        String introduction = "Release the hook to dig into the ground by click on the screen,";
        String introduction2 = "use your wisdom to find minerals, ";
        String introduction3 = "and reach the target score to pass the level!";

        GraphicsText intro1 = new GraphicsText(introduction);
        intro1.setPosition(50, 50);
        intro1.setFontSize(25);
        canvas.add(intro1);

        GraphicsText intro2 = new GraphicsText(introduction2);
        intro2.setPosition(170, 90);
        intro2.setFontSize(25);
        canvas.add(intro2);

        GraphicsText intro3 = new GraphicsText(introduction3);
        intro3.setPosition(130, 130);
        intro3.setFontSize(25);
        canvas.add(intro3);

        canvas.add(gold.finalMineral.get(1), 120, 200);
        GraphicsText bigG = new GraphicsText(": 75 points");
        bigG.setFontSize(25);
        canvas.add(bigG, 180, 225);

        canvas.draw();

        // TODO: Create introduction for each type of mineral.

        // canvas.add(gold.finalMineral.get(11), 120, 270);

        // canvas.add(gold.finalMineral.get(7), 120, 300);

        // canvas.add(gold.finalMineral.get(4));

        // canvas.add(smallStone);

        // canvas.add(gold.diamond);
    }

    public static void main(String[] args) {
        canvas = new CanvasWindow("GoldMiner", CANVAS_WIDTH, CANVAS_HEIGHT);
        gold = new Gold(canvas);

        createIntroductionPage(gold);
        canvas.pause(5000);
        canvas.removeAll();


        new GoldMiner(canvas);
    }
}

