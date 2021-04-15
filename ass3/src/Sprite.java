import biuoop.DrawSurface;

public interface Sprite {

    /**
     * drawOn
     * @param d the DrawSurface to draw on.
     */
    void drawOn(DrawSurface d);

    /**
     * timePassed.
     * notify the sprite that time has passed
     */
    void timePassed();
}