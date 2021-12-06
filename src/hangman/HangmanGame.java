package hangman;

import java.util.Objects;
import java.util.Scanner;

public class HangmanGame {
    Scanner keyboard = new Scanner(System.in);
    int wrongGuessCounter;
    char[] wrongGuessArray;

    public void playHangman(){
        String choice = "y";
        this.wrongGuessCounter = 0;
        this.wrongGuessArray = new char[]{' ', ' ', ' ', ' ', ' ', ' '};
        do{
            char letter;
            String randomWord = chooseDifficulty();
            String wordInProgress = new String(new char[randomWord.length()]).replace("\0", "_");
            System.out.println(wordInProgress);
            System.out.println(randomWord);
            String wrongGuesses = new String(wrongGuessArray);

            while (wrongGuessCounter < 6 && !Objects.equals(wordInProgress, randomWord)) {
                while (true) {
                    System.out.print("Guess a letter: ");
                    String guess = keyboard.next().toLowerCase();
                    if (guess.length() == 1 && Character.isLetter(guess.charAt(0))){
                        if (wordInProgress.contains(guess) || wrongGuesses.contains(guess)){
                            System.out.println("Letter already guessed, please try again.");
                            continue;
                        }
                        letter = guess.charAt(0);
                        break;
                    }
                    else {
                        System.out.println("Please enter only one letter. Try Again");
                    }
                }
                wordInProgress = guessLetter(letter, randomWord, wordInProgress);
                wrongGuesses = new String(wrongGuessArray);
                System.out.println("Wrong guesses: " + wrongGuesses);
            }
            if (wrongGuessCounter == 6){
                System.out.print("You lose. Try Again? (y/n): ");
            }
            else{
                System.out.print("You win! Play again? (y/n): ");
            }
            choice = keyboard.next();
            System.out.println();
        }while (Objects.equals(choice, "y"));

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
        System.out.println(updatedWordInProgress);
        return updatedWordInProgress;
    }

    public String chooseDifficulty(){
        String randomWord;
        while (true) {
            System.out.print("Choose difficulty (easy/medium/hard): ");
            String difficulty = keyboard.next();
            if (Objects.equals(difficulty.toLowerCase(), "easy")) {
                EasyWordBank easyWordBank = new EasyWordBank();
                randomWord = easyWordBank.getRandomWord();
                return randomWord;
            } else if (Objects.equals(difficulty.toLowerCase(), "medium")) {
                MediumWordBank mediumWordBank = new MediumWordBank();
                randomWord = mediumWordBank.getRandomWord();
                return randomWord;
            } else if (Objects.equals(difficulty.toLowerCase(), "hard")) {
                HardWordBank hardWordBank = new HardWordBank();
                randomWord = hardWordBank.getRandomWord();
                return randomWord;
            } else {
                System.out.println("Invalid difficulty, please try again.");
            }
        }
    }
    public void printHangmanGraphic(){

    }

}
