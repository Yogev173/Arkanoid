import biuoop.DrawSurface;

public class Block implements Collidable {

    private Rectangle rectangle;
    private boolean isFrameBlock;
    private boolean isBeenCollided = false;

    /**
     * Constructor.
     * @param rectangle the Block (shape)
     */
    public Block(Rectangle rectangle) {
        this(rectangle, false);
    }

    /**
     * Constructor.
     * @param rectangle the Block (shape)
     * @param isFrameBlock if this Block is a frame Block
     */
    public Block(Rectangle rectangle, boolean isFrameBlock) {
        this.rectangle = rectangle;
        this.isFrameBlock = isFrameBlock;
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
     * @return the new velocity expected after the hit (based on the force the object inflicted on us).
     */
    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        currentVelocity = collidedUpperSide(collisionPoint, currentVelocity);
        currentVelocity = collidedLowerSide(collisionPoint, currentVelocity);
        currentVelocity = collidedLeftSide(collisionPoint, currentVelocity);
        currentVelocity = collidedRightSide(collisionPoint, currentVelocity);
        return currentVelocity;
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
            this.isBeenCollided = true;
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
            this.isBeenCollided = true;
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
            this.isBeenCollided = true;
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
            this.isBeenCollided = true;
        }

        return currentVelocity;
    }

    public void drawOn(DrawSurface d) {
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY()
        , (int) this.rectangle.getLowerRight().getX(), (int) this.rectangle.getLowerRight().getY());
    }
}
