import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part1Test {

    public static void main(String[] args) {
        Expression e2 = new Xor(new And(new Var("x"), new Var("y")), new Val(true));

        String s = e2.toString();
        System.out.println(s);

        List<String> vars = e2.getVariables();
        for (String v : vars) {
            System.out.println(v);
        }

        Expression e3 = e2.assign("y", e2);
        System.out.println(e3);
        // ((x & ((x & y) ^ T)) ^ T)
        e3 = e3.assign("x", new Val(false));
        System.out.println(e3);
        // ((F & ((F & y) ^ T)) ^ T)

        Expression e4 = new Not(new Not(new Or(new Xor(new Val(true), new Val(true)), new And(new Val(true), new Val(true)))));
        System.out.println(e4.toString());
        try {
            System.out.println(e4.evaluate());
        } catch (Exception e) {

        }

        Expression e5 = new Not(new Not(new Or(new Xor(new Val(true), new Var("x")), new And(new Var("y"), new Val(true)))));
        System.out.println(e5.toString());
        Map<String, Boolean> map = new HashMap<>();
        map.put("x", true);
        map.put("y", false);
        try {
            System.out.println(e5.evaluate());
        } catch (Exception e) {
            try {
                System.out.println(e5.toString());
                System.out.println(e5.evaluate(map));
                System.out.println(e5.toString());
                Expression e6 = e5.assign("x", new Val(true));
                System.out.println(e6.toString());
                System.out.println(e6.evaluate(map));
                System.out.println(e6.toString());
                System.out.println(e5.toString());
            } catch (Exception ee) {

            }

        }


        Expression e = new Xor(new Var("x"), new Var("y"));
        System.out.println(e.nandify());
        System.out.println(e.norify());
        // should print:
        // ((x ↑ (x ↑ y)) ↑ (y ↑ (x ↑ y)))
        // (((x ↓ x) ↓ (y ↓ y)) ↓ (x ↓ y))




    }
}
