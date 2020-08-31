package exam.answerSheetContext.shared;

public interface ValueObject<T> {
    boolean sameValueAs(T other);
}
