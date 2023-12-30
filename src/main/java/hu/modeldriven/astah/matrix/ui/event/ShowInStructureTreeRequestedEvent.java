package hu.modeldriven.astah.matrix.ui.event;

import com.change_vision.jude.api.inf.model.INamedElement;
import hu.modeldriven.core.eventbus.Event;

public class ShowInStructureTreeRequestedEvent implements Event {

    private final INamedElement element;

    public ShowInStructureTreeRequestedEvent(INamedElement element){
        this.element = element;
    }

    public INamedElement element(){
        return element;
    }

}
