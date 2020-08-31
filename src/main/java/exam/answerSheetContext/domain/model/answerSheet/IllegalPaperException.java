package exam.answerSheetContext.domain.model.answerSheet;

class IllegalPaperException extends IllegalArgumentException {

    public IllegalPaperException(String exceptionPaperId, String actualPaperId) {
        super(String.format("NotCurrentPaperException: exception:%s, actual:%s" , exceptionPaperId, actualPaperId));
    }
}
