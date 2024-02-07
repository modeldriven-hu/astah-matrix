package hu.modeldriven.astah.matrix.ui.usecase;

import hu.modeldriven.astah.matrix.ui.event.TableStructureChangedEvent;
import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventHandler;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.util.Arrays;
import java.util.List;

public class ResizeTableColumnsUseCase implements EventHandler<Event> {

    private final JTable table;
    private final BorderLayout panelLayout;
    private final JPanel contentPanel;

    public ResizeTableColumnsUseCase(JTable table, BorderLayout panelLayout, JPanel contentPanel){
        this.table = table;
        this.panelLayout = panelLayout;
        this.contentPanel = contentPanel;
    }

    @Override
    public void handleEvent(Event event) {
        for (Class<? extends Event> interestedEventClass : subscribedEvents()){
            if (interestedEventClass.isInstance(event)){
                adjustTableColumnsWidth();
            }
        }
    }

    private void adjustTableColumnsWidth() {
        TableColumnModel columnModel = table.getColumnModel();

        int sumWidth = 0;

        // Iterate through each column
        for (int column = 0; column < table.getColumnCount(); column++) {

            TableColumn tableColumn = columnModel.getColumn(column);

            // Get the header width
            TableCellRenderer defaultHeaderRenderer = table.getTableHeader().getDefaultRenderer();
            Component headerComp = defaultHeaderRenderer.getTableCellRendererComponent(table, tableColumn.getHeaderValue(), false, false, 0, column);

            int calculatedWidth = headerComp.getPreferredSize().width;

            // Get the content width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer cellRenderer = table.getCellRenderer(row, column);
                Object value = table.getValueAt(row, column);
                Component comp = cellRenderer.getTableCellRendererComponent(table, value, false, false, row, column);
                calculatedWidth = Math.max(comp.getPreferredSize().width, calculatedWidth);
            }

            sumWidth += calculatedWidth;

            // Handle last column
            if (column == table.getColumnCount() - 1){

                // FIXME the calculation is sadly not still perfect, needs to be investigated
                // In general the BorderLayout does not change the preferred size of the component
                // Maybe getting rid of BorderLayout in favor of miglayout solves this issue

                int panelWidth = panelLayout.preferredLayoutSize(contentPanel).width - UIManager.getInt("ScrollBar.width");

                int lastColumnSize = calculatedWidth;

                // If width is smaller than of the scroll pane, the match it
                if (sumWidth <= panelWidth){
                    // we already added the column width so we need to remove and then calculate
                    int widthBeforeLastColumn = sumWidth - calculatedWidth;
                    lastColumnSize = panelWidth - widthBeforeLastColumn;
                }

                tableColumn.setPreferredWidth(lastColumnSize);
            } else {
                // Set the preferred width for the column
                tableColumn.setPreferredWidth(calculatedWidth);
            }
        }

        table.invalidate();
    }

    @Override
    public List<Class<? extends Event>> subscribedEvents() {
        return Arrays.asList(TableStructureChangedEvent.class);
    }
}
