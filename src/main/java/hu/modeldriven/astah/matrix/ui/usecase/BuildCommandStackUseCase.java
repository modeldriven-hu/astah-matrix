package hu.modeldriven.astah.matrix.ui.usecase;

import hu.modeldriven.astah.matrix.ui.event.HideColumnsRequestedEvent;
import hu.modeldriven.astah.matrix.ui.event.HideRowsRequestedEvent;
import hu.modeldriven.astah.matrix.ui.event.RedoRequestedEvent;
import hu.modeldriven.astah.matrix.ui.event.UndoRequestedEvent;
import hu.modeldriven.astah.matrix.ui.usecase.command.HideColumnsCommand;
import hu.modeldriven.astah.matrix.ui.usecase.command.HideRowsCommand;
import hu.modeldriven.core.commandstack.CommandStack;
import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventHandler;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

public class BuildCommandStackUseCase implements EventHandler<Event> {

    private final JTable table;

    private final CommandStack commandStack;

    public BuildCommandStackUseCase(JTable table) {
        this.table = table;
        this.commandStack = new CommandStack();
    }

    @Override
    public void handleEvent(Event event) {

        if (event instanceof HideRowsRequestedEvent) {
            HideRowsRequestedEvent e = (HideRowsRequestedEvent) event;
            commandStack.addCommand(new HideRowsCommand(table, e.selectedRows()));
        }

        if (event instanceof HideColumnsRequestedEvent) {
            HideColumnsRequestedEvent e = (HideColumnsRequestedEvent) event;
            commandStack.addCommand(new HideColumnsCommand(table, e.selectedColumns()));
        }

        if (event instanceof UndoRequestedEvent) {
            commandStack.undo();
        }

        if (event instanceof RedoRequestedEvent) {
            commandStack.redo();
        }
    }

    @Override
    public List<Class<? extends Event>> subscribedEvents() {
        return Arrays.asList(HideRowsRequestedEvent.class,
                HideColumnsRequestedEvent.class,
                UndoRequestedEvent.class,
                RedoRequestedEvent.class);
    }


}
