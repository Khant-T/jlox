package com.craftinginterpreters.lox;

public class Interpreter implements Expr.Visitor<Object>
{
    private Object evaluate(Expr expr)
    {
        return expr.accept(this);
    }

    @Override
    public Object visitLiteralExpr(Expr.Literal expr)
    {
        return expr.value;
    }

    @Override
    public Object visitUnaryExpr(Expr.Unary expr)
    {
        Object right = evaluate(expr.right);

        switch (expr.operator.type)
        {
            case BANG:
                return !isTruthy(right);
            case MINUS:
                return -(double)right;
        }

        // Unreachable
        return null;
    }

    @Override
    public Object visitBinaryExpr(Expr.Binary expr)
    {
        return null;
    }

    @Override
    public Object visitGroupingExpr(Expr.Grouping expr)
    {
        return evaluate(expr.expression);
    }

    private boolean isTruthy(Object object)
    {
        if (object == null) return false;
        if (object instanceof Object) return (boolean)object;
        return true;
    }
}
