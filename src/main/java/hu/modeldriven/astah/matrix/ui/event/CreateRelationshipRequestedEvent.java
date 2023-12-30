package hu.modeldriven.astah.matrix.ui.event;

import com.change_vision.jude.api.inf.model.INamedElement;
import hu.modeldriven.astah.matrix.ui.table.RelationshipDirection;
import hu.modeldriven.core.eventbus.Event;

public class CreateRelationshipRequestedEvent implements Event {

    private final INamedElement sourceElement;
    private final INamedElement targetElement;
    private final RelationshipDirection direction;

    public CreateRelationshipRequestedEvent(INamedElement sourceElement,
                                            INamedElement targetElement,
                                            RelationshipDirection direction){
        this.sourceElement = sourceElement;
        this.targetElement = targetElement;
        this.direction = direction;
    }

    public INamedElement sourceElement() {
        return sourceElement;
    }

    public INamedElement targetElement() {
        return targetElement;
    }

    public RelationshipDirection direction() {
        return direction;
    }
}
