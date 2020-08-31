package exam.answerSheetContext.domain.model.answerSheet;

import exam.answerSheetContext.shared.ValueObject;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
import java.util.Objects;
import java.util.Random;

@Getter
@AllArgsConstructor
public class AnswerSheetId implements ValueObject<AnswerSheetId> {
    private String id;

    public static AnswerSheetId nextId() {
        String id = new Date().toString() + new Random().nextLong();
        return new AnswerSheetId(id);
    }

    @Override
    public boolean sameValueAs(AnswerSheetId other) {
        return equals(other);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnswerSheetId paperId = (AnswerSheetId) o;
        return Objects.equals(id, paperId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id;
    }
}
