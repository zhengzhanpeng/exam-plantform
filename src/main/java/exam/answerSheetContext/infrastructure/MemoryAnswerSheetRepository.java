package exam.answerSheetContext.infrastructure;

import exam.answerSheetContext.domain.model.answerSheet.AnswerSheet;
import exam.answerSheetContext.domain.model.answerSheet.AnswerSheetRepository;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class MemoryAnswerSheetRepository implements AnswerSheetRepository {
    private Set<AnswerSheet> answerSheets = new HashSet<>();

    @Override
    public void save(AnswerSheet answerSheet) {
        answerSheets.add(answerSheet);
    }

    @Override
    public Optional<AnswerSheet> findById(String answerSheetId) {
        return answerSheets.stream()
                           .filter(answerSheet -> answerSheet.getAnswerSheetId().equals(answerSheetId))
                           .findAny();
    }
}
