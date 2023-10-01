package model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

class GamerTest {

    @Test
    public void whenGamerRecoveryHealth() {
        Gamer gamer = new Gamer(anyString(), anyInt(), anyInt(), 30, any(Damage.class));
        gamer.setHealth(10);
        int result = gamer.recoveryHealth();
        assertThat(result).isEqualTo(19)
                .isEqualTo(gamer.getHealth());
    }

}