package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.exception.ErrorCode;

class CarsTest {

    @Test
    @DisplayName("우승자를 찾는다")
    void findWinners() {
        MoveStrategy alwaysMove = () -> true;
        MoveStrategy neverMove = () -> false;

        List<Car> testCars = List.of(
                new Car("pobi", alwaysMove),
                new Car("woni", neverMove),
                new Car("jun", alwaysMove)
        );

        testCars.forEach(Car::move);

        Cars cars = new Cars(testCars);
        List<Car> winners = cars.findWinners();

        assertThat(winners)
                .hasSize(2)
                .extracting(Car::getName)
                .containsExactly("pobi", "jun");
    }

    @Test
    @DisplayName("중복된 자동차 이름이 있으면 예외가 발생한다")
    void duplicateCarNames() {
        MoveStrategy moveStrategy = () -> true;

        List<Car> testCars = List.of(
                new Car("pobi", moveStrategy),
                new Car("woni", moveStrategy),
                new Car("pobi", moveStrategy)
        );

        assertThatThrownBy(() -> new Cars(testCars))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorCode.DUPLICATE_CAR_NAME.getMessage());
    }
}
