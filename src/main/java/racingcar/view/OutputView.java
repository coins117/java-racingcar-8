package racingcar.view;

import racingcar.domain.Car;
import racingcar.domain.Cars;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    public void printRaceStart() {
        System.out.println("\n실행 결과");
    }

    public void printRoundResult(Cars cars) {
        cars.getCars().forEach(this::printCarStatus);
        System.out.println();
    }

    private void printCarStatus(Car car) {
        String status = formatCarStatus(car);
        System.out.println(status);
    }

    private String formatCarStatus(Car car) {
        String name = car.getName();
        int position = car.getPosition();
        return name + " : " + "-".repeat(position);
    }

    public void printWinners(List<Car> winners) {
        String winnerNames = formatWinnerNames(winners);
        System.out.println("최종 우승자 : " + winnerNames);
    }

    private String formatWinnerNames(List<Car> winners) {
        return winners.stream()
                .map(Car::getName)
                .collect(Collectors.joining(", "));
    }
}
