package racingcar;

import java.util.List;
import java.util.stream.IntStream;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.engine.RandomMoveStrategy;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingGame {

    private final InputView inputView;
    private final OutputView outputView;

    public RacingGame(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        List<String> carNames = inputView.readCarNames();
        Cars cars = createCars(carNames);
        int attemptCount = inputView.readAttemptCount();

        play(cars, attemptCount);
        printWinners(cars);
    }

    private Cars createCars(List<String> carNames) {
        List<Car> cars = carNames.stream()
                .map(name -> new Car(name, new RandomMoveStrategy()))
                .toList();
        return new Cars(cars);
    }

    private void play(Cars cars, int attemptCount) {
        outputView.printRaceStart();
        playRounds(cars, attemptCount);
    }

    private void playRounds(Cars cars, int attemptCount) {
        IntStream.range(0, attemptCount)
                .forEach(i -> playRound(cars));
    }

    private void playRound(Cars cars) {
        cars.playRound();
        outputView.printRoundResult(cars);
    }

    private void printWinners(Cars cars) {
        List<Car> winners = cars.findWinners();
        outputView.printWinners(winners);
    }
}
