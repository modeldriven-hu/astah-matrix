package hu.modeldriven.astah.matrix.ui.usecase;

import hu.modeldriven.astah.core.representation.ModelRepresentation;
import hu.modeldriven.astah.matrix.ui.event.ExceptionOccurredEvent;
import hu.modeldriven.astah.matrix.ui.event.OpenFileRequestedEvent;
import hu.modeldriven.astah.matrix.ui.event.QueryModelLoadedEvent;
import hu.modeldriven.astah.matrix.ui.usecase.persistance.YAMLQueryFile;
import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.eventbus.EventHandler;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Component;
import java.io.File;
import java.util.Collections;
import java.util.List;

public class OpenFileUseCase implements EventHandler<OpenFileRequestedEvent> {

    private final EventBus eventBus;
    private final Component parentComponent;

    private final ModelRepresentation modelRepresentation;

    public OpenFileUseCase(EventBus eventBus, Component parentComponent, ModelRepresentation modelRepresentation) {
        this.eventBus = eventBus;
        this.parentComponent = parentComponent;
        this.modelRepresentation = modelRepresentation;
    }

    @Override
    public void handleEvent(OpenFileRequestedEvent event) {
        final JFileChooser fileChooser = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter("YAML files", "yaml", "yml");
        fileChooser.addChoosableFileFilter(filter);
        fileChooser.setFileFilter(filter);

        if (fileChooser.showOpenDialog(parentComponent) == JFileChooser.APPROVE_OPTION) {
            try {
                File file = fileChooser.getSelectedFile();
                YAMLQueryFile queryFile = new YAMLQueryFile(file);
                eventBus.publish(new QueryModelLoadedEvent(queryFile.read(modelRepresentation)));
            } catch (Exception e) {
                eventBus.publish(new ExceptionOccurredEvent(e));
            }
        }
    }

    @Override
    public List<Class<? extends Event>> subscribedEvents() {
        return Collections.singletonList(OpenFileRequestedEvent.class);
    }
}
