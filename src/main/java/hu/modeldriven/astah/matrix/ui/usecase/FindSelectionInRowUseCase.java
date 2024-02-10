package hu.modeldriven.astah.matrix.ui.usecase;

import com.change_vision.jude.api.inf.model.IElement;
import com.change_vision.jude.api.inf.model.INamedElement;
import hu.modeldriven.astah.core.representation.ModelRepresentation;
import hu.modeldriven.astah.matrix.ui.event.FindSelectionInRowRequested;
import hu.modeldriven.astah.matrix.ui.event.MatrixDataDisplayedEvent;
import hu.modeldriven.astah.matrix.ui.event.ResetRequestedEvent;
import hu.modeldriven.astah.matrix.ui.table.MatrixTableModel;
import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventHandler;

import javax.swing.*;
import java.awt.Rectangle;
import java.util.Arrays;
import java.util.List;

public class FindSelectionInRowUseCase implements EventHandler<Event> {

    private final JTable table;

    private final ModelRepresentation modelRepresentation;

    private boolean dataAvailable;

    public FindSelectionInRowUseCase(JTable table, ModelRepresentation modelRepresentation) {
        this.table = table;
        this.modelRepresentation = modelRepresentation;
    }

    @Override
    public void handleEvent(Event event) {
        if (event instanceof ResetRequestedEvent) {
            this.dataAvailable = false;
        }

        if (event instanceof MatrixDataDisplayedEvent) {
            this.dataAvailable = true;
        }

        if (event instanceof FindSelectionInRowRequested &&
                this.dataAvailable &&
                this.modelRepresentation.isElementSelected()) {

            IElement selectedElement = this.modelRepresentation.getSingleSelection();

            if (selectedElement instanceof INamedElement) {
                selectRow((INamedElement) selectedElement);
            }
        }
    }

    private void selectRow(INamedElement selectedElement) {

        if (table.getModel() instanceof MatrixTableModel) {
            MatrixTableModel model = (MatrixTableModel) table.getModel();

            int selectedRowIndex = model.findRowByElement(selectedElement);

            if (selectedRowIndex > -1) {
                table.getSelectionModel().setSelectionInterval(selectedRowIndex, selectedRowIndex);
                table.getColumnModel().getSelectionModel().setSelectionInterval(0, 0);
                Rectangle cellRect = table.getCellRect(selectedRowIndex, 0, true);
                table.scrollRectToVisible(cellRect);
            }
        }

    }

    @Override
    public List<Class<? extends Event>> subscribedEvents() {
        return Arrays.asList(FindSelectionInRowRequested.class, MatrixDataDisplayedEvent.class, ResetRequestedEvent.class);
    }
}
