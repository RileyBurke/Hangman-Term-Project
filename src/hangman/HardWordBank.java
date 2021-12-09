package hangman;

import java.util.Random;

/**
 * Implements the WordBank interface with an array of words and a method to return a random word from that array.
 */
public class HardWordBank implements WordBank{
    private final String[] hardWordList = {"Abruptly", "Affix", "Bandwagon", "Buckaroo", "Cockiness", "Croquet", "Disavow", "Flapjack",
                            "Fuchsia", "Glowworm", "Iatrogenic", "Jukebox", "Kiwifruit", "Knapsack", "Megahertz", "Numbskull",
                            "Pneumonia", "Razzmatazz", "Stymied", "Transgress", "Voyeurism", "Whizzing", "Zigzagging"};

    /**
     *
     * @return A random word of type String from the hardWordList to be used in the HangmanGame.
     */
    public String getRandomWord() {
        Random random = new Random();
        String randomWord = hardWordList[random.nextInt(hardWordList.length)];
        return randomWord.toLowerCase();
    }
}
