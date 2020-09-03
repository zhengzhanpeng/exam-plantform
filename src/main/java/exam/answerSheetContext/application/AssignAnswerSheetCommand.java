package exam.answerSheetContext.application;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssignAnswerSheetCommand {
    private String studentId;

    private Paper paper;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Paper {

        private String paperId;

        private List<BlankQuiz> blankQuizzes;

        @Data
        @AllArgsConstructor
        public static class BlankQuiz {
            private String quizId;
            private String answer;
            private int score;
        }
    }
}
