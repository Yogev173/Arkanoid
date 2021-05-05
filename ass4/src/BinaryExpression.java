import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public abstract class BinaryExpression extends BaseExpression {
    private Expression leftExpression;
    private Expression rightExpression;
    private String symbol;

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
     * set the left Expression.
     * @param leftExpression the left Expression
     */
    protected void setLeftExpression(Expression leftExpression) {
        this.leftExpression = leftExpression;
    }


    /**
     * @return the right Expression
     */
    protected Expression getRightExpression() {
        return rightExpression;
    }


    /**
     * set the right Expression.
     * @param rightExpression the right Expression
     */
    protected void setRightExpression(Expression rightExpression) {
        this.rightExpression = rightExpression;
    }
}
