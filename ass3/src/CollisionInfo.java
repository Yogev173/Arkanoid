
/**
 * @author yogev abarbanel
 * Id: 326116910
 * Information about a collision.
 */
public class CollisionInfo {

    private Point collisionPoint;
    private Collidable collisionObject;

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
     * @param collisionPoint the new collisionPoint.
     */
    public void setCollisionPoint(Point collisionPoint) {
        this.collisionPoint = collisionPoint;
    }

    /**
     * setCollisionObject.
     * @param collisionObject the new collisionObject.
     */
    public void setCollisionObject(Collidable collisionObject) {
        this.collisionObject = collisionObject;
    }
}