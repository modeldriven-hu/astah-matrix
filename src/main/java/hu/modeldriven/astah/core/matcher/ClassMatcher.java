package hu.modeldriven.astah.core.matcher;

import com.change_vision.jude.api.inf.model.IElement;

public class ClassMatcher<T> implements TypeMatcher {

    private final Class<T> requiredClass;

    public ClassMatcher(Class<T> requiredClass) {
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
