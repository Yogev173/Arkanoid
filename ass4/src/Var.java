import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Var implements Expression{

    private String variable;

    public Var(String variable) {
        this.variable = variable;
    }

    /**
     * @param assignment the map that match var to his value.
     * @return the result of the evaluate expression.
     * @throws Exception If the expression contains a variable which is not in the assignment, an exception is thrown.
     */
    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        if (!assignment.containsKey(this.variable)) {
            throw new Exception("variable" + this.variable + "isn't in assignment");
        }

        return assignment.get(this.variable);
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
        List<String> list = new ArrayList<String>();
        list.add(this.variable);
        return list;
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
        if (var.equals(this.variable)) {
            return expression;
        }

        return new Var(this.variable);
    }

    /**
     * @return string representation of the expression.
     */
    @Override
    public String toString() {
        return this.variable;
    }

    /**
     * @return the expression tree resulting from converting all the operations to the logical Nand operation.
     */
    @Override
    public Expression nandify() {
        return new Var(this.variable);
    }

    /**
     * @return the expression tree resulting from converting all the operations to the logical Nor operation
     */
    @Override
    public Expression norify() {
        return new Var(this.variable);
    }
}
