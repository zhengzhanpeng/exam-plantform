package exam.answerSheetContext.application;

import lombok.Data;

import java.util.List;

@Data
public class AssignAnswerSheetCommand {
    private String studentId;

    private Paper paper;

    @Data
    public static class Paper {

        private String paperId;

        private List<BlankQuiz> blankQuizzes;

        @Data
        public static class BlankQuiz {
            private String quizId;
            private String answer;
            private int score;
        }
    }
}
