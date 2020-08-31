package exam.answerSheetContext.domain.model.paper;

import exam.answerSheetContext.ValueObject;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Collections;
import java.util.List;

@EqualsAndHashCode
public class Paper implements ValueObject<Paper> {
    private String paperId;

    private List<BlankQuiz> blankQuizzes;

    public Paper(String paperId, List<BlankQuiz> blankQuizzes) {
        this.paperId = paperId;
        this.blankQuizzes = blankQuizzes;
    }

    @Override
    public boolean sameValueAs(Paper other) {
        return this.equals(other);
    }

    public List<BlankQuiz> getBlankQuizzes() {
        return Collections.unmodifiableList(this.blankQuizzes);
    }

    @Getter
    @AllArgsConstructor
    public static class BlankQuiz implements ValueObject<BlankQuiz> {
        private String quizId;
        private String answer;
        private int score;

        @Override
        public boolean sameValueAs(BlankQuiz other) {
            return false;
        }
    }
}
