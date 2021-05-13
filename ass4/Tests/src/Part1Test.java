import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part1Test {

    public static void main(String[] args) {
        Expression e2 = new Not(new Not(new Nand(new Var("x"), new Var("y"))));
        System.out.println(e2.toString());
        System.out.println(e2.simplify().simplify());




    }
}
