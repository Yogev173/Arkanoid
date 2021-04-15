import java.util.ArrayList;
import java.util.List;

public class GameEnvironment {

    private List<Collidable> collidableList;

    /**
     * Constructor.
     */
    public GameEnvironment() {
        this.collidableList = new ArrayList<Collidable>();
    }

    /**
     * Constructor.
     * @param collidableList the List of Object type-Collidable the Ball can collide.
     */
    public GameEnvironment(List<Collidable> collidableList) {
        this.collidableList = collidableList;
    }

    /**
     * addCollidable.
     * add a Collidable Object to collidableList.
     * @param c the new Collidable Object
     */
    public void addCollidable(Collidable c) {
        if (c == null) {
            return;
        }

        collidableList.add(c);
    }

    /**
     * getClosestCollision
     * @param trajectory the route of the ball, without any obstacle.
     * @return If this object will not collide with any of the collidables in this collection, return null.
     *         Else, return the information about the closest collision that is going to occur.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        if (this.collidableList == null) {
            return null;
        }

        //Founding the closest collision
        Point newCollisionPoint = null;
        Point closetCollisionPoint = null;
        CollisionInfo collisionInfo = new CollisionInfo(null, null);
        for (Collidable c : this.collidableList) {
            newCollisionPoint = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            if (newCollisionPoint == null) {
                continue;
            } else if (closetCollisionPoint == null || trajectory.start().distance(closetCollisionPoint) >
                    trajectory.start().distance(newCollisionPoint)) {
                collisionInfo.setCollisionPoint(newCollisionPoint);
                collisionInfo.setCollisionObject(c);
                closetCollisionPoint = newCollisionPoint;
            }
        }

        if (closetCollisionPoint == null) {
            return null;
        } else {
            return collisionInfo;
        }

    }

    /**
     * getCollidableList.
     * @return the collidable list
     */
    public List<Collidable> getCollidableList() {
        return collidableList;
    }

    /**
     * setCollidableList.
     * @param collidableList the collidable list.
     */
    public void setCollidableList(List<Collidable> collidableList) {
        this.collidableList = collidableList;
    }
}