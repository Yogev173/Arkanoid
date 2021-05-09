import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Tom Ben-Dor.
 */
@SuppressWarnings("CheckStyle")
@DisplayName("Testing")
class TestingAss4 {
    static <E> void assertListEqualsNoOrder(List<E> expected, List<E> actual) {
        assertEquals(expected.size(), actual.size());
        for (E element : expected) {
            assertTrue(actual.contains(element));
        }
    }

    @Test
    @DisplayName("Val")
    void testVal() throws Exception {
        for (String s : List.of("T", "F")) {
            Expression expression = new Val(s.equals("T"));
            assertEquals(s, expression.toString());
            assertEquals(s.equals("T"), expression.evaluate());

            assertListEqualsNoOrder(List.of(), expression.getVariables());

            assertEquals(expression.toString(), expression.norify().toString());
            assertEquals(expression.toString(), expression.nandify().toString());
        }
    }

    @Test
    @DisplayName("Var")
    void testVar() throws Exception {
        Expression expression = new Var("x");
        assertEquals("x", expression.toString());

        assertThrows(Exception.class, expression::evaluate);
        assertListEqualsNoOrder(List.of("x"), expression.getVariables());

        assertEquals(expression.toString(), expression.norify().toString());
        assertEquals(expression.toString(), expression.nandify().toString());

        expression = expression.assign("x", expression);
        assertEquals("x", expression.toString());

        assertTrue(expression.evaluate(Map.of("x", true)));
        assertFalse(expression.evaluate(Map.of("x", false)));

        expression = expression.assign("x", new Val(true));

        assertEquals("T", expression.toString());
        assertTrue(expression.evaluate());
    }

    @Test
    @DisplayName("Not")
    void testNot() throws Exception {
        Expression expression = new Not(new Var("x"));
        assertEquals("~(x)", expression.toString());

        assertThrows(Exception.class, expression::evaluate);
        assertListEqualsNoOrder(List.of("x"), expression.getVariables());

        assertEquals("(x V x)", expression.norify().toString());
        assertEquals("(x A x)", expression.nandify().toString());

        expression = expression.assign("x", expression);
        assertEquals("~(~(x))", expression.toString());

        assertTrue(expression.evaluate(Map.of("x", true)));
        assertFalse(expression.evaluate(Map.of("x", false)));

        expression = expression.assign("x", new Val(true));

        assertEquals("~(~(T))", expression.toString());
        assertTrue(expression.evaluate());
    }

    @Test
    @DisplayName("And")
    void testAnd() throws Exception {
        Expression expression = new And(new Var("x"), new Var("y"));
        assertEquals("(x & y)", expression.toString());

        assertEquals("((x V x) V (y V y))", expression.norify().toString());
        assertEquals("((x A y) A (x A y))", expression.nandify().toString());

        assertEquals(expression.toString(), expression.simplify().toString());
        assertEquals("y", expression.assign("x", new Val(true)).simplify().toString());
        assertEquals("x", expression.assign("y", new Val(true)).simplify().toString());
        assertEquals("F", expression.assign("x", new Val(false)).simplify().toString());
        assertEquals("F", expression.assign("y", new Val(false)).simplify().toString());
        assertEquals("y", expression.assign("x", new Var("y")).simplify().toString());
        assertEquals("x", expression.assign("y", new Var("x")).simplify().toString());

        assertThrows(Exception.class, expression::evaluate);
        assertFalse(expression.assign("x", new Val(false)).evaluate());
        assertListEqualsNoOrder(List.of("x", "y"), expression.getVariables());

        expression = expression.assign("x", expression);
        assertListEqualsNoOrder(List.of("x", "y"), expression.getVariables());
        assertEquals("((x & y) & y)", expression.toString());

        assertFalse(expression.evaluate(Map.of("x", true, "y", false)));
        assertFalse(expression.evaluate(Map.of("x", false, "y", false)));
        assertFalse(expression.evaluate(Map.of("x", false, "y", true)));
        assertTrue(expression.evaluate(Map.of("x", true, "y", true)));

        expression = expression.assign("x", new Val(true));
        assertEquals("y", expression.simplify().toString());
        expression = expression.assign("y", new Val(false));

        assertEquals("((T & F) & F)", expression.toString());
        assertFalse(expression.evaluate());
    }

