package hu.modeldriven.astah.core.dialog.element;

import com.change_vision.jude.api.inf.model.IAction;
import com.change_vision.jude.api.inf.model.IClass;
import com.change_vision.jude.api.inf.model.IRequirement;
import com.change_vision.jude.api.inf.model.IUseCase;
import hu.modeldriven.astah.core.dialog.element.matcher.ClassMatcher;
import hu.modeldriven.astah.core.dialog.element.matcher.CombinedMatcher;
import hu.modeldriven.astah.core.dialog.element.matcher.StereotypeMatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ElementTypeSelectorTableRowData implements Supplier<List<ElementTypeSelector>> {

    @Override
    public List<ElementTypeSelector> get() {
        List<ElementTypeSelector> rows = new ArrayList<>();

        rows.add(new ElementTypeSelector("Activity", new ClassMatcher(IAction.class)));
        rows.add(new ElementTypeSelector("Requirement", new ClassMatcher(IRequirement.class)));
        rows.add(new ElementTypeSelector("Use Case", new ClassMatcher(IUseCase.class)));
        rows.add(new ElementTypeSelector("Block", new CombinedMatcher(
                new ClassMatcher(IClass.class),
                new StereotypeMatcher("block"))
        ));

        return rows;
    }
}
