package hangman;

import java.util.Objects;
import java.util.Scanner;

public class HangmanGame {
    Scanner keyboard = new Scanner(System.in);
    static int wrongGuessCounter;
    private char[] wrongLettersGuessed;

    public void playHangman(){
        wrongGuessCounter = 0;
        this.wrongLettersGuessed = new char[]{' ', ' ', ' ', ' ', ' ', ' ', ' '}; //Uses an empty array of empty characters to let us create a String of our wrong guesses.
        char letter;
        String randomWord = chooseDifficulty(); //Assigns the random word returned to a String variable.
        String wordInProgress = new String(new char[randomWord.length()]).replace("\0", "_"); //Creates a char array of '_' based on the length of the String.
        System.out.println("\n" + wordInProgress);
        String wrongGuesses = new String(wrongLettersGuessed);
        while (wrongGuessCounter < 7 && !Objects.equals(wordInProgress, randomWord)) { //Ends the game once the number of guesses reaches 6 or the word Strings match.
            while (true) { //Loops until a valid letter is entered.
                System.out.print("\nGuess a letter or the full word: ");
                String guess = keyboard.next().toLowerCase(); //Accepts input and converts it to lowercase.
                if (guess.length() == 1 && Character.isLetter(guess.charAt(0))){ //Checks for valid single letter entry.
                    letter = guess.charAt(0);
                    if (wordInProgress.contains(guess) || wrongGuesses.contains(guess)){ //Checks if the letter guessed has been guessed before.
                        System.out.println("Letter already guessed, please try again.");
                    }else{
                        wordInProgress = guessLetter(letter, randomWord, wordInProgress);
                    }
                    System.out.println();
                    break; //Breaks the loop
                } else if(guess.length() > 1){ //If more than one character it counts as a word guess.
                    wordInProgress = guessWord(guess,randomWord,wordInProgress);
                    break;
                }
                else { //Prints an error statement when an invalid input is entered.
                    System.out.println("Please enter a valid letter. Try Again.");
                }
            }
            wrongGuesses = new String(wrongLettersGuessed); //Creates a new string using the new char values.
            printHangmanGraphic(wordInProgress, wrongGuesses); //Prints the graphic based on how many times you used a bad guess.
        }
        if (wrongGuessCounter == 7){ //Print statements given after the games is over based on a win or a loss.
            System.out.println("You lose. The correct word was " + randomWord + ".");
            System.out.print("Try Again? (y/n): ");
        } else{
            System.out.print("You win! \nPlay again? (y/n): ");
        }
    }

    public String guessWord(String guess, String randomWord, String wordInProgress) {
        boolean noSpecialCharacters = true;
        char[] guessWord = guess.toCharArray();
        for (char wordLetter : guessWord) {         //Checks for numbers or special characters in the word guessed.
            if (!Character.isLetter(wordLetter)) {
                noSpecialCharacters = false;
            }
        }
        while (true) {
            if (noSpecialCharacters && Objects.equals(guess, randomWord)) { //If you get a correct guess it will fill in all the letters.
                wordInProgress = randomWord;
                return wordInProgress;
            } else if (noSpecialCharacters && !Objects.equals(guess, randomWord)) { //Incorrect guess increments the counter.
                System.out.println("Incorrect guess.");
                wrongGuessCounter += 1;
                return wordInProgress;
            } else {
                System.out.println("Please enter a valid word. Try Again."); //If there are special characters or numbers it will not count.
            }
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
            wrongLettersGuessed[wrongGuessCounter] = letter; //Adds the wrong letter to a list to be displayed with the hangman graphic.
            wrongGuessCounter += 1; //Increments the number of wrong guesses.
        }
        return updatedWordInProgress; //Returns the new String to the calling method.
    }

