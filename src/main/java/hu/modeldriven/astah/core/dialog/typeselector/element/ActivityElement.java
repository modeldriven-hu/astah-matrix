package hu.modeldriven.astah.core.dialog.typeselector.element;

import com.change_vision.jude.api.inf.model.IAction;
import hu.modeldriven.astah.core.matcher.ClassMatcher;

public class ActivityElement extends AbstractElement {

    public ActivityElement() {
        super("Activity", new ClassMatcher<>(IAction.class));
    }
}
