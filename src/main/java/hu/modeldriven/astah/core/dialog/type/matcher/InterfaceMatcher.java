package hu.modeldriven.astah.core.dialog.type.matcher;

import com.change_vision.jude.api.inf.model.IElement;

public class InterfaceMatcher implements TypeMatcher {

    private final Class<?> interfaceClass;

    public InterfaceMatcher(Class<?> interfaceClass) {
        this.interfaceClass = interfaceClass;
    }

    @Override
    public boolean matches(IElement element) {
        return interfaceClass.isAssignableFrom(element.getClass());
    }

}
