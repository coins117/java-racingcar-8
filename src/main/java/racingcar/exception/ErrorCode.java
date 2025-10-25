package racingcar.exception;

import racingcar.domain.Car;

public enum ErrorCode {

    BLANK_INPUT("입력값이 비어있습니다."),
    INVALID_ATTEMPT_COUNT_FORMAT("시도 횟수는 숫자여야 합니다."),
    INVALID_ATTEMPT_COUNT_RANGE("시도 횟수는 양수여야 합니다."),
    BLANK_CAR_NAME("자동차 이름은 비어있을 수 없습니다."),
    INVALID_CAR_NAME_LENGTH("자동차 이름은 %d자 이하여야 합니다.".formatted(Car.MAX_NAME_LENGTH));

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
