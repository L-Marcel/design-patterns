package app.errors;

public class InvalidEventTime extends RuntimeException {
    public InvalidEventTime() {
        super("Tempo inválido para execução do evento!");
    };
};
