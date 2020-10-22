package GoldMiner;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Rectangle;

/**
 * This file create a group of Gold pieces with different size and position. Three Classes or one?
 */

public class Gold {

    private static final Color GOLDEN = new Color(255, 215, 0);
    private static final Color DIAMOND = new Color(185, 242, 255);

    private double x; // x-coordinate of each mineral
    private double y; // y-coordinate of each mineral

    public Rectangle diamond;
    public Rectangle diamond1;
    public Rectangle diamond2;

    public List<GraphicsObject> threekindsOfMineral = new ArrayList<>();
    // This list contains three kinds of minerals of different size.

    public List<GraphicsObject> finalMineral = new ArrayList<>();
    // This list contains the minerals that are chosen randomly and added to the canvas window.

    private CanvasWindow canvas;


    public Gold(CanvasWindow canvas) {
        this.canvas = canvas;
        createMineral();
    }

    public void createMineral() {
        // Create three pieces of big size Gold.
        Ellipse bigGold = new Ellipse(x, y, 50, 50);
        bigGold.setFillColor(GOLDEN);
        bigGold.setStrokeColor(GOLDEN);
        Ellipse bigGold1 = new Ellipse(x, y, 50, 50);
        bigGold1.setFillColor(GOLDEN);
        bigGold1.setStrokeColor(GOLDEN);
        Ellipse bigGold2 = new Ellipse(x, y, 50, 50);
        bigGold2.setFillColor(GOLDEN);
        bigGold2.setStrokeColor(GOLDEN);

        // Create two pieces of medium size Gold.
        Ellipse mediumGold = new Ellipse(x, y, 30, 30);
        mediumGold.setFillColor(GOLDEN);
        mediumGold.setStrokeColor(GOLDEN);
        Ellipse mediumGold1 = new Ellipse(x, y, 30, 30);
        mediumGold1.setFillColor(GOLDEN);
        mediumGold1.setStrokeColor(GOLDEN);

        // Create four pieces of small size Gold.
        Ellipse smallGold = new Ellipse(x, y, 20, 20);
        smallGold.setFillColor(GOLDEN);
        smallGold.setStrokeColor(GOLDEN);
        Ellipse smallGold1 = new Ellipse(x, y, 20, 20);
        smallGold1.setFillColor(GOLDEN);
        smallGold1.setStrokeColor(GOLDEN);
        Ellipse smallGold2 = new Ellipse(x, y, 20, 20);
        smallGold2.setFillColor(GOLDEN);
        smallGold2.setStrokeColor(GOLDEN);
        Ellipse smallGold3 = new Ellipse(x, y, 20, 20);
        smallGold3.setFillColor(GOLDEN);
        smallGold3.setStrokeColor(GOLDEN);


        // Create three big Stones
        Ellipse bigStone = new Ellipse(x, y, 70, 50);
        bigStone.setFillColor(Color.gray);
        bigStone.setStrokeColor(Color.gray);
        Ellipse bigStone1 = new Ellipse(x, y, 70, 50);
        bigStone1.setFillColor(Color.gray);
        bigStone1.setStrokeColor(Color.gray);
        Ellipse bigStone2 = new Ellipse(x, y, 70, 50);
        bigStone2.setFillColor(Color.gray);
        bigStone2.setStrokeColor(Color.gray);

        // Create three small Stones
        Ellipse smallStone = new Ellipse(x, y, 35, 25);
        smallStone.setFillColor(Color.gray);
        smallStone.setStrokeColor(Color.gray);
        Ellipse smallStone1 = new Ellipse(x, y, 35, 25);
        smallStone1.setFillColor(Color.gray);
        smallStone1.setStrokeColor(Color.gray);
        Ellipse smallStone2 = new Ellipse(x, y, 35, 25);
        smallStone2.setFillColor(Color.gray);
        smallStone2.setStrokeColor(Color.gray);


        // Create three diamonds;
        diamond = new Rectangle(x, y, 20, 20);
        diamond.setFillColor(DIAMOND);
        diamond1 = new Rectangle(x, y, 20, 20);
        diamond1.setFillColor(DIAMOND);
        diamond2 = new Rectangle(x, y, 20, 20);
        diamond2.setFillColor(DIAMOND);


        finalMineral = List.of(bigGold, bigGold1, bigGold2, bigStone, bigStone1, bigStone2, smallGold,
            smallGold1, smallGold2, smallGold3, mediumGold, mediumGold1, diamond, diamond1, diamond2, smallStone1,
            smallStone, smallStone2);

    }

    public void addToCanvas(){
        for (GraphicsObject mineralPieces : finalMineral) {
            x = Math.random() * 700;
            y = Math.random() * 400 + 125;
            mineralPieces.setPosition(x, y);
            canvas.add(mineralPieces);
            canvas.draw();
        }
    }

    public List<GraphicsObject> getList() {
        return finalMineral;
    }

}
