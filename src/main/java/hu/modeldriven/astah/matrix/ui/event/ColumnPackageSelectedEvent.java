package hu.modeldriven.astah.matrix.ui.event;

import com.change_vision.jude.api.inf.model.IPackage;
import hu.modeldriven.core.eventbus.Event;

public class ColumnPackageSelectedEvent implements Event {

    private final IPackage selectedPackage;

    public ColumnPackageSelectedEvent(IPackage selectedPackage) {
        this.selectedPackage = selectedPackage;
    }

    public IPackage selectedPackage() {
        return selectedPackage;
    }

}
