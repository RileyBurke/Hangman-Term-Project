package hangman;

import java.util.Random;

public class HardWordBank extends WordBank{
    private final String[] hardWordList = {"Abruptly", "Affix", "Bandwagon", "Buckaroo", "Cockiness", "Croquet", "Disavow", "Flapjack",
                            "Fuchsia", "Glowworm", "Iatrogenic", "Jukebox", "Kiwifruit", "Knapsack", "Megahertz", "Numbskull",
                            "Pneumonia", "Razzmatazz", "Stymied", "Transgress", "Voyeurism", "Whizzing", "Zigzagging"};

    public String getRandomWord() {
        Random random = new Random();
        String randomWord = hardWordList[random.nextInt(hardWordList.length)];
        return randomWord.toLowerCase();
    }
}

//Returns one random word from the list to be used in the game.
