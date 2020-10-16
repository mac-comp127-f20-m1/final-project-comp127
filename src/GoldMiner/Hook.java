package GoldMiner;

import java.util.ArrayList;
import java.util.List;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Rectangle;

public class Hook {

    private GraphicsObject hook;
    private CanvasWindow canvas;
    private Gold gold;

    private double maxX;
    private double maxY;


    private double angle = Math.toRadians(-70);
    private double moveX = 5 * Math.sin(angle);// TODO：这里的5是目前的设置，后期需要根据hook勾到物品的不同来改变它。
    private double moveY = 5 * Math.cos(angle);


    public final double INITIAL_X = 400;
    public final double INITIAL_Y = 50;

    public List<GraphicsObject> min;

    public Hook(CanvasWindow canvas, double maxX, double maxY, Gold gold) {
        this.maxX = maxX;
        this.maxY = maxY;
        this.gold = gold;
        this.canvas = canvas;

        hook = new Rectangle(INITIAL_X, INITIAL_Y, 10, 10);
        canvas.add(hook);
        canvas.draw();

        min = new ArrayList<>(gold.getList());


    }

    public void updatePosition() {

        if (hook.getX() >= 0 && hook.getX() <= maxX && hook.getY() <= maxY) {
            hook.moveBy(moveX, moveY);
            canvas.draw();
        } else {
            moveX = 0 - moveX;
            moveY = 0 - moveY;
            hook.moveBy(moveX, moveY);
        }
        
        GraphicsObject collider = getCollidingObject();
        
             if(collider!=null){
		
          
                canvas.remove(collider); 
                
            }
        // for (GraphicsObject g : min) {
        //     if(g.getY()<= INITIAL_Y){
        //         // canvas.remove(g);
        //         // min.remove(min.indexOf(g)+1);
        //         //min.remove(g);
        //     }else{
        //         getMineral(g);
        //     }
        // }

        // TODO: 如果从list里面remove的话，会有error message：ConcurrentModificationException
        // 可以考虑将检测两者之间距离的distance method换成getElementAt.

        gold.updateMineral(this);


    }

    public void updateDirection() {

        // TODO: find a way to change the angle automatically
        // 找不到满足这个的method，考虑将自动旋转化成由玩家操控旋转，然后create一个Line来表示方向。
        angle += 10;
        moveX = 5 * Math.sin(angle);
        moveY = 5 * Math.cos(angle);


    }

    /**
     * If the disctance between the hook and the mineral is less than 200, 
     * the hook move back to the origin, and the mineral move with the hook.
     * @param g
     * @return
     */
    public boolean getMineral(GraphicsObject mineral) {

        if (distance(mineral) <= 200) {
            if (moveY >= 0) {
                moveX = 0 - moveX;
                moveY = 0 - moveY;
            }
            hook.moveBy(moveX, moveY);
            mineral.moveBy(2 * moveX, 2 * moveY);// 系数是2，因为1的时候它检测不到mineral移动。
            return true;
        } else {
            return false;
        }

    }


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
		
        if((canvas.getElementAt(getCenterX(), getCenterY())) != null) {
             return canvas.getElementAt(getCenterX(), getCenterY());
          }
        else{
             return null;
          }
        
    }
}
