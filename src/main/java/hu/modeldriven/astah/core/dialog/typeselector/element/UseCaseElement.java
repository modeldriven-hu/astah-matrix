package hu.modeldriven.astah.core.dialog.typeselector.element;

import com.change_vision.jude.api.inf.model.IUseCase;
import hu.modeldriven.astah.core.matcher.ClassMatcher;

public class UseCaseElement extends AbstractElement {
    public UseCaseElement() {
        super("Use Case", new ClassMatcher<>(IUseCase.class));
    }
}
