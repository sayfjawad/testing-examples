package nl.multicode.example.animal;

import nl.multicode.example.sound.Sound;

public class Dog {

    private final Sound sound;

    public Dog(Sound sound) {

        this.sound = sound;
    }

    public String getSound() {

        return sound.makeSound();
    }
}
