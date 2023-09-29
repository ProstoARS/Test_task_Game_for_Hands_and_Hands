import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import service.GameLogic;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GameLogicTest {

    @Test
    public void whenAttackModifierCalculation() {
        GameLogic gameLogic = new GameLogic();
        int attack = 5;
        int protection = 5;
        int result = gameLogic.attackModifier(attack, protection);
        assertThat(result).isEqualTo(1);
    }

    @Test
    public void whenGameCubeAction() {
        int result = GameLogic.gameCubeAction();
        assertThat(result).isIn(List.of(1, 2, 3, 4, 5, 6));
    }

    @Test
    public void whenSuccessfulAttack() {
        try (MockedStatic<GameLogic> mockedStatic = mockStatic(GameLogic.class)) {
            mockedStatic.when(GameLogic::gameCubeAction).thenReturn(5);
            GameLogic gameLogic = new GameLogic();
            boolean result = gameLogic.checkAttack(3);
            assertThat(result).isTrue();
        }
    }

    @Test
    public void whenFailAttack() {
        try (MockedStatic<GameLogic> mockedStatic = mockStatic(GameLogic.class)) {
            mockedStatic.when(GameLogic::gameCubeAction).thenReturn(3);
            GameLogic gameLogic = new GameLogic();
            boolean result = gameLogic.checkAttack(3);
            assertThat(result).isFalse();
        }
    }
}
