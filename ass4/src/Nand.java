import java.util.Map;

/**
 * @author yogev abarbanel
 * Nand Expression.
 */
public class Nand extends BinaryExpression {

    private static final String SYMBOL = "↑";

    private Not innerExpression;

    /**
     * Constructor.
     * @param leftExpression the left boolean Expression of the entire And
     * @param rightExpression the right boolean Expression of the entire And
     */
    public Nand(Expression leftExpression, Expression rightExpression) {
        super(leftExpression, rightExpression, SYMBOL);
        this.innerExpression = new Not(new And(this.getLeftExpression(), this.getRightExpression()));
    }

    /**
     * @param assignment the map that match var to his value.
     * @return the result of the evaluate expression.
     * @throws Exception If the expression contains a variable which is not in the assignment, an exception is thrown.
     */
    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return this.innerExpression.evaluate(assignment);
    }

    /**
     * create a new expression in which all occurrences of the variable
     * var are replaced with the provided expression (Does not modify the current expression).
     * @param var the variable to replace.
     * @param expression the expression to replace the var with.
     * @return the new expression.
     */
    @Override
    public Expression assign(String var, Expression expression) {
        return new Nand(this.getLeftExpression().assign(var, expression),
                this.getRightExpression().assign(var, expression));
    }

    /**
     * @return the expression tree resulting from converting all the operations to the logical Nand operation.
     */
    @Override
    public Expression nandify() {
        Expression left = this.getLeftExpression().nandify();
        Expression right = this.getRightExpression().nandify();
        return new Nand(left, right);
    }

    /**
     * @return the expression tree resulting from converting all the operations to the logical Nor operation
     */
    @Override
    public Expression norify() {
        Expression left = this.getLeftExpression().norify();
        Expression right = this.getRightExpression().norify();
        return new Nor(new Nor(new Nor(left, left), new Nor(right, right)),
                new Nor(new Nor(left, left), new Nor(right, right)));
    }

    /**
     * @return a simplified version of the current expression.
     */
    @Override
    public Expression simplify() {
        Expression leftExpression = this.getLeftExpression().simplify();
        Expression rightExpression = this.getRightExpression().simplify();

        if (leftExpression.toString().equals(rightExpression.toString())) {
            return new Not(leftExpression);
        } else if (leftExpression.toString().equals("T")) {
            return new Not(rightExpression);
        } else if (rightExpression.toString().equals("T")) {
            return new Not(leftExpression);
        } else if (leftExpression.toString().equals("F") || rightExpression.toString().equals("F")) {
            return new Val(true);
        } else {
            return new Nand(leftExpression, rightExpression);
        }
    }
}
