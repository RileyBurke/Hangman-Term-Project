package hangman;

import java.util.Objects;
import java.util.Scanner;

public class HangmanGame {
    Scanner keyboard = new Scanner(System.in);
    private int wrongGuessCounter;
    private char[] wrongGuessArray;

    public void playHangman(){
        String choice = "y";
        this.wrongGuessCounter = 0;
        this.wrongGuessArray = new char[]{' ', ' ', ' ', ' ', ' ', ' '};
        char letter;
        String randomWord = chooseDifficulty();
        String wordInProgress = new String(new char[randomWord.length()]).replace("\0", "_");
        System.out.println(wordInProgress);
        String wrongGuesses = new String(wrongGuessArray);

        while (wrongGuessCounter < 6 && !Objects.equals(wordInProgress, randomWord)) {
            while (true) {
                System.out.print("\nGuess a letter: ");
                String guess = keyboard.next().toLowerCase();
                if (guess.length() == 1 && Character.isLetter(guess.charAt(0))){
                    if (wordInProgress.contains(guess) || wrongGuesses.contains(guess)){
                        System.out.println("Letter already guessed, please try again.");
                        continue;
                    }
                    System.out.println();
                    letter = guess.charAt(0);
                    break;
                }
                else {
                    System.out.println("Please enter only one letter. Try Again");
                }
            }
            wordInProgress = guessLetter(letter, randomWord, wordInProgress);
            wrongGuesses = new String(wrongGuessArray);
            printHangmanGraphic(wordInProgress, wrongGuesses);
        }
        if (wrongGuessCounter == 6){
            System.out.print("You lose. Try Again? (y/n): ");
        }
        else{
            System.out.print("You win! Play again? (y/n): ");
        }
    }

    public String guessLetter(char letter, String randomWord, String wordInProgress){
        char[] gameWord = wordInProgress.toCharArray();
        String updatedWordInProgress = wordInProgress;
        for (int i = 0; i < randomWord.length(); i++) {
            if (letter == randomWord.charAt(i)) {
                gameWord[i] = letter;
            }
        }
        updatedWordInProgress = new String(gameWord);
        if (Objects.equals(wordInProgress, updatedWordInProgress)){
            wrongGuessArray[wrongGuessCounter] = letter;
            wrongGuessCounter += 1;
        }
        return updatedWordInProgress;
    }

    public String chooseDifficulty(){
        String randomWord;
        while (true) {
            System.out.print("Choose difficulty (easy/medium/hard): ");
            String difficulty = keyboard.next();
            switch (difficulty.toLowerCase()) {
                case "easy" -> {
                    EasyWordBank easyWordBank = new EasyWordBank();
                    randomWord = easyWordBank.getRandomWord();
                    System.out.println();
                    return randomWord;
                }
                case "medium" -> {
                    MediumWordBank mediumWordBank = new MediumWordBank();
                    randomWord = mediumWordBank.getRandomWord();
                    System.out.println();
                    return randomWord;
                }
                case "hard" -> {
                    HardWordBank hardWordBank = new HardWordBank();
                    randomWord = hardWordBank.getRandomWord();
                    System.out.println();
                    return randomWord;
                }
                default -> System.out.println("Invalid difficulty, please try again.");
            }
        }
    }
    public void printHangmanGraphic(String wordInProgress, String wrongGuesses){
        if (wrongGuessCounter == 0){
            System.out.println("  +---+  ");
            System.out.println("  |   |  ");
            System.out.println("      |   \t" + wordInProgress);
            System.out.println("      |  ");
            System.out.println("      |  \tWrong guesses: " + wrongGuesses);
            System.out.println("      |  ");
            System.out.println("=========");
        }
        else if (wrongGuessCounter == 1){
            System.out.println("  +---+  ");
            System.out.println("  |   |  ");
            System.out.println("  O   |   \t" + wordInProgress);
            System.out.println("      |  ");
            System.out.println("      |  \tWrong guesses: " + wrongGuesses);
            System.out.println("      |  ");
            System.out.println("=========");
        }
        else if (wrongGuessCounter == 2){
            System.out.println("  +---+  ");
            System.out.println("  |   |  ");
            System.out.println("  O   |   \t" + wordInProgress);
            System.out.println("  |   |  ");
            System.out.println("      |  \tWrong guesses: " + wrongGuesses);
            System.out.println("      |  ");
            System.out.println("=========");
        }
        else if (wrongGuessCounter == 3){
            System.out.println("  +---+  ");
            System.out.println("  |   |  ");
            System.out.println("  O   |   \t" + wordInProgress);
            System.out.println(" /|   |  ");
            System.out.println("      |  \tWrong guesses: " + wrongGuesses);
            System.out.println("      |  ");
            System.out.println("=========");
        }
        else if (wrongGuessCounter == 4){
            System.out.println("  +---+  ");
            System.out.println("  |   |  ");
            System.out.println("  O   |   \t" + wordInProgress);
            System.out.println(" /|\\  |  ");
            System.out.println("      |  \tWrong guesses: " + wrongGuesses);
            System.out.println("      |  ");
            System.out.println("=========");
        }
        else if (wrongGuessCounter == 5){
            System.out.println("  +---+  ");
            System.out.println("  |   |  ");
            System.out.println("  O   |   \t" + wordInProgress);
            System.out.println(" /|\\  |  ");
            System.out.println(" /    |  \tWrong guesses: " + wrongGuesses);
            System.out.println("      |  ");
            System.out.println("=========");
        }
        else{
            System.out.println("  +---+  ");
            System.out.println("  |   |  ");
            System.out.println("  O   |   \t" + wordInProgress);
            System.out.println(" /|\\  |  ");
            System.out.println(" / \\  |  \tWrong guesses: " + wrongGuesses);
            System.out.println("      |  ");
            System.out.println("=========");
        }
    }

}
