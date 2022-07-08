package analyzer;

import java.util.List;

public interface Analyzer {

    List<String> analyze(List<String> words);

    String analyzeWord(String word);
}
