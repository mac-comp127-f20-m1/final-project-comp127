package GoldMiner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Line;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.Ellipse;

public class Hook {

    private GraphicsObject hook;
    private CanvasWindow canvas;
    private Line hookaiming;
    private Gold gold;


    private double maxX;
    private double maxY;
    private double x2;
    private double y2;// x2,y2 to define the end point of the hook aiming line;

    private double velocity = 10;
    private double score;


    private double angle = Math.toRadians(-10);
    private double moveX = velocity * Math.sin(angle);// TODO：这里的5是目前的设置，后期需要根据hook勾到物品的不同来改变它。
    private double moveY = velocity * Math.cos(angle);


    public final double INITIAL_X = 400;
    public final double INITIAL_Y = 50;

    public boolean now = true;

    public List<GraphicsObject> min;
    public Map<Ellipse, WeightandScore> newMap;

    public Hook(CanvasWindow canvas, double maxX, double maxY, Gold gold) {
        this.maxX = maxX;
        this.maxY = maxY;
        this.canvas = canvas;
        this.gold=gold;

        hook = new Rectangle(INITIAL_X, INITIAL_Y, 10, 10);
        canvas.add(hook);
        canvas.draw();

        min = new ArrayList<>(gold.getList());
        newMap=gold.getMap();

        x2 = INITIAL_X + 5;
        y2 = INITIAL_Y + 15;
        //score=newMap.get(gold).getScore();
        hookaiming = new Line(INITIAL_X + 5, INITIAL_Y, x2, y2);
        hookaiming.setStrokeWidth(4);
        canvas.add(hookaiming);

    }
      /**
     * This function update the position of the hook
     * 
     * @param angle The change angle of the hook
     * @param mineral The name of the mineral that would generate the parameters of the minerals
     * @return
     */
    public void updatePosition(double angle, GraphicsObject mineral) {
         double weight=newMap.get(mineral).getWeight();
         double score=newMap.get(mineral).getScore();
         double newX = moveX -weight;
         double newY=moveY-weight;
        if (hook.getX() >= 0 && hook.getX() <= maxX && hook.getY() <= maxY) {
            hook.moveBy(newX, newY);
            canvas.draw();
        } else {
            newX = 0 - newX;
            newY = 0 - newY;
            hook.moveBy(10*newX, 10*newY);
            canvas.draw();
        }

        for (GraphicsObject g : min) {
                if(g.getY()<= INITIAL_Y){

                }else{
                    getMineral(g);
                }
            }
        // TODO: 如果从list里面remove的话，会有error message：ConcurrentModificationException
        // 可以考虑将检测两者之间距离的distance method换成getElementAt.
        //TODO：现在的问题是，不知道MINERALS怎么放进这个METHOD里面

    }

    public void updateDirection() {

        moveX = velocity * Math.sin(angle);
        moveY = velocity * Math.cos(angle);     
        
    }



    public void updateAiming(double i) {
            angle = Math.toRadians(i);
            x2 = Math.sin(angle) * 40 + INITIAL_X + 5;
            y2 = Math.cos(angle) * 40 + INITIAL_Y + 15;
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
    public boolean getMineral(GraphicsObject mineral) {

        if (distance(mineral) <= 200) {
            if (moveY >= 0) {
                // updateVelocity();
                moveX = 0 - moveX;
                moveY = 0 - moveY;
                
            }
            hook.moveBy(moveX, moveY);
            mineral.moveBy(2 * moveX, 2 * moveY);
            // 系数是2，因为1的时候它检测不到mineral移动。

            if (mineral.getY() <= INITIAL_Y) {
                mineral.setPosition(20, 50);
            }

            return true;
        } else {
            return false;
        }
        //TODO 还没有改MINERAL的VELOCITY

    }

    // TODO: 可以用这个来改变速度，加在上一个getMineral 的 method里面。
    // public void updateVelocity(){
    // velocity = 5;
    // moveX = velocity * Math.sin(angle);
    // moveY = velocity * Math.cos(angle);

    // }

    /**
     * Calculate the distance between the hook and the mineral.
     * 
     * @param gold
     * @return
     */
    public double distance(GraphicsObject mineral) {
        double x1 = this.getCenterX() + 5 * Math.sqrt(2);
        double x2 = mineral.getX() + 15;
        double y1 = this.getCenterY() + 5 * Math.sqrt(2);
        double y2 = mineral.getY() + 15;

        double distance = Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2);

        return distance;

    }

    public double getCenterX() {
        return hook.getX();
    }

    public double getCenterY() {
        return hook.getY();
    }

    public double getMoveX() {
        return moveX;
    }

    public double getMoveY() {
        return moveY;
    }

    public double getCurrentAngle(){
        double answer = Math.atan((hookaiming.getX2()-hookaiming.getX1())/(hookaiming.getY2()-hookaiming.getY1()));
        return answer;
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

    /**
     * Check the collision between the minerals and the hook
     * 
     * @param x x-coordinate
     * @param y y-coordinate
     */
    private GraphicsObject getCollidingObject() {

        if ((canvas.getElementAt(getCenterX(), getCenterY())) != null) {
            return canvas.getElementAt(getCenterX(), getCenterY());
        } else {
            return null;
        }

    }
    //TODO: how to collect score
}
