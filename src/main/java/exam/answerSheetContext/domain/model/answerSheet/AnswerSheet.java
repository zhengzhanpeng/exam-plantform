package exam.answerSheetContext.domain.model.answerSheet;

import exam.answerSheetContext.domain.model.paper.Paper;
import exam.answerSheetContext.shared.Entity;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "answerSheetId")
public class AnswerSheet implements Entity {

    @Getter
    private AnswerSheetId answerSheetId;

    @Getter
    private String studentId;

    @Getter
    private Paper paper;

    private List<Answer> answers;

    @Override
    public boolean sameIdentityAs(Object other) {
        return this.equals(other);
    }

    public static AnswerSheet assignAnswerSheet(Paper paper, String studentId) {
        AnswerSheet answerSheet = new AnswerSheet();
        answerSheet.paper = paper;
        answerSheet.studentId = studentId;
        answerSheet.answers = paper.getBlankQuizzes()
                                   .stream()
                                   .map(blankQuiz -> new Answer(blankQuiz.getQuizId(), 0))
                                   .collect(Collectors.toList());
        answerSheet.answerSheetId = AnswerSheetId.nextId();
        return answerSheet;
    }

    public List<Answer> getAnswers() {
        return Collections.unmodifiableList(this.answers);
    }
}
