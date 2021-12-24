import biuoop.DrawSurface;

/**
 * @author yogev abarbanel
 * Id: 326116910
 * Collidable object.
 */
public interface Collidable {
    /**
     * getCollisionRectangle.
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * hit.
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * @param collisionPoint the collided Point
     * @param currentVelocity the velocity at collided time
     * @return the new velocity expected after the hit (based on the force the object inflicted on us).
     */
    Velocity hit(Point collisionPoint, Velocity currentVelocity);


    /**
     * drawOn.
     * @param d the DrawSurface to draw on.
     */
    void drawOn(DrawSurface d);

    /**
     * addToGame.
     * add a Collidable to the Game environment.
     * @param g the game Object.
     */
    void addToGame(Game g);
}