    @Test
    @DisplayName("Xor")
    void testXor() throws Exception {
        Expression expression = new Xor(new Var("x"), new Var("y"));
        assertEquals("(x ^ y)", expression.toString());

        assertEquals("(((x V x) V (y V y)) V (x V y))", expression.norify().toString());
        assertEquals("((x A (x A y)) A (y A (x A y)))", expression.nandify().toString());

        assertEquals(expression.toString(), expression.simplify().toString());
        assertEquals("~(y)", expression.assign("x", new Val(true)).simplify().toString());
        assertEquals("~(x)", expression.assign("y", new Val(true)).simplify().toString());
        assertEquals("y", expression.assign("x", new Val(false)).simplify().toString());
        assertEquals("x", expression.assign("y", new Val(false)).simplify().toString());
        assertEquals("F", expression.assign("x", new Var("y")).simplify().toString());
        assertEquals("F", expression.assign("y", new Var("x")).simplify().toString());

        assertThrows(Exception.class, expression::evaluate);
        assertListEqualsNoOrder(List.of("x", "y"), expression.getVariables());

        expression = expression.assign("x", expression);
        assertListEqualsNoOrder(List.of("x", "y"), expression.getVariables());
        assertEquals("((x ^ y) ^ y)", expression.toString());

        assertTrue(expression.evaluate(Map.of("x", true, "y", false)));
        assertFalse(expression.evaluate(Map.of("x", false, "y", false)));
        assertFalse(expression.evaluate(Map.of("x", false, "y", true)));
        assertTrue(expression.evaluate(Map.of("x", true, "y", true)));

        expression = expression.assign("x", new Val(true));
        assertEquals("(~(y) ^ y)", expression.simplify().toString());
        expression = expression.assign("y", new Val(false));

        assertEquals("((T ^ F) ^ F)", expression.toString());
        assertTrue(expression.evaluate());
    }

    @Test
    @DisplayName("Or")
    void testOr() throws Exception {
        Expression expression = new Or(new Var("x"), new Var("y"));
        assertEquals("(x | y)", expression.toString());

        assertEquals("((x V y) V (x V y))", expression.norify().toString());
        assertEquals("((x A x) A (y A y))", expression.nandify().toString());

        assertEquals(expression.toString(), expression.simplify().toString());
        assertEquals("T", expression.assign("x", new Val(true)).simplify().toString());
        assertEquals("T", expression.assign("y", new Val(true)).simplify().toString());
        assertEquals("y", expression.assign("x", new Val(false)).simplify().toString());
        assertEquals("x", expression.assign("y", new Val(false)).simplify().toString());
        assertEquals("y", expression.assign("x", new Var("y")).simplify().toString());
        assertEquals("x", expression.assign("y", new Var("x")).simplify().toString());

        assertThrows(Exception.class, expression::evaluate);
        assertTrue(expression.assign("x", new Val(true)).evaluate());
        assertListEqualsNoOrder(List.of("x", "y"), expression.getVariables());

        expression = expression.assign("x", expression);

        assertEquals("((x | y) | y)", expression.toString());
        assertListEqualsNoOrder(List.of("x", "y"), expression.getVariables());

        assertTrue(expression.evaluate(Map.of("x", true, "y", false)));
        assertFalse(expression.evaluate(Map.of("x", false, "y", false)));
        assertTrue(expression.evaluate(Map.of("x", false, "y", true)));
        assertTrue(expression.evaluate(Map.of("x", true, "y", true)));

        expression = expression.assign("x", new Val(true));
        assertEquals("T", expression.simplify().toString());
        expression = expression.assign("y", new Val(false));

        assertEquals("((T | F) | F)", expression.toString());
        assertTrue(expression.evaluate());
    }

