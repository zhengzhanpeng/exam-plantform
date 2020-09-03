package exam.answerSheetContext.userInterface;

import exam.answerSheetContext.domain.model.answerSheet.AnswerSheet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnswerSheetDTO {
    private String answerSheetId;
    private List<AnswerDTO> answers;

    public static AnswerSheetDTO toAnswerSheetDTO(AnswerSheet answerSheet) {
        AnswerSheetDTO answerSheetDTO = new AnswerSheetDTO();
        answerSheetDTO.setAnswerSheetId(answerSheet.getAnswerSheetId().getId());

        List<AnswerDTO> answers = answerSheet.getAnswers()
                                             .stream()
                                             .map(answer -> AnswerDTO.builder()
                                                                     .answer(answer.getAnswer())
                                                                     .blankQuizId(answer.getBlankQuizId())
                                                                     .score(answer.getScore())
                                                                     .build())
                                             .collect(toList());
        answerSheetDTO.setAnswers(answers);
        return answerSheetDTO;
    }
}
