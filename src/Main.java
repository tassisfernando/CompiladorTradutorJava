import exception.AnalyzerException;
import translator.Translator;
import utils.Messages;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static java.lang.String.format;

public class Main {

    private static Translator translator;

    public static void main(String[] args) {
        try {
            printHelloMessage();
            Scanner scn = new Scanner(System.in);
            String message = scn.nextLine();
            translator = new Translator(message);
            while (!message.equalsIgnoreCase(Messages.SAIR) && !message.equalsIgnoreCase(Messages.SAIR_0)) {
                translator.execute();
                message = scn.nextLine();
                translator.setMessage(message);
            }

            translator.getInvertedIndex().dataWriter();

            System.out.println("\n\n-> Espero ter ajudado. Até mais!!");
        } catch (IOException e) {
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
        System.out.println("-------------------------- DELL BRASIL ----------------------------");
        System.out.println("->Olá! Eu sou a Débora, assistente virtual da Dell. Como posso te ajudar?");
        System.out.println("->Exemplos de frases que entendo e posso ser útil: ");
        System.out.println("--> Qual o valor de um notebook gamer?");
        System.out.println("--> Meu notebook está com problemas na tela");
        System.out.println("---> Digite 0 ou 'sair' a qualquer momento para sair. ");
    }
}
