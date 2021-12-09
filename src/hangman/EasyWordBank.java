package hangman;

import java.util.Random;

/**
 * Implements the WordBank interface with an array of words and a method to return a random word from that array.
 */
public class EasyWordBank implements WordBank {
     private final String[] easyWordList = {"Apple", "Animal", "Banana", "Bear", "Bed", "Bird", "Can", "Candy", "Car", "Farm", "Food",
                            "Fish", "Fruit", "Game", "Green", "Horse", "Home", "Light", "Love", "Mouse", "Phone", "Pillow",
                            "Pony", "Shelf", "Tree", "Zebra"};

    /**
     *
     * @return A random word of type String from the easyWordList to be used in the HangmanGame.
     */
    public String getRandomWord() {
        Random random = new Random();
        String randomWord = easyWordList[random.nextInt(easyWordList.length)];
        return randomWord.toLowerCase();
    }
}
