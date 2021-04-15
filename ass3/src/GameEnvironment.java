import java.util.ArrayList;
import java.util.List;

public class GameEnvironment {

    private List<Collidable> collidableList = new ArrayList<Collidable>();

    // add the given collidable to the environment.

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
        CollisionInfo collisionInfo =
        for (Collidable c : this.collidableList) {

        }


    }

}