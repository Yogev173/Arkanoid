import java.util.*;

public abstract class UnaryExpression extends BaseExpression {

    private Expression expression;
    private String symbol;

    public UnaryExpression(Expression expression, String symbol) {
        this.expression = expression;
        this.symbol = symbol;
    }

    /**
     * @return the result of the evaluate expression.
     * @throws Exception If the expression contains a variable.
     */
    @Override
    public Boolean evaluate() throws Exception {
        return this.evaluate(new HashMap<>());
    }

    /**
     * @return a list of the variables in the expression.
     */
    @Override
    public List<String> getVariables() {
        Set<String> variableSet = new HashSet<>();
        variableSet.addAll(this.expression.getVariables());
        return new ArrayList<String>(variableSet);
    }

    /**
     * @return string representation of the expression.
     */
    @Override
    public String toString() {
        return (symbol + "(" + this.expression.toString() + ")");
    }

    protected Expression getExpression() {
        return expression;
    }
}


