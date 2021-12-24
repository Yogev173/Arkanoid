import java.util.Map;

/**
 * @author yogev abarbanel
 * Not Expression.
 */
public class Not extends UnaryExpression {

    private static final String SYMBOL = "~";

    /**
     * Constructor.
     * @param expression the Expression the inside the Not.
     */
    public Not(Expression expression) {
        super(expression, SYMBOL);
    }

    /**
     * @param assignment the map that match var to his value.
     * @return the result of the evaluate expression.
     * @throws Exception If the expression contains a variable which is not in the assignment, an exception is thrown.
     */
    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return (!this.getExpression().evaluate(assignment));
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
        return new Not(this.getExpression().assign(var, expression));
    }

    /**
     * @return the expression tree resulting from converting all the operations to the logical Nand operation.
     */
    @Override
    public Expression nandify() {
        return new Nand(this.getExpression().nandify(), this.getExpression().nandify());
    }

    /**
     * @return the expression tree resulting from converting all the operations to the logical Nor operation
     */
    @Override
    public Expression norify() {
        return new Nor(this.getExpression().norify(), this.getExpression().norify());
    }

    /**
     * @return a simplified version of the current expression.
     */
    @Override
    public Expression simplify() {
        Expression expression = this.getExpression().simplify();
        Boolean expressionValue;

        try {
            expressionValue = expression.evaluate();

            if (expressionValue) {
                return new Val(false);
            } else {
                return new Val(true);
            }
        } catch (Exception e) {
            return new Not(expression);
        }


    }
}
