package usa.lafleur.cincospenguinos.mini_java.syntax_parser;

import usa.lafleur.cincospenguinos.mini_java.lexer.Token;
import usa.lafleur.cincospenguinos.mini_java.lexer.TokenItem;

import java.util.*;

public class SyntaxParser {
    private Queue<TokenItem> _tokenItems;

    SyntaxParser(List<TokenItem> programTokens) {
        _tokenItems = new LinkedList<>(programTokens);
    }

    class SyntaxParsingStack {
        private Stack<Expression> expressions;

        SyntaxParsingStack() {
            expressions = new Stack<>();
        }

        void pushTokenAndReduce(TokenItem item) {
            Expression expression = Expression.getForTokenItem(item);

            if (!(expression instanceof UnknownExpression)) {
                expressions.push(expression);
            }
        }

        public List<Expression> toExpressionList() {
            return Arrays.asList(expressions.toArray(new Expression[] {}));
        }
    }

    public List<Expression> parse() {
        SyntaxParsingStack stack = new SyntaxParsingStack();
        stack.pushTokenAndReduce(new TokenItem(Token.INTEGER_LITERAL, "4"));

        return stack.toExpressionList();
    }
}
