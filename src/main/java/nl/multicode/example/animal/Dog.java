package nl.multicode.example.animal;

import nl.multicode.example.sound.DogSound;
import nl.multicode.example.sound.Sound;

public class Dog {

    private final Sound sound;

    public Dog(Sound sound) {

        this.sound = new DogSound();
    }

    public String getSound() {

        return sound.makeSound();
    }
}
