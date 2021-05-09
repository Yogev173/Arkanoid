import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yogev abarbanel
 * Valubale Expression.
 */
public class Val implements Expression {
    private boolean value;

    /**
     * Constructor.
     * @param value the boolean value
     */
    public Val(boolean value) {
        this.value = value;
    }

    /**
     * @param assignment the map that match var to his value.
     * @return the result of the evaluate expression.
     * @throws Exception If the expression contains a variable which is not in the assignment, an exception is thrown.
     */
    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return this.value ? true : false;
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
        return new ArrayList<String>();
    }

    /**
     * create a new expression in which all occurrences of the variable var are replaced with the provided expression
     * (Does not modify the current expression).
     * @param var the variable to replace.
     * @param expression the expression to replace the var with.
     * @return the new expression.
     */
    @Override
    public Expression assign(String var, Expression expression) {
        return new Val(this.value);
    }

    /**
     * @return string representation of the expression.
     */
    @Override
    public String toString() {
        if (this.value) {
            return "T";
        }

        return "F";
    }

    /**
     * @return the expression tree resulting from converting all the operations to the logical Nand operation.
     */
    @Override
    public Expression nandify() {
        return new Val(this.value);
    }

    /**
     * @return the expression tree resulting from converting all the operations to the logical Nor operation
     */
    @Override
    public Expression norify() {
        return new Val(this.value);
    }

    /**
     * @return a simplified version of the current expression.
     */
    @Override
    public Expression simplify() {
        return new Val(this.value);
    }

    /**
     * @param expression the Expression to compare to.
     * @return true if the Expression equals.
     */
    @Override
    public boolean equals(Object expression) {
        if (!this.getClass().equals(expression.getClass())) {
            return false;
        } else if (this.value == ((Val) expression).value) {
            return true;
        }

        return false;
    }
}
