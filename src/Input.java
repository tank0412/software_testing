import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Input {
    public static String requestWord(String inputQuestion) {
        System.out.println(inputQuestion);
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        String input = scanner.nextLine();
        return input;
    }
    public static String requestWord(String inputQuestion, Scanner testscanner) {
        System.out.println(inputQuestion);
        Scanner scanner = testscanner;
        String input = scanner.nextLine();
        return input;
    }
    public static Boolean checkUserInput(String userInput) {
        Pattern pattern = Pattern.compile(
                "[" +                   //начало списка допустимых символов
                        "а-яА-ЯёЁ" +    //буквы русского алфавита
                        "]" +                   //конец списка допустимых символов
                        "*");
        Matcher matcher = pattern.matcher(userInput);
        return matcher.matches();
    }
}
