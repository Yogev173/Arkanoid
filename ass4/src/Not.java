import java.util.Map;

public class Not extends UnaryExpression {

    private static final String SYMBOL = "~";

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

}
