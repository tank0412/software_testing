import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Input {
    public static Scanner scanner;
    static boolean checkSecondTimeInput = true;
    Input() {
        scanner = new Scanner(System.in);
    }
    public String requestWord(String inputQuestion) {
        System.out.println(inputQuestion);
        String input = scanner.nextLine();
        return input;
    }

    public Boolean checkUserInput(String userInput) {
        Pattern pattern = Pattern.compile(
                "[" +                   //начало списка допустимых символов
                        "а-яА-ЯёЁ" +    //буквы русского алфавита
                        //"a-zA" + // латинница
                        "]" +                   //конец списка допустимых символов
                        "*");
        Matcher matcher = pattern.matcher(userInput);
        return matcher.matches();
    }
    public String requestWordForDB() {
        System.out.println("Enter word in format: beforeRoot Root afterRoot USE spaces");
        String input = scanner.nextLine();
        return input;
    }
    public String[] prepareWordToStoreInDB(String inputWord, String record) {
        String[] wordParts = new String[3];

        int countSpaces = 0;
        int count = 0;
        int i = 0, j = 0;
        for (i = 0; i < record.length(); ++i) {
            char[] array = record.toCharArray();
            if (array[i] == ' ' || i == array.length - 1) {
                int index = 0;
                char[] temp = new char[10];
                for (; j < i; ++j) {
                    if (i == array.length - 1) ++i; // для последней буквы слова
                    if (array[j] == ' ') {
                        countSpaces++;
                        continue;
                    }
                    temp[index] = array[j];
                    index++;
                }
                j = i;
                wordParts[count] = new String(temp).trim();
                count++;
            }
        }
        if (countSpaces != 2) {
            System.out.println("You have entered not three word parts!");
        }
        String tempConcat =wordParts[0] +wordParts[1] + wordParts[2];
        if(tempConcat.equals(inputWord)) {
            checkSecondTimeInput = false;
            return resizeArrayBeforeStoreInDB(wordParts);
        }
        else {
            System.out.println("First word which you entered and word from concated parts do not match");
            checkSecondTimeInput = true;
            return null;
        }
    }
    public String[] resizeArrayBeforeStoreInDB( String[] wordParts) {
        int i = 0, j = 0;
        //add new word to db
        for (i = 0; i < 3; ++i) {
            char[] tempCopy = wordParts[i].toCharArray();
            int countLetters = 0;
            for (j = 0; j < tempCopy.length; ++j) {
                if (tempCopy[j] != 0) {
                    countLetters++;
                }
            }
            char[] resized = new char[countLetters];
            for (j = 0; j < countLetters; ++j) {
                if (tempCopy[j] != 0) {
                    resized[j] = tempCopy[j];
                }
            }
            wordParts[i] = new String(resized);
        }
        return wordParts;
    }
    public void publishInputCheckResult(Boolean check) {
        if (check) {
            System.out.println("Input correct");
        }
        else {
            System.out.println("Input has latin or digit or special symbol. Try again");
        }
    }
    public void publishRootWords(String[] rootwords) {
        for (int i = 0; i < rootwords.length; ++i) {
            if (rootwords[i] != null)
                System.out.println(rootwords[i]);
        }
    }
}
