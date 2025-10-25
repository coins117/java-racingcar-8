package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}
