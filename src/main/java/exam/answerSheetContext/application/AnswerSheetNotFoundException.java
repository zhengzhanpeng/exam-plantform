package exam.answerSheetContext.application;

public class AnswerSheetNotFoundException extends RuntimeException {
    public AnswerSheetNotFoundException() {
        super("answer sheet not found");
    }
}
