package translator;

import analyzer.impl.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.Normalizer;
import java.util.Scanner;

import static java.util.Objects.requireNonNullElse;

public class Translator {

    private final boolean isFileChanged= true;
    private KeywordsAnalyzer keywordsAnalyzer;
    private StopWordsAnalyzer stopWordsAnalyzer;
    private TokenAnalyzer tokenAnalyzer;
    private LexemeAnalyzer lexemeAnalyzer;
    private AlphabetAnalyzer alphabetAnalyzer;
    private SymbolTable symbolTable;
    private InvertedIndex invertedIndex;
    private String message;

    public Translator(String message) throws IOException {
        keywordsAnalyzer = new KeywordsAnalyzer();
        stopWordsAnalyzer = new StopWordsAnalyzer();
        tokenAnalyzer = new TokenAnalyzer();
        lexemeAnalyzer = new LexemeAnalyzer();
        alphabetAnalyzer = new AlphabetAnalyzer();
        symbolTable = new SymbolTable();
        this.message = message;
        invertedIndex = new InvertedIndex();
        init();
    }

    public void init() throws IOException {
        if (isFileChanged) {
            invertedIndex.fill();
        } else {
            invertedIndex.dataReader();
        }
    }

    public InvertedIndex getInvertedIndex() {
        return invertedIndex;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void execute() {
        message = message.replaceAll("\\p{Punct}", " ");
        message = Normalizer.normalize(message, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        StringBuilder sb = new StringBuilder();
        Scanner scn = new Scanner(message);
        while (scn.hasNext()) {
            String word = scn.next().toLowerCase();
            alphabetAnalyzer.analyzeWord(word);

            if (keywordsAnalyzer.analyzeWord(word) != null && stopWordsAnalyzer.analyzeWord(word) == null) {
                sb.append(keywordsAnalyzer.analyzeWord(word)).append(" ");
                tokenAnalyzer.analyzeWord(lexemeAnalyzer.analyzeWord(keywordsAnalyzer.analyzeWord(word)));
            } else if(symbolTable.analyzeWord(lexemeAnalyzer.analyzeWord(word))== null &&
                    symbolTable.analyzeWord(word)== null && stopWordsAnalyzer.analyzeWord(word) == null){
                symbolTable.addToSymbolTable(lexemeAnalyzer.analyzeWord(word));
                sb.append(lexemeAnalyzer.analyzeWord(word)).append(" ");
                tokenAnalyzer.analyzeWord(lexemeAnalyzer.analyzeWord(word));
            } else if (lexemeAnalyzer.analyzeWord(word) != null && stopWordsAnalyzer.analyzeWord(word) == null) {
                sb.append(lexemeAnalyzer.analyzeWord(word)).append(" ");
                lexemeAnalyzer.analyzeWord(lexemeAnalyzer.analyzeWord(word));
            }

        }
        String resposta = invertedIndex.tfidf(tokenAnalyzer.getTokens());
        System.out.println(requireNonNullElse(resposta,
                "-> Desculpa, eu não entendi! Tente reformular sua mensagem :)")
        );
    }
}
