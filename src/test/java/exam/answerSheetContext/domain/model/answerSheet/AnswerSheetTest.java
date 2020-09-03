package exam.answerSheetContext.domain.model.answerSheet;

import exam.answerSheetContext.domain.model.paper.Paper;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    void should_submit_answer_sheet_successful() {
        String studentId = "studentId";
        String quizId = "quizId";
        Paper.BlankQuiz blankQuiz = new Paper.BlankQuiz(quizId, "answer", 10);
        Paper paper = new Paper("paperId", Collections.singletonList(blankQuiz));

        AnswerSheet answerSheet = AnswerSheet.assignAnswerSheet(paper, studentId);

        Answer answer = new Answer(quizId, "answer");

        answerSheet.submitAnswerSheet(studentId, paper.getPaperId(), Collections.singletonList(answer));

        Answer answerAtAnswerSheet = answerSheet.getAnswers().get(0);
        assertEquals("answer", answerAtAnswerSheet.getAnswer());
    }

    @Test
    void should_throw_IllegalPaperException_when_submit_paper_is_not_match_current_paper() {
        String studentId = "studentId";
        String quizId = "quizId";
        Paper.BlankQuiz blankQuiz = new Paper.BlankQuiz(quizId, "answer", 10);
        Paper paper = new Paper("paperId", Collections.singletonList(blankQuiz));

        AnswerSheet answerSheet = AnswerSheet.assignAnswerSheet(paper, studentId);

        Answer answer = new Answer(quizId, "answer");

        assertThrows(IllegalPaperException.class,
                () -> answerSheet.submitAnswerSheet(studentId, "errorPaperId", Collections.singletonList(answer))
        );
    }

    @Test
    void should_throw_IllegalStudentException_when_submit_paper_is_owner_student() {
        String studentId = "studentId";
        String quizId = "quizId";
        Paper.BlankQuiz blankQuiz = new Paper.BlankQuiz(quizId, "answer", 10);
        Paper paper = new Paper("paperId", Collections.singletonList(blankQuiz));

        AnswerSheet answerSheet = AnswerSheet.assignAnswerSheet(paper, studentId);

        Answer answer = new Answer(quizId, "answer");

        assertThrows(IllegalStudentException.class,
                () -> answerSheet.submitAnswerSheet("errorStudentId", paper.getPaperId(), Collections.singletonList(answer))
        );
    }

    @Test
    void should_review_answer_sheet_successful() {
        String studentId = "studentId";
        String quizId = "quizId";
        Paper.BlankQuiz blankQuiz = new Paper.BlankQuiz(quizId, "answer", 10);
        Paper paper = new Paper("paperId", Collections.singletonList(blankQuiz));

        AnswerSheet answerSheet = AnswerSheet.assignAnswerSheet(paper, studentId);

        Answer answer = new Answer(quizId, "answer");

        answerSheet.submitAnswerSheet(studentId, paper.getPaperId(), Collections.singletonList(answer));

        answerSheet.reviewAnswerSheet();

        Answer answerAtAnswerSheet = answerSheet.getAnswers().get(0);
        assertSame(10, answerAtAnswerSheet.getScore());
    }
}