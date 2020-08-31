package exam.answerSheetContext.domain.model.answerSheet;

class IllegalStudentException extends IllegalArgumentException {

    public IllegalStudentException(String exceptionStudentId, String actualStudentId) {
        super(String.format("NotOwnerAnswerSheetException: exception:%s, actual:%s" , exceptionStudentId, actualStudentId));
    }
}
