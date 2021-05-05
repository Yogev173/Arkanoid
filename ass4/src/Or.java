import java.util.Map;

public class Or extends BinaryExpression {

    private static final String SYMBOL = "|";

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
        Boolean leftExpressionEvaluate = true;
        try {
            leftExpressionEvaluate = this.getLeftExpression().evaluate(assignment);
            if (leftExpressionEvaluate == true) {
                return true;
            }
        } catch (Exception varNotInMapLeft) {
            try {
                Boolean rightExpressionEvaluate = this.getRightExpression().evaluate(assignment);
                if (rightExpressionEvaluate == true) {
                    return true;
                }
            } catch (Exception varNotInMapRight) {
                throw varNotInMapLeft;
            }
        }

        return this.getRightExpression().evaluate(assignment);
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
        return new Nor(new Nor(left, left), new Nor(right, right));
    }
}
