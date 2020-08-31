package exam.answerSheetContext.domain.model.answerSheet;

import exam.answerSheetContext.shared.ValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@NoArgsConstructor
public class Answer implements ValueObject {

    @Getter
    private String blankQuizId;

    @Getter
    private String answer;

    @Getter
    private Integer score;

    public Answer(String blankQuizId, Integer score) {
        this.blankQuizId = blankQuizId;
        this.score = score;
    }

    @Override
    public boolean sameValueAs(Object other) {
        return this.equals(other);
    }
}
