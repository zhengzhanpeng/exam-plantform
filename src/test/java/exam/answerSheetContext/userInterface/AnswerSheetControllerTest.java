package exam.answerSheetContext.userInterface;

import exam.answerSheetContext.application.AssignAnswerSheetCommand;
import exam.answerSheetContext.application.SubmitAnswerSheetCommand;
import exam.answerSheetContext.domain.model.answerSheet.Answer;
import exam.answerSheetContext.domain.model.answerSheet.AnswerSheet;
import exam.answerSheetContext.domain.model.answerSheet.AnswerSheetRepository;
import exam.answerSheetContext.domain.model.paper.Paper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class AnswerSheetControllerTest extends IntegrationTestBase {

    private final AnswerSheetRepository memoryAnswerSheetRepository;

    @Test
    void should_assign_answer_sheet_successful() {
        AssignAnswerSheetCommand.Paper.BlankQuiz blankQuiz = new AssignAnswerSheetCommand.Paper.BlankQuiz("quizeId", "answer", 10);

        AssignAnswerSheetCommand.Paper paper = new AssignAnswerSheetCommand.Paper("paperId", Collections.singletonList(blankQuiz));
        AssignAnswerSheetCommand assignAnswerSheetCommand = AssignAnswerSheetCommand.builder()
                                                                                    .studentId("studentId")
                                                                                    .paper(paper)
                                                                                    .build();

        given().body(assignAnswerSheetCommand)
               .post("/api/answer-sheet")
               .then()
               .statusCode(200)
               .body("answerSheetId", is(not(nullValue())))
               .body("answers[0].blankQuizId", is(not(nullValue())))
               .body("answers[0].answer", is(nullValue()))
               .body("answers[0].score", is(0));

        AnswerSheet answerSheet = memoryAnswerSheetRepository.findAll().iterator().next();
        assertEquals("studentId", answerSheet.getStudentId());
        assertEquals(1, answerSheet.getAnswers().size());

        Paper paperAtDatabase = answerSheet.getPaper();
        assertNotNull(paperAtDatabase.getPaperId());

        Paper.BlankQuiz blankQuizAtDatabase = paperAtDatabase.getBlankQuizzes().get(0);
        assertEquals("answer", blankQuizAtDatabase.getAnswer());
        assertEquals(10, blankQuizAtDatabase.getScore());
        assertNotNull(blankQuizAtDatabase.getQuizId());
    }

    @Test
    void should_submit_answer_sheet_successful() {
        Paper.BlankQuiz blankQuiz = new Paper.BlankQuiz("blankQuizId", "answer", 10);
        Paper paper = new Paper("paperId", Collections.singletonList(blankQuiz));
        AnswerSheet answerSheet = AnswerSheet.assignAnswerSheet(paper, "studentId");
        memoryAnswerSheetRepository.save(answerSheet);

        SubmitAnswerSheetCommand command = new SubmitAnswerSheetCommand();
        AnswerDTO answerDTO = new AnswerDTO("blankQuizId", "answer", 10);
        command.setAnswers(Collections.singletonList(answerDTO));
        command.setAnswerSheetId(answerSheet.getAnswerSheetId().getId());
        command.setPaperId("paperId");

        given().body(command)
               .patch("/api/answer-sheet")
               .then()
               .statusCode(200);

        AnswerSheet answerSheetAtDatabase = memoryAnswerSheetRepository.findAll().iterator().next();
        Answer answer = answerSheetAtDatabase.getAnswers().get(0);
        assertEquals("answer", answer.getAnswer());
        assertEquals(10, answer.getScore());
    }
}