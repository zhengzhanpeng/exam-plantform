package exam.answerSheetContext.domain.model.answerSheet;

import java.util.Optional;

public interface AnswerSheetRepository {
    void save(AnswerSheet answerSheet);

    Optional<AnswerSheet> findById(String answerSheetId);
}
