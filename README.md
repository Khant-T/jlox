# jlox
`jlox` is a tree-walk interpreter from [Crafting Interpreters](https://craftinginterpreters.com) by Robert Nystrom.

## Grammar
### Expression
| production | rule(s)                                                                |
|------------|------------------------------------------------------------------------|
| expression | literal \| unary \| binary \| grouping                                 |
| literal    | `NUMBER` \| `STRING` \| "true" \| "false" \| "nil"                     |
| grouping   | "(" expression ")"                                                     |
| unary      | ( "-" \| "!" ) expression                                              |
| binary     | expression operator expression                                         |
| operator   | "==" \| "!=" \| "<" \| "<=" \| ">" \| ">=" \| "+" \| "-" \| "*" \| "/" |

