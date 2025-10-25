package racingcar.domain;

import java.util.List;

public class Cars {

    private final List<Car> cars;

    public Cars(List<Car> cars) {
        this.cars = List.copyOf(cars);
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
