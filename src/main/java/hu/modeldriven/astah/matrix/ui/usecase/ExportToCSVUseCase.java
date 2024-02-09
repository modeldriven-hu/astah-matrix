package hu.modeldriven.astah.matrix.ui.usecase;

import hu.modeldriven.astah.matrix.ui.event.ExceptionOccurredEvent;
import hu.modeldriven.astah.matrix.ui.event.ExportCSVRequestedEvent;
import hu.modeldriven.astah.matrix.ui.event.TableDataCalculatedEvent;
import hu.modeldriven.astah.matrix.ui.table.MatrixData;
import hu.modeldriven.astah.matrix.ui.usecase.persistance.CSVQueryResultFile;
import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.eventbus.EventHandler;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Component;
import java.io.File;
import java.util.Arrays;
import java.util.List;

public class ExportToCSVUseCase implements EventHandler<Event> {

    private static final String CSV = ".csv";
    private final EventBus eventBus;
    private final Component parentComponent;

    private MatrixData matrixData;

    public ExportToCSVUseCase(EventBus eventBus, Component parentComponent) {
        this.eventBus = eventBus;
        this.parentComponent = parentComponent;
    }

    @Override
    public void handleEvent(Event event) {

        if (event instanceof TableDataCalculatedEvent) {
            this.matrixData = ((TableDataCalculatedEvent) event).tableData();
        }

        if (event instanceof ExportCSVRequestedEvent && matrixData != null) {
            saveFile();
        }

    }

    private void saveFile() {
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Specify a file to save");

            FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Files", "csv");
            fileChooser.addChoosableFileFilter(filter);
            fileChooser.setFileFilter(filter);

            if (fileChooser.showSaveDialog(parentComponent) != JFileChooser.APPROVE_OPTION) {
                return;
            }

            File file = fileChooser.getSelectedFile();

            if (file.exists()) {
                int result = JOptionPane.showConfirmDialog(null,
                        "The file already exists. Do you want to overwrite it?",
                        "File Already Exists",
                        JOptionPane.YES_NO_OPTION);

                if (result == JOptionPane.NO_OPTION) {
                    return;
                }
            }

            if (!file.getName().endsWith(CSV)) {
                file = new File(file.getParent() + File.separator + file.getName() + CSV);
            }

            CSVQueryResultFile csvFile = new CSVQueryResultFile(file);
            csvFile.write(matrixData);

        } catch (Exception e) {
            eventBus.publish(new ExceptionOccurredEvent(e));
        }
    }

    @Override
    public List<Class<? extends Event>> subscribedEvents() {
        return Arrays.asList(
                ExportCSVRequestedEvent.class,
                TableDataCalculatedEvent.class
        );
    }

}
