package service;

import exceptions.CharacterDeathException;
import io.Input;
import io.Output;
import io.StubOutput;
import model.Damage;
import model.Gamer;
import model.Monster;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static service.WinLooseConstant.LOOSE_OUT_GAME;
import static service.WinLooseConstant.WINNER_OUT_GAME;

class StartGameTest {

    private StartGame startGame;
    private Input input;
    private Fight fight;
    private Gamer knight;
    private Monster troll;

    @BeforeEach
    public void prepare() {
        this.input = mock(Input.class);
        this.fight = mock(Fight.class);
        Output output = new StubOutput();
        this.knight = new Gamer("Рыцарь", 15, 25, 30, new Damage(3, 9));
        this.troll = new Monster("Троль", 15, 20, 20, new Damage(1, 8));
        this.startGame = new StartGame(input, output, fight);
    }

    @Test
    void whenGamerWin() throws CharacterDeathException, InterruptedException {
        when(input.askInt(anyString())).thenReturn(1);
        when(fight.startOneRoundFight(any(Gamer.class), any(Monster.class))).thenReturn(true);
        int rsl = startGame.execute(knight, List.of(troll));
        assertThat(rsl).isEqualTo(WINNER_OUT_GAME);
    }

    @Test
    void whenGamerLoose() throws CharacterDeathException, InterruptedException {
        when(fight.startOneRoundFight(any(Monster.class), any(Gamer.class))).thenReturn(true);
        int rsl = startGame.execute(knight, List.of(troll));
        assertThat(rsl).isEqualTo(LOOSE_OUT_GAME);
    }

}