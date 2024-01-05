package hu.modeldriven.astah.matrix.ui.usecase;

import hu.modeldriven.astah.matrix.ui.event.MatrixElementNotSelectedEvent;
import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventHandler;

import javax.swing.JOptionPane;
import java.awt.Component;
import java.util.Collections;
import java.util.List;

public class DisplayErrorOnNoMatrixElementSelectionUseCase implements EventHandler<MatrixElementNotSelectedEvent> {

    private Component parentComponent;

    public DisplayErrorOnNoMatrixElementSelectionUseCase(Component parentComponent){
        this.parentComponent = parentComponent;
    }
    @Override
    public void handleEvent(MatrixElementNotSelectedEvent event) {
        JOptionPane.showMessageDialog(parentComponent, "Please select an element in the matrix!",
                "No selection", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public List<Class<? extends Event>> subscribedEvents() {
        return Collections.singletonList(MatrixElementNotSelectedEvent.class);
    }
}
