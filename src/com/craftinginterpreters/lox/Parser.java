package com.craftinginterpreters.lox;

import java.util.List;

import static com.craftinginterpreters.lox.TokenType.*;

public class Parser
{
    private final List<Token> tokens;
    private int current = 0;

    Parser(List<Token> tokens)
    {
        this.tokens = tokens;
    }

    private Expr expression()
    {
        return equality();
    }

    private Expr equality()
    {
        Expr expr = comparison();

        while (match(BANG_EQUAL, EQUAL_EQUAL))
        {
            Token operator = previous();
            Expr  right    = comparison();
            expr = new Expr.Binary(expr, operator, right);
        }

        return expr;
    }
}
