package analyzer.impl;

import analyzer.Analyzer;
import exception.AnalyzerException;
import utils.Messages;

import java.text.Normalizer;
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

    @Override
    public String analyzeWord(String word) {
        if(!word.matches(regex.pattern())) {
            throw new AnalyzerException(List.of(word), Messages.ALPHABET_ERROR_MESSAGE);
        }
        return word;
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

    public String[] format(String text) {
        text = text.toLowerCase();
        text = text.replaceAll("\\p{Punct}", "");
        text = Normalizer.normalize(text, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        String[] words = text.trim().split("[,.!?'@_] *| +");
        return words;
    }
}
