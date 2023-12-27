package hu.modeldriven.astah.matrix.ui.usecase;

import com.change_vision.jude.api.inf.model.IPackage;
import hu.modeldriven.astah.core.dialog.pkg.PackageSelectorDialog;
import hu.modeldriven.astah.core.model.Model;
import hu.modeldriven.astah.core.model.ModelAccessException;
import hu.modeldriven.astah.matrix.ui.event.ModelAccessExceptionOccurredEvent;
import hu.modeldriven.astah.matrix.ui.event.RowPackageSelectedEvent;
import hu.modeldriven.astah.matrix.ui.event.RowPackageSelectionRequestedEvent;
import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.eventbus.EventHandler;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class DisplayRowPackageSelectorUseCase implements
        Consumer<IPackage>,
        EventHandler<RowPackageSelectionRequestedEvent> {

    private final EventBus eventBus;
    private final Model model;

    public DisplayRowPackageSelectorUseCase(EventBus eventBus, Model model) {
        this.eventBus = eventBus;
        this.model = model;
    }

    @Override
    public void handleEvent(RowPackageSelectionRequestedEvent event) {

        try {
            PackageSelectorDialog dialog = new PackageSelectorDialog(
                    model.rootPackage(),
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
