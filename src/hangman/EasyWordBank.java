package hangman;

import java.util.Random;

public class EasyWordBank extends WordBank {
    String[] easyWordList = {"Apple", "Animal", "Banana", "Bear", "Bed", "Bird", "Can", "Candy", "Car", "Farm", "Food",
                            "Fish", "Fruit", "Game", "Green", "Horse", "Home", "Light", "Love", "Mouse", "Phone", "Pillow",
                            "Pony", "Shelf", "Tree", "Zebra"};

    public String getRandomWord() {
        Random random = new Random();
        String randomWord = easyWordList[random.nextInt(easyWordList.length)];
        return randomWord.toLowerCase();
    }
}
