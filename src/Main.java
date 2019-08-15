public class Main {
    public static void main(String[] args) {
        System.out.println("Lab2");
        Input input = new Input();
        String inputWord = input.requestWord("Please write a word");
        System.out.println(inputWord);
        if(input.checkUserInput(inputWord)) {
            System.out.println("Input correct");
        }
        else {
            System.out.println("Input incorrect");
        }
        return;
    }
}
