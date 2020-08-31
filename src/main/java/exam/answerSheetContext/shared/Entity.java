package exam.answerSheetContext.shared;

public interface Entity<T> {
    boolean sameIdentityAs(T other);
}
