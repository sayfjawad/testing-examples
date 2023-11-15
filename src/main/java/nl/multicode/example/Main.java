package nl.multicode.example;

import nl.multicode.example.animal.Dog;
import nl.multicode.example.sound.BarkingSound;

public class Main {

    public static void main(String[] args) {

        final Dog dog = new Dog(new BarkingSound());

        System.out.println("Dog makes " + dog.getSound() + " sound!");
    }
}