package exam.answerSheetContext.domain.model.answerSheet;

import exam.answerSheetContext.infrastructure.Repository;

import java.util.Optional;
import java.util.Set;

public interface AnswerSheetRepository extends Repository {
    void save(AnswerSheet answerSheet);

    Set<AnswerSheet> findAll();

    Optional<AnswerSheet> findById(String answerSheetId);
}
