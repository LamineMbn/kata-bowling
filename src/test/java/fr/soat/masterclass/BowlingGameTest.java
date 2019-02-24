package fr.soat.masterclass;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class BowlingGameTest {

    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
    }

    @Test
    void should_return_score_of_zero_when_no_pins_touched() {

        rollMany(20,0);

        assertThat(game.score()).isEqualTo(0);
    }

    @Test
    void should_return_score_of_20_when_one_pin_touched_per_roll() {

        rollMany(20,1);

        assertThat(game.score()).isEqualTo(20);
    }

    @Test
    void should_return_score_16_if_one_spare_and_all_zeros() {
        rollSpare();
        game.roll(3);
        rollMany(17,0);

        assertThat(game.score()).isEqualTo(16);

    }

    @Test
    void should_return_score_26_if_one_strike_and_all_zeros() {
        game.roll(10); //strike
        game.roll(3);
        game.roll(4);
        rollMany(16,0);

        assertThat(game.score()).isEqualTo(24);

    }

    @Test
    void should_return_score_300_if_perfect_game() {
        rollMany(12,10);

        assertThat(game.score()).isEqualTo(300);

    }

    private void rollSpare() {
        game.roll(5);
        game.roll(5);
    }

    private void rollMany(int rolls, int fallenPins) {
        IntStream.rangeClosed(1, rolls).forEach(roll -> game.roll(fallenPins));
    }
}
