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
                input.publishInputCheckResult(check);
            }
            while (!check);
            boolean checkIsExists = db.checkIsWordExists(inputWord);
            if (checkIsExists) {
                System.out.println("World have already exists!");
                //Requests words with same root form db
                String[] rootwords = db.getRootWords(inputWord);
                input.publishRootWords(rootwords);
            } else {
                String isContinue = "";
                boolean isEquals = false;
                do {
                    isContinue = input.requestWord("Should we save word in DB: press 'Y' for continue or 'q' for exit");
                    switch(isContinue.charAt(0)) {
                        case 'Y': {
                            isEquals = true;
                            break;
                        }
                        case 'q': {
                            System.out.println("Exiting...");
                            return;
                        }
                        default: {
                            System.out.println("Incorrect answer. Try again");
                            break;
                        }

                    }
                }
                while(!isEquals);
                int j = 0, i;
                String[] wordParts = new String[3];
                do {
                    String record = input.requestWordForDB();
                    if (record.equals("q")) {
                        System.out.println("Exiting...");
                        return;
                    }
                    wordParts = input.prepareWordToStoreInDB(inputWord, record);
                }
                while(Input.checkSecondTimeInput);

                db.insertWord(wordParts);

            }
        }
    }
}
