package hexlet.code;

public enum ChangeStatus {
    SAME("same"),
    ADD("add"),
    DELETE("delete"),
    CHANGE("change");

    final String operation;

    ChangeStatus(String operation) {
        this.operation = operation;
    }
}
