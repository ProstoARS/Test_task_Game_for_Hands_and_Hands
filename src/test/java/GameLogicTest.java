import org.junit.jupiter.api.Test;
import service.GameLogic;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class GameLogicTest {

    @Test
    public void whenAttackModifierCalculation() {
        int attack = 5;
        int protection = 5;
        int result = GameLogic.attackModifier(attack, protection);
        assertThat(result).isEqualTo(1);
    }

    @Test
    public void whenGameCubeAction() {
        int result = GameLogic.gameCubeAction();
        assertThat(result).isIn(List.of(1, 2, 3, 4, 5, 6));
    }
}
