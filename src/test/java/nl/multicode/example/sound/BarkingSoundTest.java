package nl.multicode.example.sound;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class BarkingSoundTest {

    @Test
    void makeSound() {

        assertThat(new BarkingSound().makeSound()).isEqualTo("'barking'");
    }
}