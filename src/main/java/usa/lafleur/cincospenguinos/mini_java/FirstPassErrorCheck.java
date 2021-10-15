package usa.lafleur.cincospenguinos.mini_java;

import usa.lafleur.cincospenguinos.mini_java.lexer.TokenItem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FirstPassErrorCheck {
    private final List<TokenItem> _tokenStream;

    public FirstPassErrorCheck(List<TokenItem> tokenStream) {
        _tokenStream = tokenStream;
    }

    public List<SyntaxErrorItem> syntaxErrors() {
        List<SyntaxErrorItem> items = new ArrayList<>();

        BraceErrorAggregate curlyBraceAggregate = BraceErrorAggregate.getAggregateFor("{");
        BraceErrorAggregate squareBraceAggregate = BraceErrorAggregate.getAggregateFor("[");
        BraceErrorAggregate parenAggregate = BraceErrorAggregate.getAggregateFor("(");

        for (TokenItem item : _tokenStream) {
            items.add(curlyBraceAggregate.consider(item));
            items.add(squareBraceAggregate.consider(item));
            items.add(parenAggregate.consider(item));
        }

        items.add(curlyBraceAggregate.complete());
        items.add(squareBraceAggregate.complete());
        items.add(parenAggregate.complete());

        return items
                .stream()
                .filter(e -> !(e instanceof NullSyntaxErrorItem))
                .collect(Collectors.toList());
    }
}
