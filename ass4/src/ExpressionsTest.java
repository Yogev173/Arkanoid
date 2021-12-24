import java.util.HashMap;
import java.util.Map;

/**
 * @author yogev abarbanel.
 * Test for Expression interface.
 */
public class ExpressionsTest {

    /**
     * main.
     * Test for Expression interface.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        //1
        Expression expression = new And(new And(new Var("x"),
                new Var("y")), new And(new Var("z"), new Val(true)));
        //2
        System.out.println(expression.toString());
        //3
        Map<String, Boolean> map = new HashMap<>();
        map.put("x", true);
        map.put("y", false);
        map.put("z", true);
        try {
            System.out.println(expression.evaluate(map));
        } catch (Exception e) {
            System.out.println("couldn't evaluate");
        }
        //4
        System.out.println(expression.nandify());
        //5
        System.out.println(expression.norify());
        //6
        System.out.println(expression.simplify());
    }
}
