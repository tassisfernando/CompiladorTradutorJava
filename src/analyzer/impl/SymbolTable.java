package analyzer.impl;

import analyzer.Analyzer;

import java.util.ArrayList;
import java.util.List;

public class SymbolTable implements Analyzer {

    private List<String> table;

    public SymbolTable() {
        table = new ArrayList<>();
    }

    @Override
    public List<String> analyze(List<String> words) {
        return table.contains(words.toString()) ? words : null;
    }

    @Override
    public String analyzeWord(String word) {
        return table.contains(word) ? word : null;
    }

    public void addToSymbolTable(String word) {
        if(word != null) {
            table.add(word);
        }
    }
}
