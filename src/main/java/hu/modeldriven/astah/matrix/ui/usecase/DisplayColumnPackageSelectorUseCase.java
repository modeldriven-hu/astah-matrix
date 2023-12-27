package hu.modeldriven.astah.matrix.ui.usecase;

import com.change_vision.jude.api.inf.model.IPackage;
import hu.modeldriven.astah.core.dialog.pkg.PackageSelectorDialog;
import hu.modeldriven.astah.core.model.Model;
import hu.modeldriven.astah.core.model.ModelAccessException;
import hu.modeldriven.astah.matrix.ui.event.*;
import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.eventbus.EventHandler;

import java.awt.Component;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class DisplayColumnPackageSelectorUseCase implements
        Consumer<IPackage>,
        EventHandler<ColumnPackageSelectionRequestedEvent> {

    private final Component parentComponent;

    private final EventBus eventBus;
    private final Model model;

    public DisplayColumnPackageSelectorUseCase(Component parentComponent, EventBus eventBus, Model model) {
        this.parentComponent = parentComponent;
        this.eventBus = eventBus;
        this.model = model;
    }

    @Override
    public void handleEvent(ColumnPackageSelectionRequestedEvent event) {

        try {
            PackageSelectorDialog dialog = new PackageSelectorDialog(
                    parentComponent,
                    model.rootPackage(),
                    this);
            dialog.show();
        } catch (ModelAccessException e) {
            eventBus.publish(new ModelAccessExceptionOccurredEvent(e));
        }
    }

    @Override
    public List<Class<? extends Event>> subscribedEvents() {
        return Collections.singletonList(ColumnPackageSelectionRequestedEvent.class);
    }

    @Override
    public void accept(IPackage selectedPackage) {
        eventBus.publish(new ColumnPackageSelectedEvent(selectedPackage)
        );
    }
}
