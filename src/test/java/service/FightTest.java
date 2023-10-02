package service;

import model.Damage;
import model.Gamer;
import model.Monster;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class FightTest {

    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    public void cleanUpStreams() {
        System.setOut(null);
    }

    @Test
    public void whenCharactersFightOneRoundThenHPDecrease() {
        try (MockedStatic<GameLogic> mockedGameLogic = mockStatic(GameLogic.class)) {
            mockedGameLogic.when(GameLogic::gameCubeAction).thenReturn(5);
            Damage damage = mock(Damage.class);
            when(damage.attackValue()).thenReturn(5);
            GameLogic gameLogic = new GameLogic();
            Gamer gamer = new Gamer("Рыцарь", 15, 30, 30, damage);
            Monster monster = new Monster("Скелет", 5, 10, 15, new Damage(1, 3));
            Fight fight = new Fight(gameLogic);
            fight.startOneRoundFight(gamer, monster);
            assertThat(monster.getHealth()).isEqualTo(10);
            assertThat("Здоровье персонажа Скелет уменьшилось на 5 и стало равно 10 hp.")
                    .isEqualTo(output.toString());
        }
    }

    @Test
    public void whenCharactersFightOneRoundThenAttackFail() {
        try (MockedStatic<GameLogic> mockedGameLogic = mockStatic(GameLogic.class)) {
            mockedGameLogic.when(GameLogic::gameCubeAction).thenReturn(3);
            GameLogic gameLogic = new GameLogic();
            Gamer gamer = new Gamer("Рыцарь", 15, 30, 30, new Damage(3, 6));
            Monster monster = new Monster("Скелет", 5, 10, 15, new Damage(1, 3));
            Fight fight = new Fight(gameLogic);
            fight.startOneRoundFight(gamer, monster);
            assertThat(monster.getHealth()).isEqualTo(15);
            assertThat("Персонаж Скелет отразил атаку").isEqualTo(output.toString());
        }
    }

    @Test
    public void whenCharactersFightOneRoundThenCharacterDeath() {
        try (MockedStatic<GameLogic> mockedGameLogic = mockStatic(GameLogic.class)) {
            mockedGameLogic.when(GameLogic::gameCubeAction).thenReturn(5);
            Damage damage = mock(Damage.class);
            when(damage.attackValue()).thenReturn(5);
            GameLogic gameLogic = new GameLogic();
            Gamer gamer = new Gamer("Рыцарь", 15, 30, 30, damage);
            Monster monster = new Monster("Скелет", 5, 10, 5, new Damage(1, 3));
            Fight fight = new Fight(gameLogic);
            fight.startOneRoundFight(gamer, monster);
            assertThat("Персонаж Скелет умер.").isEqualTo(output.toString());
        }
    }



}