package model;

import exceptions.CharacterDeathException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

class CharacterTest {

    @Test
    public void whenCheckDeath() {
        Gamer gamer = new Gamer(anyString(), anyInt(), anyInt(), 30, any(Damage.class));
        assertThat(gamer.checkDeathCharacter(0)).isTrue();
    }

    @Test
    public void whenCharacterDeathThenTrowsException() {
        Gamer gamer = new Gamer(anyString(), anyInt(), anyInt(), 30, any(Damage.class));
        assertThatThrownBy(() -> gamer.setHealth(-1)).isInstanceOf(CharacterDeathException.class)
                .hasMessageContaining("Персонаж умер");
    }

}