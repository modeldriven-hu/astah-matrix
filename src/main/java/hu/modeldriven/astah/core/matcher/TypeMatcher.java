package hu.modeldriven.astah.core.matcher;

import com.change_vision.jude.api.inf.model.IElement;

public interface TypeMatcher {

    boolean matches(IElement element);
}
