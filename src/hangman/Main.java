package hangman;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner keyboard = new Scanner(System.in);
        HangmanGame hangmanGame = new HangmanGame();
        String playAgain;
        do {
            hangmanGame.playHangman();
            playAgain = keyboard.next().toLowerCase();
            System.out.println();
        }while(Objects.equals(playAgain, "y"));
        System.out.println("Bye!");
    }
}

