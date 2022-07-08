package analyzer.impl;

import analyzer.Analyzer;
import org.tartarus.snowball.SnowballStemmer;
import org.tartarus.snowball.ext.portugueseStemmer;

import java.util.List;
import java.util.stream.Collectors;

public class LexemeAnalyzer implements Analyzer {

    private final SnowballStemmer stemmer;

    public LexemeAnalyzer() {
        stemmer = new portugueseStemmer();
    }

    @Override
    public List<String> analyze(List<String> words) {
        return words.stream().map(this::validLexeme).collect(Collectors.toList());
    }

    @Override
    public String analyzeWord(String word) {
        return validLexeme(word);
    }

    public String validLexeme(String word) {
        stemmer.setCurrent(word);
        return stemmer.stem() ? stemmer.getCurrent() : word;
    }
}