    /**
     * Allows the player to choose a difficulty level, then returns a random word based on that difficulty.
     * @return Random word of specified difficulty.
     */
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
            System.out.println("  |   |  \t" + wordInProgress);
            System.out.println("      |  ");
            System.out.println("      |  \tWrong guesses: " + wrongGuesses);
            System.out.println("      |  ");
            System.out.println("      |  \tWrong guesses remaining: " + (7 - wrongGuessCounter));
            System.out.println("      |  ");
            System.out.println("=========");
        } else if (wrongGuessCounter == 1){  //Prints when there is one wrong guess.
            System.out.println("  +---+  ");
            System.out.println("  |   |  \t" + wordInProgress);
            System.out.println("  O   |  ");
            System.out.println("      |  \tWrong guesses: " + wrongGuesses);
            System.out.println("      |  ");
            System.out.println("      |  \tWrong guesses remaining: " + (7 - wrongGuessCounter));
            System.out.println("      |  ");
            System.out.println("=========");
        } else if (wrongGuessCounter == 2){ //Prints when there are two wrong guesses.
            System.out.println("  +---+  ");
            System.out.println("  |   |  \t" + wordInProgress);
            System.out.println("  O   |  ");
            System.out.println("  |   |  \tWrong guesses: " + wrongGuesses);
            System.out.println("      |  ");
            System.out.println("      |  \tWrong guesses remaining: " + (7 - wrongGuessCounter));
            System.out.println("      |  ");
            System.out.println("=========");
        } else if (wrongGuessCounter == 3){ //Prints when there are three wrong guesses.
            System.out.println("  +---+  ");
            System.out.println("  |   |  \t" + wordInProgress);
            System.out.println("  O   |  ");
            System.out.println(" /|   |  \tWrong guesses: " + wrongGuesses);
            System.out.println("      |  ");
            System.out.println("      |  \tWrong guesses remaining: " + (7 - wrongGuessCounter));
            System.out.println("      |  ");
            System.out.println("=========");
        } else if (wrongGuessCounter == 4){ //Prints when there are four wrong guesses.
            System.out.println("  +---+  ");
            System.out.println("  |   |  \t" + wordInProgress);
            System.out.println("  O   |  ");
            System.out.println(" /|\\  |  \tWrong guesses: " + wrongGuesses);
            System.out.println("      |  ");
            System.out.println("      |  \tWrong guesses remaining: " + (7 - wrongGuessCounter));
            System.out.println("      |  ");
            System.out.println("=========");
        } else if (wrongGuessCounter == 5){ //Prints when there are five wrong guesses.
            System.out.println("  +---+  ");
            System.out.println("  |   |  \t" + wordInProgress);
            System.out.println("  O   |  ");
            System.out.println(" /|\\  |  \tWrong guesses: " + wrongGuesses);
            System.out.println("  |   |  ");
            System.out.println("      |  \tWrong guesses remaining: " + (7 - wrongGuessCounter));
            System.out.println("      |  ");
            System.out.println("=========");
        } else if (wrongGuessCounter == 6){ //Prints when there are five wrong guesses.
            System.out.println("  +---+  ");
            System.out.println("  |   |  \t" + wordInProgress);
            System.out.println("  O   |  ");
            System.out.println(" /|\\  |  \tWrong guesses: " + wrongGuesses);
            System.out.println("  |   |  ");
            System.out.println(" /    |  \tWrong guesses remaining: " + (7 - wrongGuessCounter));
            System.out.println("      |  ");
            System.out.println("=========");
        } else{                             //Prints when there are six wrong guesses before the game ends.
            System.out.println("  +---+  ");
            System.out.println("  |   |  \t" + wordInProgress);
            System.out.println("  O   |  ");
            System.out.println(" /|\\  |  \tWrong guesses: " + wrongGuesses);
            System.out.println("  |   |  ");
            System.out.println(" / \\  | \tWrong guesses remaining: " + (7 - wrongGuessCounter));
            System.out.println("      |  ");
            System.out.println("=========");
        }
    }
}
