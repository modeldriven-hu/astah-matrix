package hu.modeldriven.astah.matrix.ui.usecase;

import com.change_vision.jude.api.inf.model.IPackage;
import hu.modeldriven.astah.core.dialog.pkg.PackageSelectorDialog;
import hu.modeldriven.astah.core.representation.ModelAccessException;
import hu.modeldriven.astah.core.representation.ModelRepresentation;
import hu.modeldriven.astah.matrix.ui.event.ModelAccessExceptionOccurredEvent;
import hu.modeldriven.astah.matrix.ui.event.RowPackageSelectedEvent;
import hu.modeldriven.astah.matrix.ui.event.RowPackageSelectionRequestedEvent;
import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.eventbus.EventHandler;

import java.awt.Component;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class DisplayRowPackageSelectorUseCase implements
        Consumer<IPackage>,
        EventHandler<RowPackageSelectionRequestedEvent> {

    private final Component parentComponent;

    private final EventBus eventBus;
    private final ModelRepresentation modelRepresentation;

    public DisplayRowPackageSelectorUseCase(Component parentComponent, EventBus eventBus, ModelRepresentation modelRepresentation) {
        this.parentComponent = parentComponent;
        this.eventBus = eventBus;
        this.modelRepresentation = modelRepresentation;
    }

    @Override
    public void handleEvent(RowPackageSelectionRequestedEvent event) {

        try {
            PackageSelectorDialog dialog = new PackageSelectorDialog(
                    parentComponent,
                    modelRepresentation.rootPackage(),
                    this);
            dialog.show();
        } catch (ModelAccessException e) {
            eventBus.publish(new ModelAccessExceptionOccurredEvent(e));
        }
    }

    @Override
    public List<Class<? extends Event>> subscribedEvents() {
        return Collections.singletonList(RowPackageSelectionRequestedEvent.class);
    }

    @Override
    public void accept(IPackage selectedPackage) {
        eventBus.publish(new RowPackageSelectedEvent(selectedPackage));
    }
}
