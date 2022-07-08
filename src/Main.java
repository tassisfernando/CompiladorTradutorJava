import analyzer.LexicalAnalyzer;
import exception.AnalyzerException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.String.format;

public class Main {
    public static void main(String[] args) {
        printHelloMessage();

        try {
            Scanner scn = new Scanner(System.in);
            String message = scn.nextLine();

            LexicalAnalyzer analyzer = new LexicalAnalyzer(message.toString());
            analyzer.analyze();
            System.out.println("Mensagem inicial: " + message);
            System.out.println("Lista de tokens: " + analyzer.printTokens());
            System.out.println("Tabela de símbolos inicial: " + analyzer.printSymbolList());
        } catch (FileNotFoundException e) {
            String msg = format("Exceção: %s", e.getMessage());
            printException(msg);
        } catch (AnalyzerException e) {
            String msg = format(e.getMessage(), e.getWords());
            printException(msg);
        }
    }

    public static void printException(String msg) {
        System.out.println(msg);
        System.exit(1);
    }

    private static void printHelloMessage() {
        System.out.println("-------------------- DELL BRASIL ---------------------");
        System.out.println("Olá! Eu sou a Débora, assistente virtual da Dell. Como posso te ajudar?");
        System.out.println("Exemplos de frases que entendo e posso ser útil: ");
        System.out.println("-> Qual o valor de um notebook gamer?");
        System.out.println("-> Meu notebook está com problemas na tela");
    }
}
