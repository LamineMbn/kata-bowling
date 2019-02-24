package fr.soat.masterclass;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<Integer> rolls = new ArrayList<>(21);
    private int score;

    public void roll(int pins) {
        rolls.add(pins);
    }

    public int score() {
        int frameIndex = 0;

        for (int frame = 0; frame < 10; frame++) {
            if (isStrike(frameIndex)) {
                score += 10 + strikeBonus(frameIndex);
                frameIndex++;
            } else if (isSpare(frameIndex)) {
                score += 10 + spareBonus(frameIndex);
                frameIndex += 2;
            } else {
                score += sumOfPinsInFrame(frameIndex);
                frameIndex += 2;
            }
        }

        return score;
    }

    private int sumOfPinsInFrame(int frameIndex) {
        return rolls.get(frameIndex) + rolls.get(frameIndex + 1);
    }

    private int spareBonus(int frameIndex) {
        return rolls.get(frameIndex + 2);
    }

    private int strikeBonus(int frameIndex) {
        return rolls.get(frameIndex + 1) + rolls.get(frameIndex + 2);
    }

    private boolean isStrike(int frameIndex) {
        return rolls.get(frameIndex) == 10;
    }

    private boolean isSpare(int i) {
        return sumOfPinsInFrame(i) == 10;
    }
}
