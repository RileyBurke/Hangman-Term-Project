package hangman;

import java.util.Random;

/**
 * Implements the WordBank interface with an array of words and a method to return a random word from that array.
 */
public class MediumWordBank implements WordBank{
     private final String[] mediumWordList = {"Airplane", "Building", "Beautiful", "Cellphone", "Clock", "Computer", "Dance", "Grain",
                                "Hello", "Internet", "Keyboard", "Monitor", "Motorcycle", "Movie", "Potato", "Shelf",
                                "Restaurant", "Sailboat", "Secret", "Television", "Turkey", "Videogame", "Website",
                                "Wheat"};

    /**
     *
     * @return A random word of type String from the mediumWordList to be used in the HangmanGame.
     */
    public String getRandomWord() {
        Random random = new Random();
        String randomWord = mediumWordList[random.nextInt(mediumWordList.length)];
        return randomWord.toLowerCase();
    }
}
