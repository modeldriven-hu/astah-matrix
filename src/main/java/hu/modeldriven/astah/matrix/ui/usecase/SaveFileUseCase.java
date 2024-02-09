package hu.modeldriven.astah.matrix.ui.usecase;

import hu.modeldriven.astah.matrix.ui.event.ExceptionOccurredEvent;
import hu.modeldriven.astah.matrix.ui.event.QueryModelChangedEvent;
import hu.modeldriven.astah.matrix.ui.event.SaveFileRequestedEvent;
import hu.modeldriven.astah.matrix.ui.usecase.model.QueryModel;
import hu.modeldriven.astah.matrix.ui.usecase.persistance.YAMLQueryFile;
import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.eventbus.EventHandler;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Component;
import java.io.File;
import java.util.Arrays;
import java.util.List;

public class SaveFileUseCase implements EventHandler<Event> {

    public static final String YAML = ".yaml";
    private final EventBus eventBus;
    private final Component parentComponent;

    private QueryModel queryModel;


    public SaveFileUseCase(EventBus eventBus, Component parentComponent) {
        this.eventBus = eventBus;
        this.parentComponent = parentComponent;
    }

    @Override
    public void handleEvent(Event event) {

        if (event instanceof QueryModelChangedEvent) {
            this.queryModel = ((QueryModelChangedEvent) event).queryModel();
        }

        if (event instanceof SaveFileRequestedEvent && queryModel != null && queryModel.isAllDataProvided()) {
                saveFile();
        }

    }

    private void saveFile() {
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Specify a file to save");

            FileNameExtensionFilter filter = new FileNameExtensionFilter("YAML files", "yaml");
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

            if (!file.getName().endsWith(YAML)) {
                file = new File(file.getParent() + File.separator + file.getName() + YAML);
            }

            YAMLQueryFile queryFile = new YAMLQueryFile(file);
            queryFile.write(queryModel);

        } catch (Exception e) {
            eventBus.publish(new ExceptionOccurredEvent(e));
        }
    }

    @Override
    public List<Class<? extends Event>> subscribedEvents() {
        return Arrays.asList(
                SaveFileRequestedEvent.class,
                QueryModelChangedEvent.class
        );
    }

}

