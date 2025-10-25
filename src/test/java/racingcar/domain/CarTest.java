package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.exception.ErrorCode;

class CarTest {

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"", " ", "\t", "\n"})
    @DisplayName("이름이 비어있으면 예외가 발생한다")
    void blankCarName(String name) {
        MoveStrategy moveStrategy = () -> true;

        assertThatThrownBy(() -> new Car(name, moveStrategy))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorCode.BLANK_CAR_NAME.getMessage());
    }

    @Test
    @DisplayName("이름이 일정 길이를 초과하면 예외가 발생한다")
    void invalidCarNameMaxLength() {
        MoveStrategy moveStrategy = () -> true;
        String longName = "a".repeat(Car.MAX_NAME_LENGTH + 1);

        assertThatThrownBy(() -> new Car(longName, moveStrategy))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorCode.INVALID_CAR_NAME_LENGTH.getMessage());
    }

    @Test
    @DisplayName("전진 조건이 만족되면 자동차가 전진한다")
    void moveForward() {
        MoveStrategy moveStrategy = () -> true;
        Car car = new Car("pobi", moveStrategy);

        car.move();

        assertThat(car.getPosition()).isEqualTo(1);
    }

    @Test
    @DisplayName("전진 조건이 만족되지 않으면 자동차가 정지한다")
    void stop() {
        MoveStrategy moveStrategy = () -> false;
        Car car = new Car("pobi", moveStrategy);

        car.move();

        assertThat(car.getPosition()).isZero();
    }
}
