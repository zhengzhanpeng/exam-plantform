package exam.answerSheetContext.domain.model.answerSheet;

import java.util.Optional;
import java.util.Set;

public interface AnswerSheetRepository {
    void save(AnswerSheet answerSheet);

    Set<AnswerSheet> findAll();

    Optional<AnswerSheet> findById(String answerSheetId);
}
