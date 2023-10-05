package model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class DamageTest {

    @Test
    void whenAttackRange() {
        Damage damage = new Damage(3, 8);
        int result = damage.attackValue();
        assertThat(result).isIn(List.of(3, 4, 5, 6, 7, 8));
    }

}