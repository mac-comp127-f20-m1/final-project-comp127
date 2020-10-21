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
    private int limitSec = 60;

    public List<GraphicsObject> mineralList;

    private static CanvasWindow canvas;
    private static Gold gold;
    private Hook hook;

    private GraphicsText scoreText;
    private GraphicsText timeText;
    private String currentScore = "Your Score : 0";
    private String currentTime = "Your time limit:  60 sec";
    private GraphicsText winMessage;
    private GraphicsText lostMessage;

    private Line hookLine;

    private boolean collectingMinerals = false;

    private double angle;


    public GoldMiner(CanvasWindow canvas)  {

        gold.addToCanvas();
        hook = new Hook(canvas, 800, 600, gold);

        scoreText = new GraphicsText(currentScore);
        scoreText.setPosition(100, 50);
        scoreText.setFontSize(18);
        canvas.add(scoreText);

        GraphicsText targetScore = new GraphicsText("Target Score: 500");
        targetScore.setPosition(100, 20);
        targetScore.setFontSize(18);
        canvas.add(targetScore);

        timeText = new GraphicsText(currentTime);
        timeText.setPosition(500, 50);
        timeText.setFontSize(18);
        canvas.add(timeText);

        hookLine = new Line(hook.INITIAL_X, hook.INITIAL_Y, hook.getCenterX(), hook.getCenterY());
        canvas.add(hookLine);


        createPlayerImage();

        runGame();
        //timeCountDown();
        //TODO: everything would stop moving until the time=0;
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

                    if (hook.score > 500) {
                        collectingMinerals = false;
                        canvas.removeAll();
                        String win = "YOU WIN!!";
                        winMessage = new GraphicsText(win);
                        winMessage.setFillColor(Color.RED);
                        winMessage.setFontSize(45);
                        canvas.add(winMessage, 350, 250);
                        canvas.draw();
                        //canvas.pause(3000);
                    } else if (hook.score < 500 && limitSec <= 0) {
                        canvas.removeAll();
                        String lost = "GAME OVER!!";
                        lostMessage = new GraphicsText(lost);
                        lostMessage.setFillColor(Color.RED);
                        lostMessage.setFontSize(45);
                        canvas.add(lostMessage, 350, 250);
                        canvas.draw();
                    //game over would not show on the screen

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
     * 
     * @param gold
     */
    public static void createIntroductionPage(Gold gold) {

        String introduction = "Release the hook to dig into the ground by click on the screen,";
        String introduction2 = "use your wisdom to find minerals, ";
        String introduction3 = "and reach the target score to pass the level!";
        String introduction4 = "Different minerals worth different points,";
        String introduction5 = "diamonds the highest, the stone the lowest.";
        String introduction6 = "Scores of the gold and stones vary from the radius.";

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

        GraphicsText intro4 = new GraphicsText(introduction4);
        intro4.setPosition(150, 180);
        intro4.setFontSize(24);
        intro4.setFillColor(Color.darkGray);
        canvas.add(intro4);

        GraphicsText intro5 = new GraphicsText(introduction5);
        intro5.setPosition(130, 220);
        intro5.setFontSize(24);
        intro5.setFillColor(Color.darkGray);
        canvas.add(intro5);

        GraphicsText intro6 = new GraphicsText(introduction6);
        intro6.setPosition(100, 260);
        intro6.setFontSize(24);
        intro6.setFillColor(Color.darkGray);
        canvas.add(intro6);


        canvas.add(gold.finalMineral.get(1), 120, 300);
        GraphicsText bigG = new GraphicsText(": 75 points");
        bigG.setFontSize(25);
        canvas.add(bigG, 180, 325);

        canvas.add(gold.finalMineral.get(11), 125, 400);
        GraphicsText mediumG = new GraphicsText(": 45 points");
        mediumG.setFontSize(25);
        canvas.add(mediumG, 180, 420);

        canvas.add(gold.finalMineral.get(7), 130, 480);
        GraphicsText smallG = new GraphicsText(": 30 points");
        smallG.setFontSize(25);
        canvas.add(smallG, 180, 495);


        canvas.add(gold.finalMineral.get(4), 385, 300);
        GraphicsText bigS = new GraphicsText(": 35 points");
        bigS.setFontSize(25);
        canvas.add(bigS, 470, 325);

        canvas.add(gold.finalMineral.get(16), 405, 400);
        GraphicsText smallS = new GraphicsText(": 17.5 points");
        smallS.setFontSize(25);
        canvas.add(smallS, 470, 420);

        canvas.add(gold.finalMineral.get(13), 415, 480);
        GraphicsText diamond = new GraphicsText(": 100 points");
        diamond.setFontSize(25);
        canvas.add(diamond, 470, 495);


        canvas.draw();
    }

    /**
     * This method count the time that left for the user.
     * 
     *
     */
    public void timeCountDown() {
        while (limitSec > 0) {
            limitSec--;
            try {
                Thread.sleep(1000);
                canvas.remove(timeText);
                currentTime = "Your time limit: " + limitSec;
                timeText = new GraphicsText(currentTime);
                timeText.setPosition(500, 50);
                timeText.setFontSize(18);
                canvas.add(timeText);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

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

