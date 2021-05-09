import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

/**
 * @author yogev abarbanel
 * unary operator Expression.
 */
public abstract class UnaryExpression extends BaseExpression {

    private Expression expression;
    private String symbol;

    /**
     * Constructor.
     * @param expression the Expression inside the unary operator
     * @param symbol the sign of the unary operator
     */
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

    /**
     * @return the inner Expression.
     */
    protected Expression getExpression() {
        return expression;
    }

    /**
     * @param otherExpression the Expression to compare to.
     * @return true if the Expression equals.
     */
    @Override
    public boolean equals(Object otherExpression) {
        if (!this.getClass().equals(otherExpression.getClass())) {
            return false;
        } else if (this.expression.equals(((UnaryExpression) expression).expression)) {
            return true;
        }

        return false;
    }
}

