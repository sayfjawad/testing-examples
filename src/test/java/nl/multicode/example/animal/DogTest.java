package nl.multicode.example.animal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import nl.multicode.example.sound.Sound;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DogTest {

    public static final String THE_SOUND = "the sound!";
    @Mock
    private Sound sound;

    @InjectMocks
    private Dog dog;

    @Test
    void getSound() {

        when(sound.makeSound()).thenReturn(THE_SOUND);

        final var result = dog.getSound();

        assertThat(result).isEqualTo(THE_SOUND);
        verify(sound).makeSound();

    }
}