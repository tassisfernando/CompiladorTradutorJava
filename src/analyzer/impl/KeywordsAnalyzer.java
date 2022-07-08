package analyzer.impl;

import analyzer.Analyzer;
import utils.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class KeywordsAnalyzer implements Analyzer {

    private List<String> keywords, keywordsLexemes;
    private Map<String, String> hashKeywords;
    private LexemeAnalyzer lexemeAnalyzer;

    public KeywordsAnalyzer() throws FileNotFoundException {
        init();
        buildHash();
    }

    private void init() throws FileNotFoundException {
        File f = new File("keywords_ptbr.txt");
        Scanner s = new Scanner(f);
        StringBuilder stopWordsString = new StringBuilder(" ");
        while (s.hasNext()) {
            stopWordsString.append(s.nextLine().toLowerCase()).append("\n");
        }
        this.keywords = Utils.getStringList(stopWordsString.toString(), "\n");
        lexemeAnalyzer = new LexemeAnalyzer();
        keywordsLexemes = new ArrayList<>();
        hashKeywords = new HashMap<>();
    }

    private void buildHash() {
        keywords.forEach(keyword -> {
            String lexeme = lexemeAnalyzer.validLexeme(keyword);
            keywordsLexemes.add(lexeme);
            hashKeywords.put(lexeme, keyword);
        });
    }

    @Override
    public List<String> analyze(List<String> words) {
        List<String> symbolList = new ArrayList<>();

        for(int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            if(!keywordsLexemes.contains(word)) {
                symbolList.add(word);
            } else {
                String keyword = hashKeywords.get(word);
                words.set(i, keyword);
            }
        }

        return symbolList;
    }
}