    @Test
    @DisplayName("Nor")
    void testNor() throws Exception {
        Expression expression = new Nor(new Var("x"), new Var("y"));
        assertEquals("(x V y)", expression.toString());

        assertEquals("(x V y)", expression.norify().toString());
        assertEquals("(((x A x) A (y A y)) A ((x A x) A (y A y)))", expression.nandify().toString());

        assertEquals(expression.toString(), expression.simplify().toString());
        assertEquals("F", expression.assign("x", new Val(true)).simplify().toString());
        assertEquals("F", expression.assign("y", new Val(true)).simplify().toString());
        assertEquals("~(y)", expression.assign("x", new Val(false)).simplify().toString());
        assertEquals("~(x)", expression.assign("y", new Val(false)).simplify().toString());
        assertEquals("~(y)", expression.assign("x", new Var("y")).simplify().toString());
        assertEquals("~(x)", expression.assign("y", new Var("x")).simplify().toString());

        assertThrows(Exception.class, expression::evaluate);
        assertFalse(expression.assign("x", new Val(true)).evaluate());
        assertListEqualsNoOrder(List.of("x", "y"), expression.getVariables());

        expression = expression.assign("x", expression);
        assertListEqualsNoOrder(List.of("x", "y"), expression.getVariables());
        assertEquals("((x V y) V y)", expression.toString());

        assertTrue(expression.evaluate(Map.of("x", true, "y", false)));
        assertFalse(expression.evaluate(Map.of("x", false, "y", false)));
        assertFalse(expression.evaluate(Map.of("x", false, "y", true)));
        assertFalse(expression.evaluate(Map.of("x", true, "y", true)));

        expression = expression.assign("x", new Val(true));
        assertEquals("~(y)", expression.simplify().toString());
        expression = expression.assign("y", new Val(false));

        assertEquals("((T V F) V F)", expression.toString());
        assertTrue(expression.evaluate());
    }

    @Test
    @DisplayName("Nand")
    void testNand() throws Exception {
        Expression expression = new Nand(new Var("x"), new Var("y"));
        assertEquals("(x A y)", expression.toString());

        assertEquals("(((x V x) V (y V y)) V ((x V x) V (y V y)))", expression.norify().toString());
        assertEquals("(x A y)", expression.nandify().toString());

        assertEquals(expression.toString(), expression.simplify().toString());
        assertEquals("~(y)", expression.assign("x", new Val(true)).simplify().toString());
        assertEquals("~(x)", expression.assign("y", new Val(true)).simplify().toString());
        assertEquals("T", expression.assign("x", new Val(false)).simplify().toString());
        assertEquals("T", expression.assign("y", new Val(false)).simplify().toString());
        assertEquals("~(y)", expression.assign("x", new Var("y")).simplify().toString());
        assertEquals("~(x)", expression.assign("y", new Var("x")).simplify().toString());

        assertThrows(Exception.class, expression::evaluate);
        assertTrue(expression.assign("x", new Val(false)).evaluate());
        assertListEqualsNoOrder(List.of("x", "y"), expression.getVariables());

        expression = expression.assign("x", expression);
        assertListEqualsNoOrder(List.of("x", "y"), expression.getVariables());
        assertEquals("((x A y) A y)", expression.toString());

        assertTrue(expression.evaluate(Map.of("x", true, "y", false)));
        assertTrue(expression.evaluate(Map.of("x", false, "y", false)));
        assertFalse(expression.evaluate(Map.of("x", false, "y", true)));
        assertTrue(expression.evaluate(Map.of("x", true, "y", true)));

        expression = expression.assign("x", new Val(true));
        assertEquals("(~(y) A y)", expression.simplify().toString());
        expression = expression.assign("y", new Val(false));

        assertEquals("((T A F) A F)", expression.toString());
        assertTrue(expression.evaluate());
    }

    @Test
    @DisplayName("Xnor")
    void testXnor() throws Exception {
        Expression expression = new Xnor(new Var("x"), new Var("y"));
        assertEquals("(x # y)", expression.toString());

        assertEquals("((x V (x V y)) V (y V (x V y)))", expression.norify().toString());
        assertEquals("(((x A x) A (y A y)) A (x A y))", expression.nandify().toString());

        assertEquals(expression.toString(), expression.simplify().toString());
        assertEquals("T", expression.assign("x", new Var("y")).simplify().toString());
        assertEquals("T", expression.assign("y", new Var("x")).simplify().toString());

        assertThrows(Exception.class, expression::evaluate);
        assertListEqualsNoOrder(List.of("x", "y"), expression.getVariables());

        expression = expression.assign("x", expression);
        assertListEqualsNoOrder(List.of("x", "y"), expression.getVariables());
        assertEquals("((x # y) # y)", expression.toString());

        assertTrue(expression.evaluate(Map.of("x", true, "y", false)));
        assertFalse(expression.evaluate(Map.of("x", false, "y", false)));
        assertFalse(expression.evaluate(Map.of("x", false, "y", true)));
        assertTrue(expression.evaluate(Map.of("x", true, "y", true)));

        expression = expression.assign("x", new Val(true));
        expression = expression.assign("y", new Val(false));

        assertEquals("((T # F) # F)", expression.toString());
        assertTrue(expression.evaluate());
    }
}