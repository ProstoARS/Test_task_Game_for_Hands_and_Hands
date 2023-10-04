package model;

import exceptions.CharacterDeathException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

class GamerTest {

    @Test
    public void whenGamerRecoveryHealth() throws CharacterDeathException {
        Gamer gamer = new Gamer(anyString(), anyInt(), anyInt(), 30, any(Damage.class));
        gamer.setHealth(10);
        int result = gamer.getHealth() + gamer.recoveryHealth();
        assertThat(result).isEqualTo(19)
                .isEqualTo(gamer.getHealth());
    }

    @Test
    public void whenGamerRecoveryHealthThenHealthNotRiseMaxValue() throws CharacterDeathException {
        Gamer gamer = new Gamer(anyString(), anyInt(), anyInt(), 30, any(Damage.class));
        gamer.setHealth(25);
        int result = gamer.getHealth() + gamer.recoveryHealth();
        assertThat(result).isEqualTo(30)
                .isEqualTo(gamer.getHealth());
    }

}