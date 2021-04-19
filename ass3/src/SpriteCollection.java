import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;


/**
 * @author yogev abarbanel
 * Id: 326116910
 * one Sprite Collection.
 */
public class SpriteCollection {

    private List<Sprite> spriteList = new ArrayList<Sprite>();

    /**
     * addSprite.
     * @param s the new Sprite to add.
     */
    public void addSprite(Sprite s) {
        this.spriteList.add(s);
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
        for (Sprite sprite : this.spriteList) {
            sprite.timePassed();
        }
    }

    /**
     * drawAllOn.
     * call drawOn(d) on all sprites.
     * @param d the DrawSurface.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : this.spriteList) {
            sprite.drawOn(d);
        }
    }
}