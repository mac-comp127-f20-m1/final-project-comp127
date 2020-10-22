package GoldMiner;

import java.util.ArrayList;
import java.util.List;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Line;
import edu.macalester.graphics.Rectangle;

public class Hook {

    public final double INITIAL_X = 400;
    public final double INITIAL_Y = 50;

    public double score; // Accumulate the score the player has got.

    private CanvasWindow canvas;
    private Gold gold;

    private GraphicsObject hook;
    private Line hookaiming;

    private double maxX;
    private double maxY;
    private double x2;
    private double y2; // x2 and y2 are the coordinate of the end point of the hook aiming line;

    private double velocity = 10;

    private double angle = Math.toRadians(-10);
    private double moveX = velocity * Math.sin(angle);
    private double moveY = velocity * Math.cos(angle);

    public List<GraphicsObject> mineralList;
    // public Map<Ellipse, WeightandScore> newMap;

    public Hook(CanvasWindow canvas, double maxX, double maxY, Gold gold) {
        this.maxX = maxX;
        this.maxY = maxY;
        this.canvas = canvas;
        this.gold = gold;

        hook = new Rectangle(INITIAL_X, INITIAL_Y, 10, 10);
        hook.setCenter(INITIAL_X, INITIAL_Y);
        canvas.add(hook);
        canvas.draw();

        mineralList = new ArrayList<>(gold.getList());

        x2 = INITIAL_X + 2.5;
        y2 = INITIAL_Y + 15;

        hookaiming = new Line(INITIAL_X, INITIAL_Y, x2, y2);
        hookaiming.setStrokeWidth(4);
        canvas.add(hookaiming);
    }

    /**
     * This function update the position of the hook
     * 
     * @param angle   The change angle of the hook
     * @param mineral The name of the mineral that would generate the parameters of the minerals
     * @return
     */
    public void updatePosition(double angle) {
        if (hook.getX() >= 0 && hook.getX() <= maxX && hook.getY() <= maxY) {
            hook.moveBy(moveX, moveY);
        } else {
            moveX = 0 - moveX;
            moveY = 0 - moveY;
            hook.moveBy(10 * moveX, 10 * moveY);
        }

        for (GraphicsObject oneMineral : mineralList) {
            if (oneMineral.getY() >= 900) {
                canvas.remove(oneMineral);
                mineralList.remove(oneMineral);
                break;
            } else {
                getMineral(oneMineral);
            }
        }

    }

    /**
     * This method update the moveX and moveY of the hook by given velocity.
     * 
     * @param velocity
     */
    public void updateDirection(double velocity) {
        moveX = velocity * Math.sin(angle);
        moveY = velocity * Math.cos(angle);
    }


    /**
     * This method takes angle in degrees as parameter, update the position of the aiming line.
     * 
     * @param angleInDegrees
     */
    public void updateAiming(double angleInDegrees) {
        angle = Math.toRadians(angleInDegrees);
        x2 = Math.sin(angle) * 40 + INITIAL_X;
        y2 = Math.cos(angle) * 40 + INITIAL_Y;
        hookaiming.setEndPosition(x2, y2);
        canvas.draw();
        canvas.pause(50);
    }

    /**
     * If the disctance between the hook and the mineral is less than 200, the hook move back to the
     * origin, and the mineral move with the hook.
     * 
     * @param g
     * @return
     */
    public void getMineral(GraphicsObject mineral) {

        double radius = mineral.getSize().getX() / 2;
        velocity = 20 / radius;
        // As the radius of the mineral increase, the velocity of the hook will decrease.

        if (distance(mineral) <= Math.pow(radius, 2)) {
            if (moveY >= 0) {
                updateDirection(velocity);
                moveX = 0 - moveX;
                moveY = 0 - moveY;
            }
            hook.moveBy(moveX, moveY);
            mineral.moveBy(2 * moveX, 2 * moveY);
            // Remove the mineral when it get to the initial point of the hook.
            if (mineral.getY() <= INITIAL_Y + 4) {
                mineral.setPosition(1000, 1000);
                // Change the velocity of the hook
                if (mineral.equals(gold.diamond) || mineral.equals(gold.diamond1)
                    || mineral.equals(gold.diamond2)) {
                    // if the mineral is diamond
                    score += 100;

                } else if (mineral.getSize().getX() != mineral.getSize().getY()) {
                    // if the mineral is stone
                    score += radius;
                }else {
                    // if the mineral is gold
                    score += radius * 3;
                }
            }
        } 
    }

    /**
     * Calculate the distance between the hook and the mineral.
     * 
     * @param gold
     * @return
     */
    public double distance(GraphicsObject mineral) {

        double x1 = this.getCenterX();
        double x2 = mineral.getCenter().getX();
        double y1 = this.getCenterY();
        double y2 = mineral.getCenter().getY();

        double distance = Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2);

        return distance;

    }

    /**
     * Return the x-coordinate of the center of the hook.
     * 
     * @return
     */
    public double getCenterX() {
        return hook.getCenter().getX();
    }

    /**
     * Return the y-coordinate of the center fo the hook.
     * 
     * @return
     */
    public double getCenterY() {
        return hook.getCenter().getY();
    }

    /**
     * Change the position of the hook.
     * 
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public void posite(double x, double y) {
        hook.setPosition(x, y);
    }
}
