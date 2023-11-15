package nl.multicode.example.sound;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class HowlingSoundTest {

    @Test
    void makeSound() {

        assertThat(new HowlingSound().makeSound()).isEqualTo("'howling'");
    }
}