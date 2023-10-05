import exceptions.CharacterDeathException;
import io.Input;
import io.Output;
import io.StubOutput;
import model.Gamer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.MemoryGamerRepository;
import repository.MemoryMonsterRepository;
import service.CharacterSelection;
import service.Exit;
import service.StartGame;

import java.util.List;

import static java.lang.System.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import static service.WinLooseConstant.LOOSE_OUT_GAME;
import static service.WinLooseConstant.WINNER_OUT_GAME;

class GameRunnerTest {

    private Output out;
    private Input in;
    private StartGame startGame;

    @BeforeEach
    public void prepare() {
        this.out = new StubOutput();
        this.in = mock(Input.class);
        this.startGame = mock(StartGame.class);
    }

    @Test
    void whenExitGame() {
        GameRunner gameRunner = new GameRunner(out);
        when(in.askInt(anyString())).thenReturn(1);
        gameRunner.exe(in,
                new MemoryGamerRepository(),
                new MemoryMonsterRepository(),
                List.of(new CharacterSelection(out), new Exit(out)),
                startGame);
        assertThat(out.toString()).isEqualTo("==== Меню ====" + lineSeparator()
                                             + "0. Выбор персонажа" + lineSeparator()
                                             + "1. Завершить Игру" + lineSeparator()
                                             + "----- Игра закончилась -----" + lineSeparator());
    }

    @Test
    void whenWinGame() throws CharacterDeathException, InterruptedException {
        GameRunner gameRunner = new GameRunner(out);
        when(in.askInt(anyString())).thenReturn(0).thenReturn(1);
        when(startGame.execute(any(Gamer.class), anyList())).thenReturn(WINNER_OUT_GAME);
        gameRunner.exe(in,
                new MemoryGamerRepository(),
                new MemoryMonsterRepository(),
                List.of(new CharacterSelection(out), new Exit(out)),
                startGame);
        assertThat(out.toString()).isEqualTo("==== Меню ====" + lineSeparator()
                                             + "0. Выбор персонажа" + lineSeparator()
                                             + "1. Завершить Игру" + lineSeparator()
                                             + "==== Выбор персонажа ====" + lineSeparator()
                                             + "1. -= Рыцарь =-" + lineSeparator()
                                             + "  Здоровье:  25" + lineSeparator()
                                             + "  Атака:     18" + lineSeparator()
                                             + "  Защита:    25" + lineSeparator()
                                             + "  Урон:      3 - 9" + lineSeparator()
                                             + "2. -= Варвар =-" + lineSeparator()
                                             + "  Здоровье:  30" + lineSeparator()
                                             + "  Атака:     22" + lineSeparator()
                                             + "  Защита:    18" + lineSeparator()
                                             + "  Урон:      5 - 12" + lineSeparator()
                                             + "3. -= Ниндзя =-" + lineSeparator()
                                             + "  Здоровье:  20" + lineSeparator()
                                             + "  Атака:     30" + lineSeparator()
                                             + "  Защита:    15" + lineSeparator()
                                             + "  Урон:      15 - 20" + lineSeparator() + lineSeparator()
                                             + "--- Поздравляем, вы победили! ---" + lineSeparator() + lineSeparator()
                                             + "==== Меню ====" + lineSeparator()
                                             + "0. Выбор персонажа" + lineSeparator()
                                             + "1. Завершить Игру" + lineSeparator()
                                             + "----- Игра закончилась -----" + lineSeparator()
        );
    }


    @Test
    void whenLooseGame() throws CharacterDeathException, InterruptedException {
        GameRunner gameRunner = new GameRunner(out);
        when(in.askInt(anyString())).thenReturn(0).thenReturn(1);
        when(startGame.execute(any(Gamer.class), anyList())).thenReturn(LOOSE_OUT_GAME);
        gameRunner.exe(in,
                new MemoryGamerRepository(),
                new MemoryMonsterRepository(),
                List.of(new CharacterSelection(out), new Exit(out)),
                startGame);
        assertThat(out.toString()).isEqualTo("==== Меню ====" + lineSeparator()
                                             + "0. Выбор персонажа" + lineSeparator()
                                             + "1. Завершить Игру" + lineSeparator()
                                             + "==== Выбор персонажа ====" + lineSeparator()
                                             + "1. -= Рыцарь =-" + lineSeparator()
                                             + "  Здоровье:  25" + lineSeparator()
                                             + "  Атака:     18" + lineSeparator()
                                             + "  Защита:    25" + lineSeparator()
                                             + "  Урон:      3 - 9" + lineSeparator()
                                             + "2. -= Варвар =-" + lineSeparator()
                                             + "  Здоровье:  30" + lineSeparator()
                                             + "  Атака:     22" + lineSeparator()
                                             + "  Защита:    18" + lineSeparator()
                                             + "  Урон:      5 - 12" + lineSeparator()
                                             + "3. -= Ниндзя =-" + lineSeparator()
                                             + "  Здоровье:  20" + lineSeparator()
                                             + "  Атака:     30" + lineSeparator()
                                             + "  Защита:    15" + lineSeparator()
                                             + "  Урон:      15 - 20" + lineSeparator() + lineSeparator()
                                             + "--- Вы проиграли ---" + lineSeparator() + lineSeparator()
                                             + "==== Меню ====" + lineSeparator()
                                             + "0. Выбор персонажа" + lineSeparator()
                                             + "1. Завершить Игру" + lineSeparator()
                                             + "----- Игра закончилась -----" + lineSeparator()
        );
    }
}