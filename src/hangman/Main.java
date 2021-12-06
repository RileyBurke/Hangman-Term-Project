package hangman;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner keyboard = new Scanner(System.in);
        HangmanGame hangmanGame = new HangmanGame();
        String choice = "y";
        do {
            hangmanGame.playHangman();
            choice = keyboard.next().toLowerCase();
            System.out.println();
        }while(Objects.equals(choice, "y"));
        System.out.println("Bye!");
    }
}

