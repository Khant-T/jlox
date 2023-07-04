package com.craftinginterpreters.lox;

public class AstPrinter implements Expr.Visitor<String>, Stmt.Visitor<String>
{
    String print(Expr expr)
    {
        return expr.accept(this);
    }

    String print(Stmt stmt)
    {
        return stmt.accept(this);
    }

    @Override
    public String visitPrintStmt(Stmt.Print stmt)
    {
        return parenthesize("print");
    }

    @Override
    public String visitExpressionStmt(Stmt.Expression stmt)
    {
        if (stmt.expression instanceof Expr.Binary)
            return visitBinaryExpr((Expr.Binary)stmt.expression);
        if (stmt.expression instanceof Expr.Unary)
            return visitUnaryExpr((Expr.Unary)stmt.expression);
        if (stmt.expression instanceof Expr.Grouping)
            return visitGroupingExpr((Expr.Grouping)stmt.expression);
        if (stmt.expression instanceof Expr.Literal)
            return visitLiteralExpr((Expr.Literal)stmt.expression);

        // Unreachable.
        return null;
    }

    @Override
    public String visitVarStmt(Stmt.Var stmt)
    {
        return "(define " + stmt.name.lexeme + ")";
    }

    @Override
    public String visitBinaryExpr(Expr.Binary expr)
    {
        return parenthesize(expr.operator.lexeme, expr.left, expr.right);
    }

    @Override
    public String visitGroupingExpr(Expr.Grouping expr)
    {
        return parenthesize("group", expr.expression);
    }

    @Override
    public String visitLiteralExpr(Expr.Literal expr)
    {
        if (expr.value == null) return "nil";
        return expr.value.toString();
    }

    @Override
    public String visitUnaryExpr(Expr.Unary expr)
    {
        return parenthesize(expr.operator.lexeme, expr.right);
    }

    @Override
    public String visitVariableExpr(Expr.Variable expr)
    {
        return "define " + expr.name;
    }

    private String parenthesize(String name, Expr... exprs)
    {
        StringBuilder builder = new StringBuilder();

        builder.append("(").append(name);
        for (Expr expr : exprs)
        {
            builder.append(" ");
            builder.append(expr.accept(this));
        }
        builder.append(")");

        return builder.toString();
    }
}
