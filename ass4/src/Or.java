import java.util.Map;

/**
 * @author yogev abarbanel
 * Or Expression.
 */
public class Or extends BinaryExpression {

    private static final String SYMBOL = "|";

    /**
     * Constructor.
     * @param leftExpression the left boolean Expression of the entire And
     * @param rightExpression the right boolean Expression of the entire And
     */
    public Or(Expression leftExpression, Expression rightExpression) {
        super(leftExpression, rightExpression, SYMBOL);
    }

    /**
     * @param assignment the map that match var to his value.
     * @return the result of the evaluate expression.
     * @throws Exception If the expression contains a variable which is not in the assignment, an exception is thrown.
     */
    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        Expression simplifyLeftExpression = this.getLeftExpression().simplify();
        Expression simplifyRightExpression = this.getRightExpression().simplify();


        try {
            boolean leftExpressionEvaluate = simplifyLeftExpression.evaluate(assignment);
            if (leftExpressionEvaluate) {
                return true;
            }
        } catch (Exception varNotInMapLeft) {
            try {
                boolean rightExpressionEvaluate = simplifyRightExpression.evaluate(assignment);
                if (rightExpressionEvaluate) {
                    return true;
                }
            } catch (Exception varNotInMapRight) {
                throw varNotInMapLeft;
            }
        }

        //Left Expression is false, so the right determine the outcome
        return simplifyRightExpression.evaluate(assignment);
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
        return new Or(this.getLeftExpression().assign(var, expression),
                this.getRightExpression().assign(var, expression));
    }

    /**
     * @return the expression tree resulting from converting all the operations to the logical Nand operation.
     */
    @Override
    public Expression nandify() {
        Expression left = this.getLeftExpression().nandify();
        Expression right = this.getRightExpression().nandify();
        return new Nand(new Nand(left, left), new Nand(right, right));
    }

    /**
     * @return the expression tree resulting from converting all the operations to the logical Nor operation
     */
    @Override
    public Expression norify() {
        Expression left = this.getLeftExpression().norify();
        Expression right = this.getRightExpression().norify();
        return new Nor(new Nor(left, right), new Nor(left, right));
    }

    /**
     * @return a simplified version of the current expression.
     */
    @Override
    public Expression simplify() {
        Expression leftExpression = this.getLeftExpression().simplify();
        Expression rightExpression = this.getRightExpression().simplify();

        if (leftExpression.equals(rightExpression)) {
            return leftExpression;
        } else if (leftExpression.toString().equals("F")) {
            return rightExpression;
        } else if (rightExpression.toString().equals("F")) {
            return leftExpression;
        } else if (leftExpression.toString().equals("T") || rightExpression.toString().equals("T")) {
            return new Val(true);
        } else {
            return new Or(leftExpression, rightExpression);
        }
    }
}
