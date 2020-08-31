package exam.answerSheetContext.application;

import exam.answerSheetContext.userInterface.AnswerDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubmitAnswerSheetCommand {
    private String answerSheetId;
    private String paperId;
    private List<AnswerDTO> answers;
}
