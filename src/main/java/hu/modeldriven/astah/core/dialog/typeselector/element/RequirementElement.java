package hu.modeldriven.astah.core.dialog.typeselector.element;

import com.change_vision.jude.api.inf.model.IRequirement;
import hu.modeldriven.astah.core.matcher.ClassMatcher;

public class RequirementElement extends AbstractElement {

    public RequirementElement() {
        super("Requirement", new ClassMatcher<>(IRequirement.class));
    }
}
