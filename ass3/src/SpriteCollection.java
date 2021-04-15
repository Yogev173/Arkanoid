import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

public class SpriteCollection {

    private List<Sprite> spiriteList = new ArrayList<Sprite>();

    /**
     * addSprite.
     * @param s the new Sprite to add.
     */
    public void addSprite(Sprite s) {
        this.spiriteList.add(s);
    }

    /**
     * addMultipalSprite.
     * @param spiriteList List of Sprite Object to add.
     */
    public void addMultipalSprite(List<Sprite> spiriteList) {
        for (Sprite sprite : spiriteList) {
            this.addSprite(sprite);
        }
    }

    /**
     * notifyAllTimePassed.
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        for (Sprite sprite : this.spiriteList) {
            sprite.timePassed();
        }
    }

    /**
     * drawAllOn.
     * call drawOn(d) on all sprites.
     * @param d the DrawSurface.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : this.spiriteList) {
            sprite.drawOn(d);
        }
    }
}