package hangman;

import java.util.Objects;
import java.util.Scanner;

/**
 * Prints a greeting and starts the Hangman game. Upon completion of the game an option to play again is given.
 */
public class Main {
    public static void main(String[] args){
        Scanner keyboard = new Scanner(System.in);
        HangmanGame hangmanGame = new HangmanGame();
        System.out.println("Welcome to Hangman!\n");
        String playAgain;
        do { //Always runs the game once, then gives a condition to play again.
            hangmanGame.playHangman(); //Starts the hangman game.
            playAgain = keyboard.next().toLowerCase(); //User enters option to play again.
            System.out.println();
        }while(Objects.equals(playAgain, "y")); //If the user enters 'y' the game will restart, if not the program will end.
        System.out.println("Bye!");
    }
}

