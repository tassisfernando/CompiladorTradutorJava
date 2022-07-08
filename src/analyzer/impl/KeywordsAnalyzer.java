package analyzer.impl;

import analyzer.Analyzer;
import enums.*;
import utils.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class KeywordsAnalyzer implements Analyzer {

    private List<String> keywords, keywordsLexemes;
    private Map<String, String> hashKeywords;
    private LexemeAnalyzer lexemeAnalyzer;

    private List<ActionEnum> actions;
    private List<DeviceEnum> devices;
    private List<ProblemEnum> problems;
    private List<EquipmentEnum> equipments;
    private List<SoftwareEnum> softwares;

    public KeywordsAnalyzer() throws FileNotFoundException {
        init();
        buildHash();
    }

    private void init() throws FileNotFoundException {
        List<KeywordsEnum> temp = Arrays.stream(KeywordsEnum.values()).collect(Collectors.toCollection(LinkedList::new));
        keywords = new LinkedList<>();
        for (KeywordsEnum key: temp) {
            keywords.add(key.name());
        }
        actions = Arrays.stream(ActionEnum.values()).collect(Collectors.toCollection(LinkedList::new));
        devices = Arrays.stream(DeviceEnum.values()).collect(Collectors.toCollection(LinkedList::new));
        problems = Arrays.stream(ProblemEnum.values()).collect(Collectors.toCollection(LinkedList::new));
        equipments = Arrays.stream(EquipmentEnum.values()).collect(Collectors.toCollection(LinkedList::new));
        softwares = Arrays.stream(SoftwareEnum.values()).collect(Collectors.toCollection(LinkedList::new));

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

    @Override
    public String analyzeWord(String word) {
        return this.keywords.contains(word) || this.keywordsLexemes.contains(word) ? word : null;
    }
}
