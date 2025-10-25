package racingcar.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import racingcar.exception.ErrorCode;

public class InputView {

    private static final String CAR_NAME_DELIMITER = ",";

    public List<String> readCarNames() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String input = Console.readLine();

        validateNotBlank(input);
        return splitCarNames(input);
    }

    private void validateNotBlank(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(ErrorCode.BLANK_INPUT.getMessage());
        }
    }

    private List<String> splitCarNames(String input) {
        return Arrays.stream(input.split(CAR_NAME_DELIMITER))
                .map(String::trim)
                .toList();
    }

    public int readAttemptCount() {
        System.out.println("시도할 횟수는 몇 회인가요?");
        String input = Console.readLine();

        validateNotBlank(input);
        int attemptCount = parseInteger(input);
        validatePositive(attemptCount);
        return attemptCount;
    }

    private int parseInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorCode.INVALID_ATTEMPT_COUNT_FORMAT.getMessage());
        }
    }

    private void validatePositive(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException(ErrorCode.INVALID_ATTEMPT_COUNT_RANGE.getMessage());
        }
    }
}
