package exam.answerSheetContext.domain.model.answerSheet;

import exam.answerSheetContext.shared.ValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
public class Answer implements ValueObject {

    @Getter
    private String blankQuizId;

    @Getter
    private String answer;

    @Getter
    private Integer score;

    Answer(String blankQuizId, Integer score) {
        this.blankQuizId = blankQuizId;
        this.score = score;
    }

    public Answer(String blankQuizId, String answer) {
        this.blankQuizId = blankQuizId;
        this.answer = answer;
    }

    void setAnswer(String answer) {
        this.answer = answer;
    }

    void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public boolean sameValueAs(Object other) {
        return this.equals(other);
    }
}
