import java.util.List;
import java.util.Map;

public interface Expression {
    /**
     * @param assignment the map that match var to his value.
     * @return the result of the evaluate expression.
     * @throws Exception  If the expression contains a variable which is not in the assignment, an exception is thrown.
     */
    Boolean evaluate(Map<String, Boolean> assignment) throws Exception;

    /**
     * @return the result of the evaluate expression.
     * @throws Exception If the expression contains a variable.
     */
    Boolean evaluate() throws Exception;

    /**
     * @return a list of the variables in the expression.
     */
    List<String> getVariables();

    /**
     * @return string representation of the expression.
     */
    String toString();

    /**
     * create a new expression in which all occurrences of the variable
     * var are replaced with the provided expression (Does not modify the current expression).
     * @param var the variable to replace.
     * @param expression the expression to replace the var with.
     * @return the new expression.
     */
    Expression assign(String var, Expression expression);


}