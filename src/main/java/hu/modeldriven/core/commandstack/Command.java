package hu.modeldriven.core.commandstack;

public interface Command{

    void execute();

    void undo();

    void redo();

}
