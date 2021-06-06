package collision;

import geometry.shape.Point;

/**
 * @author yogev abarbanel
 * Id: 326116910
 * Information about a collision.
 */
public class CollisionInfo {

    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * Constructor.
     * @param collisionPoint the Point the collision accor in.
     * @param collisionObject the Object that the collision accor with.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * collisionPoint.
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * collisionObject.
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }

    /**
     * setCollisionPoint.
     * @param newCollisionPoint the new collisionPoint.
     */
    public void setCollisionPoint(Point newCollisionPoint) {
        this.collisionPoint = newCollisionPoint;
    }

    /**
     * setCollisionObject.
     * @param newCollisionObject the new collisionObject.
     */
    public void setCollisionObject(Collidable newCollisionObject) {
        this.collisionObject = newCollisionObject;
    }
}