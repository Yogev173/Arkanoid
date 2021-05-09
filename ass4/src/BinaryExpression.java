import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

/**
 * @author yogev abarbanel
 * Binary operator Expression.
 */
public abstract class BinaryExpression extends BaseExpression {
    private Expression leftExpression;
    private Expression rightExpression;
    private String symbol;

    /**
     * Constructor.
     * @param leftExpression the left boolean Expression of the entire And
     * @param rightExpression the right boolean Expression of the entire And
     * @param symbol the sign of the operator
     */
    public BinaryExpression(Expression leftExpression, Expression rightExpression, String symbol) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
        this.symbol = symbol;
    }

    /**
     * @return the result of the evaluate expression.
     * @throws Exception If the expression contains a variable.
     */
    @Override
    public Boolean evaluate() throws Exception {
        return this.evaluate(new HashMap<String, Boolean>());
    }

    /**
     * @return a list of the variables in the expression.
     */
    @Override
    public List<String> getVariables() {
        Set<String> variableSet = new HashSet<>();
        variableSet.addAll(this.leftExpression.getVariables());
        variableSet.addAll(this.rightExpression.getVariables());
        return new ArrayList<String>(variableSet);
    }

    /**
     * @return string representation of the expression.
     */
    @Override
    public String toString() {
        return ("(" + this.leftExpression.toString() + " " + symbol + " " + this.rightExpression.toString() + ")");
    }

    /**
     * @return the left Expression
     */
    protected Expression getLeftExpression() {
        return leftExpression;
    }

    /**
     * @return the right Expression
     */
    protected Expression getRightExpression() {
        return rightExpression;
    }

    /**
     * @param expression the Expression to compare to.
     * @return true if the Expression equals.
     */
    @Override
    public boolean equals(Object expression) {
        if (!this.getClass().equals(expression.getClass())) {
            return false;
        } else if (this.rightExpression.equals(((BinaryExpression) expression).rightExpression)
                && this.leftExpression.equals(((BinaryExpression) expression).leftExpression)) {
            return true;
        } else if (this.rightExpression.equals(((BinaryExpression) expression).leftExpression)
                && this.leftExpression.equals(((BinaryExpression) expression).rightExpression)) {
            return true;
        }

        return false;
    }
}
