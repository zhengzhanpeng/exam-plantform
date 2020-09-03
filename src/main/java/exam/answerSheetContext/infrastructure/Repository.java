package exam.answerSheetContext.infrastructure;

import java.util.Set;

public interface Repository<T> {
    Set<T> findAll();
}
