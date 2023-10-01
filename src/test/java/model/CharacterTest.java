package model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

class CharacterTest {

    @Test
    public void whenCheckDeath() {
        Gamer gamer = new Gamer(anyString(), anyInt(), anyInt(), 30, any(Damage.class));
        gamer.setHealth(0);
        assertThat(gamer.checkDeathCharacter()).isTrue();
    }

}