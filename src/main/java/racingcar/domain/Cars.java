package racingcar.domain;

import java.util.List;
import racingcar.exception.ErrorCode;

public class Cars {

    private final List<Car> cars;

    public Cars(List<Car> cars) {
        validateDuplicateNames(cars);
        this.cars = List.copyOf(cars);
    }

    private void validateDuplicateNames(List<Car> cars) {
        long uniqueNameCount = cars.stream()
                .map(Car::getName)
                .distinct()
                .count();

        if (uniqueNameCount != cars.size()) {
            throw new IllegalArgumentException(ErrorCode.DUPLICATE_CAR_NAME.getMessage());
        }
    }

    public void playRound() {
        cars.forEach(Car::move);
    }

    public List<Car> findWinners() {
        int maxPosition = findMaxPosition();
        return findCarsWithPosition(maxPosition);
    }

    private int findMaxPosition() {
        return cars.stream()
                .mapToInt(Car::getPosition)
                .max()
                .orElse(0);
    }

    private List<Car> findCarsWithPosition(int position) {
        return cars.stream()
                .filter(car -> car.isAtPosition(position))
                .toList();
    }

    public List<Car> getCars() {
        return cars;
    }
}
