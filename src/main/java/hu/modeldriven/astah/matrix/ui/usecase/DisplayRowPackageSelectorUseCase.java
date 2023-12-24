package hu.modeldriven.astah.matrix.ui.usecase;

import hu.modeldriven.astah.dialog.PackageSelectorDialog;
import hu.modeldriven.astah.matrix.ui.event.RowPackageSelectedEvent;
import hu.modeldriven.astah.matrix.ui.event.RowPackageSelectionRequestedEvent;
import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.eventbus.EventHandler;

import java.util.Arrays;
import java.util.List;

public class DisplayRowPackageSelectorUseCase implements EventHandler<RowPackageSelectionRequestedEvent> {

    private final EventBus eventBus;

    public DisplayRowPackageSelectorUseCase(EventBus eventBus){
        this.eventBus = eventBus;
    }

    @Override
    public void handleEvent(RowPackageSelectionRequestedEvent e) {
        PackageSelectorDialog dialog = new PackageSelectorDialog();
        PackageSelectorDialog.PackageSelectionResult result = dialog.show();

        if (result == PackageSelectorDialog.PackageSelectionResult.PACKAGE_SELECTED) {
            eventBus.publish(new RowPackageSelectedEvent(dialog.selectedPackage()));
        }
    }

    @Override
    public List<Class<? extends Event>> subscribedEvents() {
        return Arrays.asList(RowPackageSelectionRequestedEvent.class);
    }
}
