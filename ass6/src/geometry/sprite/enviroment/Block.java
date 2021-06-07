package geometry.sprite.enviroment;

import biuoop.DrawSurface;
import collision.Collidable;
import listener.HitListener;
import listener.HitNotifier;
import geometry.characteristics.Velocity;
import geometry.shape.Point;
import geometry.shape.Rectangle;
import geometry.sprite.Ball;
import sprite.Sprite;
import game.levels.GameLevel;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yogev abarbanel
 * Id: 326116910
 * one Block.
 */
public class Block implements Collidable, Sprite, HitNotifier {

    public static final int DEFAULT_WIDTH = 60;
    public static final int DEFAULT_HEIGHT = 20;

    private List<HitListener> hitListeners;

    private Rectangle rectangle;
    private Color color;

    /**
     * Constructor.
     * @param rectangle the Block (shape)
     */
    public Block(Rectangle rectangle) {
        this(rectangle, Ball.randomColor());
    }


    /**
     * Constructor.
     * @param rectangle the Block (shape)
     * @param color the color of the Block
     */
    public Block(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * getCollisionRectangle.
     *
     * @return the "collision shape" of the object.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * hit.
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * @param collisionPoint  the collided Point
     * @param currentVelocity the velocity at collided time
     * @param hitter the hitting ball.
     * @return the new velocity expected after the hit (based on the force the object inflicted on us).
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        currentVelocity = collidedUpperSide(collisionPoint, currentVelocity);
        currentVelocity = collidedLowerSide(collisionPoint, currentVelocity);
        currentVelocity = collidedLeftSide(collisionPoint, currentVelocity);
        currentVelocity = collidedRightSide(collisionPoint, currentVelocity);
        this.notifyHit(hitter);

        return currentVelocity;
    }

    /**
     * addToGame.
     * add a Collidable to the Game environment.
     *
     * @param g the game Object.
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * collidedUpperSide.
     * check if an object hit the upper side and return the new velocity in accordance to that.
     * @param collisionPoint  the collided Point
     * @param currentVelocity the velocity at collided time
     * @return the new velocity expected after the hit (based on the force the object inflicted on us).
     */
    private Velocity collidedUpperSide(Point collisionPoint, Velocity currentVelocity) {
        if (rectangle.getUpperSide().isInLine(collisionPoint)) {
            currentVelocity.setDy(-currentVelocity.getDy());
        }

        return currentVelocity;
    }

    /**
     * collidedLowerSide.
     * check if an object hit the lower side and return the new velocity in accordance to that.
     * @param collisionPoint  the collided Point
     * @param currentVelocity the velocity at collided time
     * @return the new velocity expected after the hit (based on the force the object inflicted on us).
     */
    private Velocity collidedLowerSide(Point collisionPoint, Velocity currentVelocity) {
        if (rectangle.getLowerSide().isInLine(collisionPoint)) {
            currentVelocity.setDy(-currentVelocity.getDy());
        }

        return currentVelocity;
    }

    /**
     * collidedLeftSide.
     * check if an object hit the left side and return the new velocity in accordance to that.
     * @param collisionPoint  the collided Point
     * @param currentVelocity the velocity at collided time
     * @return the new velocity expected after the hit (based on the force the object inflicted on us).
     */
    private Velocity collidedLeftSide(Point collisionPoint, Velocity currentVelocity) {
        if (rectangle.getLeftSide().isInLine(collisionPoint)) {
            currentVelocity.setDx(-currentVelocity.getDx());
        }

        return currentVelocity;
    }

    /**
     * collidedRightSide.
     * check if an object hit the right side and return the new velocity in accordance to that.
     * @param collisionPoint  the collided Point
     * @param currentVelocity the velocity at collided time
     * @return the new velocity expected after the hit (based on the force the object inflicted on us).
     */
    private Velocity collidedRightSide(Point collisionPoint, Velocity currentVelocity) {
        if (rectangle.getRightSide().isInLine(collisionPoint)) {
            currentVelocity.setDx(-currentVelocity.getDx());
        }

        return currentVelocity;
    }


    /**
     * drawOn.
     * @param d the DrawSurface to draw on.
     */
    public void drawOn(DrawSurface d) {
        //the RectAngle
        d.setColor(this.color);
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY()
        , (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());

        //the border of the Rectangle
        d.setColor(Color.BLACK);
        this.rectangle.getUpperSide().drawLine(d);
        this.rectangle.getLeftSide().drawLine(d);
        this.rectangle.getRightSide().drawLine(d);
        this.rectangle.getLowerSide().drawLine(d);
    }



    /**
     * timePassed.
     * notify the sprite that time has passed, change the Color.
     */
    @Override
    public void timePassed() {

    }

    /**
     * remove this Block from the Game.
     * @param gameLevel the game to remove the Block from
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    /**
     * notify the listeners that hit accord.
     * @param hitter the Ball that hit the Block.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Add hl as a listener to hit events.
     * @param hl the new hit listener.
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl the hit listener to remove.
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}
