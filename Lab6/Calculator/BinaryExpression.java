public abstract class BinaryExpression implements Expression  {
    private Expression lft;
    private Expression rht;
    private String operator;
    public BinaryExpression(Expression lft, Expression rht, String operator) {
        this.lft = lft;
        this.rht = rht;
        this.operator = operator;
    }
    public String toString()
    {
        return "(" + lft + operator + rht + ")";
    }

    public Expression getLft() {
        return lft;
    }

    public Expression getRht() {
        return rht;
    }

    public double evaluate(final Bindings bindings)
    {

        double lftEval = lft.evaluate(bindings);
        double rhtEval = rht.evaluate(bindings);
        return _applyOperator(lftEval, rhtEval);
    }

    protected abstract double _applyOperator(double lft, double rht);
}
