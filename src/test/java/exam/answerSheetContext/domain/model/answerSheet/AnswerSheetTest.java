package exam.answerSheetContext.domain.model.answerSheet;

import exam.answerSheetContext.domain.model.paper.Paper;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

class AnswerSheetTest {

    @Test
    void should_assign_answer_sheet_successful() {
        String studentId = "studentId";
        Paper.BlankQuiz blankQuiz = new Paper.BlankQuiz("quizId", "answer", 10);
        Paper paper = new Paper("paperId", Collections.singletonList(blankQuiz));

        AnswerSheet answerSheet = AnswerSheet.assignAnswerSheet(paper, studentId);


        assertEquals(studentId, answerSheet.getStudentId());
        assertEquals(answerSheet.getPaper(), paper);
        assertNotNull(answerSheet.getAnswerSheetId());

        List<Answer> answers = answerSheet.getAnswers();
        assertEquals(1, answers.size());

        Answer answer = answers.get(0);
        assertNull(answer.getAnswer());
        assertEquals(answer.getBlankQuizId(), blankQuiz.getQuizId());
        assertSame(0, answer.getScore());
    }
}