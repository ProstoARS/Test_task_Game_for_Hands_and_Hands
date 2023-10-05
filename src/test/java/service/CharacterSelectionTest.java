package service;

import io.Input;
import io.Output;
import io.StubOutput;
import model.Damage;
import model.Gamer;
import org.junit.jupiter.api.Test;
import repository.IGamerRepository;
import repository.IMonsterRepository;
import repository.MemoryMonsterRepository;

import java.util.List;

import static java.lang.System.lineSeparator;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CharacterSelectionTest {

    @Test
    public void whenChooseCharacter() {
        Input in = mock(Input.class);
        Output out = new StubOutput();
        when(in.askInt(anyString())).thenReturn(1);
        IGamerRepository gamerRepository = mock(IGamerRepository.class);
        when(gamerRepository.findAll()).thenReturn(List.of(new Gamer(
                "Рыцарь", 15, 25, 30, new Damage(3, 9))));
        IMonsterRepository monsterRepository = new MemoryMonsterRepository();
        UserAction selection = new CharacterSelection(out);
        int rsl = selection.execute(in, gamerRepository, monsterRepository);
        assertThat(out.toString()).isEqualTo("==== Выбор персонажа ====" + lineSeparator()
                                             + "1. -= Рыцарь =-" + lineSeparator()
                                             + "  Здоровье:  30" + lineSeparator()
                                             + "  Атака:     15" + lineSeparator()
                                             + "  Защита:    25" + lineSeparator()
                                             + "  Урон:      3 - 9" + lineSeparator());
        assertThat(rsl).isEqualTo(0);
    }

    @Test
    public void whenWrongInput() {
        Input in = mock(Input.class);
        Output out = new StubOutput();
        when(in.askInt(anyString())).thenReturn(2).thenReturn(1);
        IGamerRepository gamerRepository = mock(IGamerRepository.class);
        when(gamerRepository.findAll()).thenReturn(List.of(new Gamer(
                "Рыцарь", 15, 25, 30, new Damage(3, 9))));
        IMonsterRepository monsterRepository = new MemoryMonsterRepository();
        UserAction selection = new CharacterSelection(out);
        selection.execute(in, gamerRepository, monsterRepository);
        assertThat(out.toString()).isEqualTo("==== Выбор персонажа ====" + lineSeparator()
                                             + "1. -= Рыцарь =-" + lineSeparator()
                                             + "  Здоровье:  30" + lineSeparator()
                                             + "  Атака:     15" + lineSeparator()
                                             + "  Защита:    25" + lineSeparator()
                                             + "  Урон:      3 - 9" + lineSeparator()
                                             + "Неправильный ввод, выберите: 1 .. 1" + lineSeparator()
                                             + "1. -= Рыцарь =-" + lineSeparator()
                                             + "  Здоровье:  30" + lineSeparator()
                                             + "  Атака:     15" + lineSeparator()
                                             + "  Защита:    25" + lineSeparator()
                                             + "  Урон:      3 - 9" + lineSeparator());
    }
}