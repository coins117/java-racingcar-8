package racingcar.domain.engine;

import camp.nextstep.edu.missionutils.Randoms;
import racingcar.domain.Engine;

public class RandomEngine implements Engine {

    private static final int MIN_RANDOM_VALUE = 0;
    private static final int MAX_RANDOM_VALUE = 9;
    private static final int MOVE_THRESHOLD = 4;

    @Override
    public boolean canMove() {
        int randomValue = Randoms.pickNumberInRange(MIN_RANDOM_VALUE, MAX_RANDOM_VALUE);
        return isMovable(randomValue);
    }

    private boolean isMovable(int value) {
        return value >= MOVE_THRESHOLD;
    }
}
