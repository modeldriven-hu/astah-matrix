package hu.modeldriven.astah.matrix.ui.event;

import com.change_vision.jude.api.inf.model.IPackage;
import hu.modeldriven.core.eventbus.Event;

public class RowPackageSelectedEvent implements Event {

    private final IPackage selectedPackage;

    public RowPackageSelectedEvent(IPackage selectedPackage){
        this.selectedPackage = selectedPackage;
    }

}
