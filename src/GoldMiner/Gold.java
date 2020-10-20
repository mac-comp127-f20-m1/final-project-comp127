package GoldMiner;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
    private static final double MAXDIAMOND = 40;
    private static final double MAXGOLD = 30;
    private static final double MAXSTONE = 15;
    private static final double SCOREDIAMOND = 40;
    private static final double SCOREGOLD = 30;
    private static final double SCORESTONE = 20;


    public Random rand = new Random();
    private Random random;

    private double x;
    private double y;

    public Rectangle diamond;
    public Ellipse bigGold;
    public Ellipse bigStone;
    public Ellipse mediumGold;
    public Ellipse smallGold;
    public Ellipse smallStone;

    public Ellipse largeGold;
    public List<GraphicsObject> threekindsOfMineral = new ArrayList<>();
    // This list contains three kinds of minerals of different size.

    public List<GraphicsObject> finalMineral = new ArrayList<>();
    // This list contains the minerals that are chosen randomly and added to the canvas window.
    private Map<Ellipse, WeightandScore> informationOfMinerals = new HashMap<>();
    // This contains the information inclusing the radius, weight and score of a mineral
    private CanvasWindow canvas;


    public Gold(CanvasWindow canvas) {
        this.canvas = canvas;


        createMineral();

        for (GraphicsObject mineralPieces : finalMineral) {
            x = Math.random() * 700;
            y = Math.random() * 400 + 125;
            // x += 50;
            // y += 30;
            mineralPieces.setPosition(x, y);
            canvas.add(mineralPieces);
            canvas.draw();
        }

    }

    public void createMineral() {

        // Ellipse bigGold = new Ellipse(x, y, 50, 50);
        bigGold = new Ellipse(x, y, 50, 50);
        bigGold.setFillColor(GOLDEN);
        bigGold.setStrokeColor(GOLDEN);

        Ellipse bigGold1 = new Ellipse(x, y, 50, 50);
        bigGold1.setFillColor(GOLDEN);
        bigGold1.setStrokeColor(GOLDEN);

        Ellipse bigGold2 = new Ellipse(x, y, 50, 50);
        bigGold2.setFillColor(GOLDEN);
        bigGold2.setStrokeColor(GOLDEN);

        // Ellipse mediumGold = new Ellipse(x, y, 30, 30);
        mediumGold = new Ellipse(x, y, 30, 30);
        mediumGold.setFillColor(GOLDEN);
        mediumGold.setStrokeColor(GOLDEN);

        Ellipse mediumGold1 = new Ellipse(x, y, 30, 30);
        mediumGold1.setFillColor(GOLDEN);
        mediumGold1.setStrokeColor(GOLDEN);

        // Ellipse smallGold = new Ellipse(x, y, 20, 20);
        smallGold = new Ellipse(x, y, 20, 20);
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


        // Ellipse bigStone = new Ellipse(x, y, 50, 50);
        bigStone = new Ellipse(x, y, 50, 50);
        bigStone.setFillColor(Color.gray);
        bigStone.setStrokeColor(Color.gray);
        Ellipse bigStone1 = new Ellipse(x, y, 50, 50);
        bigStone1.setFillColor(Color.gray);
        bigStone1.setStrokeColor(Color.gray);
        Ellipse bigStone2 = new Ellipse(x, y, 50, 50);
        bigStone2.setFillColor(Color.gray);
        bigStone2.setStrokeColor(Color.gray);

        // Ellipse smallStone = new Ellipse(x, y, 25, 25);
        smallStone = new Ellipse(x, y, 25, 25);
        smallStone.setFillColor(Color.gray);
        smallStone.setStrokeColor(Color.gray);
        Ellipse smallStone1 = new Ellipse(x, y, 25, 25);
        smallStone1.setFillColor(Color.gray);
        smallStone1.setStrokeColor(Color.gray);
        Ellipse smallStone2 = new Ellipse(x, y, 25, 25);
        smallStone2.setFillColor(Color.gray);
        smallStone2.setStrokeColor(Color.gray);


        // Rectangle diamond = new Rectangle(x, y, 20, 20);
        diamond = new Rectangle(x, y, 20, 20);
        diamond.setFillColor(DIAMOND);
        Rectangle diamond1 = new Rectangle(x, y, 20, 20);
        diamond1.setFillColor(DIAMOND);
        Rectangle diamond2 = new Rectangle(x, y, 20, 20);
        diamond2.setFillColor(DIAMOND);


        finalMineral = List.of(bigGold, bigGold1, bigGold2, bigStone, bigStone1, bigStone2, smallGold,
            smallGold1, smallGold2, smallGold3, mediumGold, mediumGold1, diamond, diamond1, diamond2);

    }

    /**
     * Return the radius of the given mineral 还没写出来，我在想是不是可以用Map来解决这个问题，把mineral设为Key,
     * 把mineral的重量设为value;
     * 
     * @param mineral
     * @return
     */
    public double getMineralRadius(GraphicsObject mineral) {

        return informationOfMinerals.get(mineral).getRadius();
    }

    /**
     * Create a map that the key contains the information of the minerals and the value is the mineral
     * 
     * @param
     * @return
     */
    public void createMap() {
        // informationOfMinerals.put(new WeightandScore(MAXDIAMOND-randomDouble(), SCOREDIAMOND, 20),
        // diamond);
        informationOfMinerals.put(bigGold, 
            new WeightandScore(MAXGOLD - randomDouble(), SCOREGOLD, 50));
        informationOfMinerals.put(mediumGold,
            new WeightandScore(MAXGOLD - randomDouble() * 4, SCOREGOLD - randomDouble(), 30));
        informationOfMinerals.put(smallGold,
            new WeightandScore(MAXGOLD - randomDouble() * 8, SCOREGOLD - randomDouble() * 4, 20));
        informationOfMinerals.put(bigStone, new WeightandScore(MAXSTONE - randomDouble(), SCORESTONE, 50));
        informationOfMinerals.put(smallStone,
            new WeightandScore(MAXSTONE - randomDouble() * 4, SCOREGOLD - randomDouble(), 25));
    }
    // TODO:diamond是Rectangle其他类型是Ellipse,导致diaomnd无法与其他minerals放到同一个map里面


    public List<GraphicsObject> getList() {
        return finalMineral;
    }
    // TODO: 现有的问题是：第一：当hook勾到mineral的时候，要根据mineral的种类来改变钩子的移动的速度（hook file里有提到，
    // 但是因为很难去判定到底hook抓到的是哪一个mineral，所以我在想是不是能用Map来解决这个问题。
    //
    // 第二，我尝试把三种mineral集中在一个file里面，但是现有的list finalMineral太过复杂。
    // 我本来是想把一种mineral多次添加到list里面，但是当我去定义这个mineral的坐标的时候，所有同类mineral的坐标都会同时被更改。
    // 所以我只能命名bigGold1,2,3...

    
    /** create random double between 1 and 3 */
    public double randomDouble() {
        double max = 3.0;
        double min = 1.0;
        return random.nextDouble() * (max - min) + min;
    }

    public Map<Ellipse, WeightandScore> getMap(){
        return informationOfMinerals;
    }
}
