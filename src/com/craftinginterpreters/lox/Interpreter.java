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
        return null;
    }
}
