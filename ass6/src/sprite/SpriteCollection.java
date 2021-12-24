package sprite;

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
     * notifyAllTimePassed.
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> listCopy = new ArrayList<Sprite>(this.spriteList);

        for (Sprite sprite : listCopy) {
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

    /**
     * remove Sprite.
     * @param s the Sprite to remove from sprites.
     */
    public void removeSprite(Sprite s) {
        this.spriteList.remove(s);
    }
}