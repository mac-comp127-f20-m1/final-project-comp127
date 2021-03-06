package GoldMiner;

import java.awt.Color;
import java.util.List;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Line;

public class GoldMiner {

    public static final int CANVAS_WIDTH = 800;
    public static final int CANVAS_HEIGHT = 600;

    private static CanvasWindow canvas;
    private static Gold gold;

    public List<GraphicsObject> mineralList;
    
    private Hook hook;
    private double angle;
    private Line hookLine;
    private double limitSec = 60;

    private GraphicsText scoreText;
    private GraphicsText timeText;
    private String currentScore = "Your Score : 0";
    private String currentTime = "Time Left :  60 sec";
    private GraphicsText winMessage;
    private GraphicsText lostMessage;


    private boolean collectingMinerals = false;

    public GoldMiner(CanvasWindow canvas) {

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
        timeText.setPosition(520, 40);
        timeText.setFont(FontStyle.BOLD, 18);

        canvas.add(timeText);

        hookLine = new Line(hook.INITIAL_X, hook.INITIAL_Y, hook.getCenterX(), hook.getCenterY());
        canvas.add(hookLine);

        createPlayerImage();

        runGame();
    }

    public void runGame() {
        canvas.animate((deltaTime) -> {
            timeCountDown(deltaTime);

            if (!collectingMinerals) {
                hook.updateAiming(angle);
                angle += 1;

                if (angle >= 70) {
                    angle = -70;
                }

            } else {
                hook.updatePosition(angle);
                hookLine.setEndPosition(hook.getCenterX(), hook.getCenterY());

                if (hook.getCenterY() <= 47) {
                    collectingMinerals = false;
                    scoreText.setText("Your Score : " + hook.score);
                    scoreText.setFillColor(Color.MAGENTA);

                    if (hook.score > 500) {
                        collectingMinerals = false;
                        printWinMessage();
                        canvas.closeWindow();
                    }
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


    /**
     * This method create the image of the player on the middle top of the canvas window.
     */
    public void createPlayerImage() {
        Ellipse head = new Ellipse(390, 6, 30, 30);
        head.setStrokeColor(Color.PINK);
        head.setStrokeWidth(2);
        Line body = new Line(406, 36, 406, 53);
        body.setStrokeColor(Color.PINK);
        body.setStrokeWidth(4);
        GraphicsText text = new GraphicsText("YOU");
        text.setCenter(409, 21);
        text.setFontSize(10);
        canvas.add(head);
        canvas.add(body);
        canvas.add(text);
        canvas.draw();
    }

    /**
     * This method prints the win message on the canvas window.
     */
    public void printWinMessage() {
        String win = "YOU WIN!!";
        winMessage = new GraphicsText(win);
        winMessage.setFillColor(Color.RED);
        winMessage.setFontSize(45);
        canvas.add(winMessage, 350, 250);
        canvas.draw();
        canvas.pause(3000);
    }

    /**
     * This method prints the win message on the canvas window.
     */
    public void printLoseMessage() {
        canvas.removeAll();
        String lost = "GAME OVER!!";
        lostMessage = new GraphicsText(lost);
        lostMessage.setFillColor(Color.RED);
        lostMessage.setFontSize(45);
        canvas.add(lostMessage, 350, 250);
        canvas.draw();
        canvas.pause(3000);
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
    public void timeCountDown(double deltaTime) {
        limitSec -= deltaTime;
        timeText.setText("Time Left : " + (int) limitSec  +" sec");
        if (hook.score < 500 && limitSec <= 0) {
            printLoseMessage();
            canvas.closeWindow();
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

