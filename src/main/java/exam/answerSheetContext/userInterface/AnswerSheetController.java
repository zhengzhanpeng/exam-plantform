package exam.answerSheetContext.userInterface;

import exam.answerSheetContext.application.AnswerSheetService;
import exam.answerSheetContext.application.AssignAnswerSheetCommand;
import exam.answerSheetContext.application.SubmitAnswerSheetCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/answer-sheet")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AnswerSheetController {

    private final AnswerSheetService answerSheetService;

    @PostMapping
    public AnswerSheetDTO assignAnswerSheet(@RequestBody AssignAnswerSheetCommand assignAnswerSheetCommand) {
        return answerSheetService.assignAnswerSheet(assignAnswerSheetCommand);
    }

    @PatchMapping
    public void submitAnswerSheet(@RequestBody SubmitAnswerSheetCommand submitAnswerSheetCommand) {
        answerSheetService.submitAnswerSheet(submitAnswerSheetCommand);
    }
}
