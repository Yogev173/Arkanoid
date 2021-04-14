import java.util.Comparator;

/**
 * @author yogev abarbanel
 * Id: 326116910
 * compare tow point in relation to a Point
 */
public class PointByDistance implements Comparator {
    private Point relationPoint;

    /**
     * Constructor.
     * @param relationPoint the point to check the distance to
     */
    public PointByDistance(Point relationPoint) {
        this.relationPoint = relationPoint;
    }

    /**
     * compare.
     * @param point1 first point to compare
     * @param point2 second point to compare
     * @return -2 if the Object aren't Point.
     *          -1 if point1 < point2
     *          1 if point1 > point2
     *          0 if point1 = point2
     */
    @Override
    public int compare(Object point1 , Object point2) {
        if (point1 instanceof Point && point2 instanceof Point) {
            if (((Point) point1).distance(relationPoint) < ((Point) point2).distance(relationPoint)) {
                return -1;
            } else if (((Point) point1).distance(relationPoint) > ((Point) point2).distance(relationPoint)) {
                return 1;
            } else {
                return 0;
            }
        } else {
            return -2;
        }
    }
}
