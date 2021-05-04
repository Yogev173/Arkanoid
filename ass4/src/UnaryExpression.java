import java.util.*;

public abstract class UnaryExpression extends BaseExpression {

    private  Expression expression;

    public UnaryExpression(Expression expression) {
        this.expression = expression;
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

    protected Expression getExpression() {
        return expression;
    }
}


