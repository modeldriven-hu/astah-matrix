package hu.modeldriven.astah.core.dialog.element.matcher;

import com.change_vision.jude.api.inf.model.IElement;

public class ClassMatcher implements ElementMatcher{

    private final Class requiredClass;

    public ClassMatcher(Class requiredClass){
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
