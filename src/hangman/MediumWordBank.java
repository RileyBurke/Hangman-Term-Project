package hangman;

import java.util.Random;

public class MediumWordBank extends WordBank{
     private final String[] mediumWordList = {"Airplane", "Building", "Beautiful", "Cellphone", "Clock", "Computer", "Dance", "Grain",
                                "Hello", "Internet", "Keyboard", "Monitor", "Motorcycle", "Movie", "Potato", "Shelf",
                                "Restaurant", "Sailboat", "Secret", "Television", "Turkey", "Videogame", "Website",
                                "Wheat"};

    public String getRandomWord() {
        Random random = new Random();
        String randomWord = mediumWordList[random.nextInt(mediumWordList.length)];
        return randomWord.toLowerCase();
    }
}
