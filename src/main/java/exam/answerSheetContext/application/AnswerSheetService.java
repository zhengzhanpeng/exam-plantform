package exam.answerSheetContext.application;

import exam.answerSheetContext.domain.model.answerSheet.Answer;
import exam.answerSheetContext.domain.model.answerSheet.AnswerSheet;
import exam.answerSheetContext.domain.model.answerSheet.AnswerSheetRepository;
import exam.answerSheetContext.domain.model.paper.Paper;
import exam.answerSheetContext.userInterface.AnswerSheetDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AnswerSheetService {

    private final AnswerSheetRepository memoryAnswerSheetRepository;

    public AnswerSheetDTO assignAnswerSheet(AssignAnswerSheetCommand assignAnswerSheetCommand) {
        AnswerSheet answerSheet = AnswerSheet.assignAnswerSheet(paperFrom(assignAnswerSheetCommand), assignAnswerSheetCommand.getStudentId());

        memoryAnswerSheetRepository.save(answerSheet);

        return AnswerSheetDTO.toAnswerSheetDTO(answerSheet);
    }

    public void submitAnswerSheet(SubmitAnswerSheetCommand submitAnswerSheetCommand) {
        AnswerSheet answerSheet = memoryAnswerSheetRepository.findById(submitAnswerSheetCommand.getAnswerSheetId())
                                                             .orElseThrow(AnswerSheetNotFoundException::new);
        String studentId = getStudentIdByUserToken();
        answerSheet.submitAnswerSheet(studentId, submitAnswerSheetCommand.getPaperId(), answerFrom(submitAnswerSheetCommand));
        answerSheet.reviewAnswerSheet();
    }

    private String getStudentIdByUserToken() {
        return "studentId";
    }

    private Paper paperFrom(AssignAnswerSheetCommand assignAnswerSheetCommand) {
        AssignAnswerSheetCommand.Paper paper = assignAnswerSheetCommand.getPaper();
        List<Paper.BlankQuiz> blankQuizs = paper.getBlankQuizzes()
                                             .stream()
                                             .map(blankQuiz -> new Paper.BlankQuiz(blankQuiz.getQuizId(), blankQuiz.getAnswer(), blankQuiz.getScore()))
                                             .collect(Collectors.toList());
        return new Paper(paper.getPaperId(), blankQuizs);
    }

    private List<Answer> answerFrom(SubmitAnswerSheetCommand submitAnswerSheetCommand) {
        return submitAnswerSheetCommand.getAnswers()
                                       .stream()
                                       .map(answerDTO -> new Answer(answerDTO.getBlankQuizId(), answerDTO.getAnswer()))
                                       .collect(Collectors.toList());
    }
}
