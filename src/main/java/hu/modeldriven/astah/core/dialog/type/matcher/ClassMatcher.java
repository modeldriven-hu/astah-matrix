package hu.modeldriven.astah.core.dialog.type.matcher;

import com.change_vision.jude.api.inf.model.IElement;

public class ClassMatcher implements TypeMatcher {

    private final Class requiredClass;

    public ClassMatcher(Class requiredClass) {
        this.requiredClass = requiredClass;
    }

    @Override
    public boolean matches(IElement element) {
        return requiredClass.isInstance(element);
    }

    @Override
    public String toString() {
        return super.toString() + " (" + requiredClass + ")";
    }
}
