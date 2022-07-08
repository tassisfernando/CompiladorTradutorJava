package translator;

import analyzer.impl.*;

import java.io.*;
import java.util.*;

public class InvertedIndex {

    private LexemeAnalyzer lexemeAnalyzer;
    private SymbolTable symbolTable;
    private KeywordsAnalyzer keywordsAnalyzer;
    private TokenAnalyzer tokenAnalyzer;
    private StopWordsAnalyzer stopWordsAnalyzer;
    private AlphabetAnalyzer alphabetAnalyzer;

    private Map<String, List<Index>> invertedMap;
    private Map<Integer, String> answers = new HashMap<>();

    public InvertedIndex() throws FileNotFoundException {
        this.invertedMap = new HashMap<>();
        this.lexemeAnalyzer = new LexemeAnalyzer();
        this.symbolTable = new SymbolTable();
        this.keywordsAnalyzer = new KeywordsAnalyzer();
        this.tokenAnalyzer = new TokenAnalyzer();
        this.stopWordsAnalyzer = new StopWordsAnalyzer();
        this.alphabetAnalyzer = new AlphabetAnalyzer();
    }

    public void fill() throws IOException {
        File file = new File("src/files/answers.txt");
        Scanner scnFile = new Scanner(file);

        int position = 0;
        while (scnFile.hasNext()) {

            String text = scnFile.nextLine();
            fillAnswer();
            String[] words = alphabetAnalyzer.format(text);

            for (String word : words) {
                if (stopWordsAnalyzer.analyzeWord(word) == null) {
                    if (keywordsAnalyzer.analyzeWord(word) != null
                            && stopWordsAnalyzer.analyzeWord(word) == null) {
                        tokenAnalyzer.analyzeWord(lexemeAnalyzer.analyzeWord(keywordsAnalyzer.analyzeWord(word)));
                    } else if (symbolTable.analyzeWord(lexemeAnalyzer.analyzeWord(word)) == null
                            && symbolTable.analyzeWord(word) == null
                            && stopWordsAnalyzer.analyzeWord(word) == null) {
                        tokenAnalyzer.analyzeWord(lexemeAnalyzer.analyzeWord(word));
                    }

                    for (String key : tokenAnalyzer.getTokens()) {
                        addKey(key, position);
                    }
                    tokenAnalyzer.getTokens().clear();
                }
            }
            position++;
        }

        dataWriter();
    }

    public String tfidf(List<String> tokens){
        List<String> localTokens = new LinkedList<>(tokens);
        List<Integer> usages = new LinkedList<>(Collections.nCopies(answers.size(),0));
        for (String token: localTokens) {
            if (invertedMap.get(token) != null) {
                for (Index ind : invertedMap.get(token)) {
                    usages.set(ind.getIndex(), usages.get(ind.getIndex()) + ind.getUsage());
                }
            }
        }
        int position = 0;
        for (int i = 0; i < usages.size()-1; i++) {
            if (usages.get(position) < usages.get(i+1))
                position = i+1;
            else if(Objects.equals(usages.get(position), usages.get(i + 1))){
                Random random = new Random();
                int ran = random.nextInt(2);
                if (ran != 1)
                    position = i+1;
            }
        }
        return answers.get(position);
    }

    public void addKey(String word, int index){
        if (invertedMap.containsKey(word)){
            for (Index key: invertedMap.get(word)) {
                if(key.getIndex() == index) {
                    key.incrementUsage();
                    return;
                }
            }
            invertedMap.get(word).add(new Index(index,1));

        } else {
            Index key = new Index(index,1);
            List<Index> list = new ArrayList<>();
            list.add(key);
            invertedMap.put(word,list);
        }
    }

    public void fillAnswer() throws FileNotFoundException {
        File resposta = new File("src/files/answers.txt");
        Scanner inArchive = new Scanner(resposta);
        int position = 0;
        while (inArchive.hasNext()) {
            answers.put(position, inArchive.nextLine());
            position++;
        }
    }

    public void dataWriter() throws IOException {
        FileWriter fw = new FileWriter("src/files/file.txt");
        PrintWriter printWriter = new PrintWriter(fw);
        invertedMap.forEach((key1, value) -> {
            value.stream().sorted();
            printWriter.println(key1 + " " + value.toString());
        });
        printWriter.close();
    }

    public void dataReader()throws IOException{
        invertedMap.clear();
        fillAnswer();
        File arquivo = new File("src/files/file.txt");
        Scanner inArchive = new Scanner(arquivo);

        while (inArchive.hasNext()) {
            String key = inArchive.next();
            String texto = inArchive.nextLine();
            texto = texto.replaceAll("[a-zA-Z,:\\]\\[]","");
            String[] words = texto.trim().split("[,.!?'@_] *| +");
            List<Index> list = new LinkedList<>();

            for (int i = 0; i < words.length; i=i+2) {
                list.add(new Index(Integer.parseInt(words[i]),Integer.parseInt(words[i+1])));
            }
            invertedMap.put(key, list);
        }
    }

}
