package analyzer;

import analyzer.impl.AlphabetAnalyzer;
import analyzer.impl.KeywordsAnalyzer;
import analyzer.impl.LexemeAnalyzer;
import analyzer.impl.StopWordsAnalyzer;
import utils.Utils;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.regex.Pattern;

import static java.util.Arrays.asList;

public class LexicalAnalyzer  {

    private final String userMessage;
    private List<String> tokensList, symbolList;
    private final List<Analyzer> analyzers;
    private final KeywordsAnalyzer keywordsAnalyzer;
    private final Pattern regexSpliter = Pattern.compile("\\s");;

    public LexicalAnalyzer(String userMessage) throws FileNotFoundException {
        this.userMessage = userMessage;
        this.analyzers = asList(
                new AlphabetAnalyzer(),
                new StopWordsAnalyzer(),
                new LexemeAnalyzer()
        );
        this.keywordsAnalyzer = new KeywordsAnalyzer();
    }

    public void analyze() {
        this.tokensList = Utils.getStringList(this.userMessage, regexSpliter.pattern());
        analyzers.forEach(analyzer -> this.tokensList = analyzer.analyze(tokensList));
        symbolList = keywordsAnalyzer.analyze(tokensList);
    }

    public String printTokens() {
        return tokensList != null ? tokensList.toString() : "";
    }

    public String printSymbolList() {
        return symbolList != null ? symbolList.toString() : "";
    }
}
