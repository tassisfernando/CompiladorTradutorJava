package analyzer.impl;

import analyzer.Analyzer;
import exception.AnalyzerException;
import utils.Messages;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class AlphabetAnalyzer implements Analyzer {

    private final Pattern regex = Pattern.compile("[a-zA-Zá-ú-Á-Ú0-9_@.\\/#?!&+-ç]*");

    @Override
    public List<String> analyze(List<String> words) {
        List<String> invalidWords = validAlphabet(words);

        if(!invalidWords.isEmpty()) {
            throw new AnalyzerException(invalidWords, Messages.ALPHABET_ERROR_MESSAGE);
        }
        return words;
    }

    private List<String> validAlphabet(List<String> words) {
        List<String> invalidWords = new ArrayList<>();
        words.forEach(word -> {
            if(!word.matches(regex.pattern())) {
                invalidWords.add(word);
            }
        });

        return invalidWords;
    }
}
