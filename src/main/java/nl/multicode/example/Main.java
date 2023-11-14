package nl.multicode.example;

import nl.multicode.example.animal.Dog;
import nl.multicode.example.sound.DogSound;

public class Main {

    public static void main(String[] args) {

        final Dog dog = new Dog(new DogSound());

        System.out.println("Dog makes " + dog.getSound() + " sound!");
    }
}