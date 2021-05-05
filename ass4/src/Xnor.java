import java.util.Map;

public class Xnor extends BinaryExpression {

    private static final String SYMBOL = "#";

    private Not expression;

    public Xnor(Expression leftExpression, Expression rightExpression) {
        super(leftExpression, rightExpression, SYMBOL);
        this.expression = new Not(new Xor(this.getLeftExpression(), this.getRightExpression()));
    }

    /**
     * @param assignment the map that match var to his value.
     * @return the result of the evaluate expression.
     * @throws Exception If the expression contains a variable which is not in the assignment, an exception is thrown.
     */
    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return this.expression.evaluate(assignment);
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
        return new Xnor(this.getLeftExpression().assign(var, expression),
                this.getRightExpression().assign(var, expression));
    }

    /**
     * @return the expression tree resulting from converting all the operations to the logical Nand operation.
     */
    @Override
    public Expression nandify() {
        return expression.nandify();
    }

    /**
     * @return the expression tree resulting from converting all the operations to the logical Nor operation
     */
    @Override
    public Expression norify() {
        return expression.norify();
    }
}