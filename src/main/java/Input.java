import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Input {
    public static Scanner scanner;
    Input() {
        scanner = new Scanner(System.in);
    }
    public static String requestWord(String inputQuestion) {
        System.out.println(inputQuestion);
        String input = scanner.nextLine();
        return input;
    }

    public static Boolean checkUserInput(String userInput) {
        Pattern pattern = Pattern.compile(
                "[" +                   //начало списка допустимых символов
                        "а-яА-ЯёЁ" +    //буквы русского алфавита
                        "a-zA" + // латинница
                        "]" +                   //конец списка допустимых символов
                        "*");
        Matcher matcher = pattern.matcher(userInput);
        return matcher.matches();
    }
    public static String requestWordForDB() {
        System.out.println("Enter word in format: beforeRoot Root afterRoot USE spaces");
        String input = scanner.nextLine();
        return input;
    }
}
