package hu.modeldriven.astah.matrix.ui.usecase;

import com.change_vision.jude.api.inf.model.IPackage;
import hu.modeldriven.astah.core.dialog.pkg.PackageSelectorDialog;
import hu.modeldriven.astah.matrix.ui.event.RowPackageSelectedEvent;
import hu.modeldriven.astah.matrix.ui.event.RowPackageSelectionRequestedEvent;
import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.eventbus.EventHandler;

import java.util.Collections;
import java.util.List;

public class DisplayRowPackageSelectorUseCase implements EventHandler<RowPackageSelectionRequestedEvent> {

    private final EventBus eventBus;

    public DisplayRowPackageSelectorUseCase(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void handleEvent(RowPackageSelectionRequestedEvent e) {
        PackageSelectorDialog dialog = new PackageSelectorDialog(findRootPackage(), selectedPackage -> {
            eventBus.publish(new RowPackageSelectedEvent(selectedPackage));
        });
        dialog.show();
    }

    private IPackage findRootPackage() {
        // FIXME get from astah
        return null;
    }

    @Override
    public List<Class<? extends Event>> subscribedEvents() {
        return Collections.singletonList(RowPackageSelectionRequestedEvent.class);
    }
}
