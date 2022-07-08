package exception;

import java.util.List;

public class AnalyzerException extends RuntimeException {

    private List<String> words;

    public AnalyzerException(List<String> words, String msg) {
        super(msg);
        this.words = words;
    }

    public String getWords() {
        return words.toString();
    }
}
