public class Main {
    public static void main(String[] args) {
        Database db = new Database();
        System.out.println("Lab2");
        Input input = new Input();
        String inputWord = null;
        boolean check = false;
        for(;;) {
            do {
                inputWord = input.requestWord("Please write a word");
                //System.out.println(inputWord);
                if (inputWord.equals("q")) {
                    System.out.println("Exiting...");
                    return;
                }
                check = input.checkUserInput(inputWord);
                if (check) {
                    System.out.println("Input correct");
                }
                else {
                    System.out.println("Input has latin or digit or special symbol. Try again");
                }
            }
            while (!check);
            boolean checkIsExists = db.checkIsWordExists(inputWord);
            if (checkIsExists) {
                //Requests words with same root form db
                String[] rootwords = db.getRootWords(inputWord);
                for (int i = 0; i < rootwords.length; ++i) {
                    if (rootwords[i] != null)
                        System.out.println(rootwords[i]);
                }
            } else {
                String[] wordParts = new String[3];
                String record = input.requestWordForDB();
                int countSpaces = 0;
                int j = 0, i;
                int count = 0;
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
                        wordParts[count] = new String(temp);
                        count++;
                    }
                }
                if(countSpaces != 2) {
                    System.out.println("You have entered not Two words!");
                }
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
                db.insertWord(wordParts);

            }
        }
    }
}
