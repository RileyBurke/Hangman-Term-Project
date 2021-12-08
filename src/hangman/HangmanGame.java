package hangman;

import java.util.Objects;
import java.util.Scanner;

public class HangmanGame {
    Scanner keyboard = new Scanner(System.in);
    private int wrongGuessCounter;
    private char[] wrongGuessArray;

    public void playHangman(){
        this.wrongGuessCounter = 0;
        this.wrongGuessArray = new char[]{' ', ' ', ' ', ' ', ' ', ' '}; //Uses an empty array of empty characters to let us create a String of our wrong guesses.
        char letter;
        String randomWord = chooseDifficulty(); //Assigns the random word returned to a String variable.
        String wordInProgress = new String(new char[randomWord.length()]).replace("\0", "_"); //Creates a char array of '_' based on the length of the String.
        System.out.println("\n" + wordInProgress);
        String wrongGuesses = new String(wrongGuessArray);
        while (wrongGuessCounter < 6 && !Objects.equals(wordInProgress, randomWord)) { //Ends the game once the number of guesses reaches 6 or the word Strings match.
            while (true) { //Loops until a valid letter is entered.
                System.out.print("\nGuess a letter: ");
                String guess = keyboard.next().toLowerCase(); //Accepts input and converts it to lowercase.
                if (guess.length() == 1 && Character.isLetter(guess.charAt(0))){ //Checks for valid single letter entry.
                    if (wordInProgress.contains(guess) || wrongGuesses.contains(guess)){ //Checks if the letter guessed has been guessed before.
                        System.out.println("Letter already guessed, please try again.");
                        continue; //Returns to letter input if the letter has been guessed before.
                    }
                    System.out.println();
                    letter = guess.charAt(0);
                    break; //Breaks the loop
                } else { //Prints an error statement when an invalid input is entered.
                    System.out.println("Please enter one valid letter. Try Again.");
                }
            }
            wordInProgress = guessLetter(letter, randomWord, wordInProgress);
            wrongGuesses = new String(wrongGuessArray); //Creates a new string using the new char values.
            printHangmanGraphic(wordInProgress, wrongGuesses); //Prints the graphic based on how many times you used a bad guess.
        }
        if (wrongGuessCounter == 6){ //Print statements given after the games is over based on a win or a loss.
            System.out.print("You lose. Try Again? (y/n): ");
        } else{
            System.out.print("You win! Play again? (y/n): ");
        }
    }

    public String guessLetter(char letter, String randomWord, String wordInProgress){
        char[] gameWord = wordInProgress.toCharArray();   //Breaks the String of the word in play into a char array.
        for (int i = 0; i < randomWord.length(); i++) {
            if (letter == randomWord.charAt(i)) { //Checks each letter of the word solution.
                gameWord[i] = letter;             //Replaces the '_' when a letter matches the solution.
            }
        }
        String updatedWordInProgress = new String(gameWord);
        if (Objects.equals(wordInProgress, updatedWordInProgress)){ //If nothing changes in the word the guess is wrong and this statement will be True.
            wrongGuessArray[wrongGuessCounter] = letter; //Adds the wrong letter to a list to be displayed with the hangman graphic.
            wrongGuessCounter += 1; //Increments the number of wrong guesses.
        }
        return updatedWordInProgress; //Returns the new String to the calling method.
    }

    public String chooseDifficulty(){
        String randomWord;
        while (true) { //Keeps you in the loop until you enter a valid difficulty.
            System.out.print("Choose difficulty (easy/medium/hard): ");
            String difficulty = keyboard.next(); //Accepts input for the difficulty.
            switch (difficulty.toLowerCase()) {
                case "easy" -> { //Returns a random word from the easy word list.
                    EasyWordBank easyWordBank = new EasyWordBank();
                    randomWord = easyWordBank.getRandomWord(); //Retrieves the random word to be solved from the easy word bank.
                    return randomWord;
                }
                case "medium" -> { //Returns a random word from the medium word list.
                    MediumWordBank mediumWordBank = new MediumWordBank();
                    randomWord = mediumWordBank.getRandomWord(); //Retrieves the random word to be solved from the medium word bank.
                    return randomWord;
                }
                case "hard" -> { //Returns a random word from the hard word list.
                    HardWordBank hardWordBank = new HardWordBank();
                    randomWord = hardWordBank.getRandomWord(); //Retrieves the random word to be solved from the hard word bank.
                    return randomWord;
                }
                default -> System.out.println("Invalid difficulty, please try again.\n"); //Prints when an invalid difficulty option is entered.
            }
        }
    }

    public void printHangmanGraphic(String wordInProgress, String wrongGuesses){
        if (wrongGuessCounter == 0){          //Prints when there are no wrong guesses.
            System.out.println("  +---+  ");
            System.out.println("  |   |  ");
            System.out.println("      |   \t" + wordInProgress);
            System.out.println("      |  ");
            System.out.println("      |  ");
            System.out.println("      |  ");
            System.out.println("=========");
        } else if (wrongGuessCounter == 1){  //Prints when there is one wrong guess.
            System.out.println("  +---+  ");
            System.out.println("  |   |  ");
            System.out.println("  O   |   \t" + wordInProgress);
            System.out.println("      |  ");
            System.out.println("      |  \tWrong guesses: " + wrongGuesses);
            System.out.println("      |  ");
            System.out.println("=========");
        } else if (wrongGuessCounter == 2){ //Prints when there are two wrong guesses.
            System.out.println("  +---+  ");
            System.out.println("  |   |  ");
            System.out.println("  O   |   \t" + wordInProgress);
            System.out.println("  |   |  ");
            System.out.println("      |  \tWrong guesses: " + wrongGuesses);
            System.out.println("      |  ");
            System.out.println("=========");
        } else if (wrongGuessCounter == 3){ //Prints when there are three wrong guesses.
            System.out.println("  +---+  ");
            System.out.println("  |   |  ");
            System.out.println("  O   |   \t" + wordInProgress);
            System.out.println(" /|   |  ");
            System.out.println("      |  \tWrong guesses: " + wrongGuesses);
            System.out.println("      |  ");
            System.out.println("=========");
        } else if (wrongGuessCounter == 4){ //Prints when there are four wrong guesses.
            System.out.println("  +---+  ");
            System.out.println("  |   |  ");
            System.out.println("  O   |   \t" + wordInProgress);
            System.out.println(" /|\\  |  ");
            System.out.println("      |  \tWrong guesses: " + wrongGuesses);
            System.out.println("      |  ");
            System.out.println("=========");
        } else if (wrongGuessCounter == 5){ //Prints when there are five wrong guesses.
            System.out.println("  +---+  ");
            System.out.println("  |   |  ");
            System.out.println("  O   |   \t" + wordInProgress);
            System.out.println(" /|\\  |  ");
            System.out.println(" /    |  \tWrong guesses: " + wrongGuesses);
            System.out.println("      |  ");
            System.out.println("=========");
        } else{                             //Prints when there are six wrong guesses before the game ends.
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
