package racingcar.domain;

import racingcar.exception.ErrorCode;

public class Car {

    public static final int MAX_NAME_LENGTH = 5;
    private static final int INITIAL_POSITION = 0;

    private final String name;
    private final Engine engine;
    private int position;

    public Car(String name, Engine engine) {
        validateName(name);
        this.name = name;
        this.engine = engine;
        this.position = INITIAL_POSITION;
    }

    private void validateName(String name) {
        validateNameNotBlank(name);
        validateNameLength(name);
    }

    private void validateNameNotBlank(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(ErrorCode.BLANK_CAR_NAME.getMessage());
        }
    }

    private void validateNameLength(String name) {
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(ErrorCode.INVALID_CAR_NAME_LENGTH.getMessage());
        }
    }

    public void move() {
        if (engine.canMove()) {
            position++;
        }
    }

    public boolean isAtPosition(int position) {
        return this.position == position;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
