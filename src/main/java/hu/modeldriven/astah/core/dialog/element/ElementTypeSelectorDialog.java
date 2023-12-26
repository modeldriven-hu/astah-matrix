package hu.modeldriven.astah.core.dialog.element;

import com.change_vision.jude.api.inf.model.*;
import hu.modeldriven.astah.core.dialog.element.matcher.ClassMatcher;
import hu.modeldriven.astah.core.dialog.element.matcher.CombinedMatcher;
import hu.modeldriven.astah.core.dialog.element.matcher.ElementMatcher;
import hu.modeldriven.astah.core.dialog.element.matcher.StereotypeMatcher;

import java.util.HashMap;
import java.util.Map;

public class ElementTypeSelectorDialog {

    private final Map<String, ElementMatcher> elementMatcherHashMap = new HashMap<>();

    public ElementTypeSelectorDialog(){

        elementMatcherHashMap.put("Action", new ClassMatcher(IAction.class));
        elementMatcherHashMap.put("Class", new ClassMatcher(IClass.class));
        elementMatcherHashMap.put("Use Case", new ClassMatcher(IUseCase.class));
        elementMatcherHashMap.put("Requirement", new ClassMatcher(IRequirement.class));
        elementMatcherHashMap.put("Package", new ClassMatcher(IPackage.class));
        elementMatcherHashMap.put("Block", new CombinedMatcher(
                new ClassMatcher(IClass.class),
                new StereotypeMatcher("Block"))
        );
    }

}
