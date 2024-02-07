package hu.modeldriven.astah.core.dialog.type;

import com.change_vision.jude.api.inf.model.IAction;
import com.change_vision.jude.api.inf.model.IRequirement;
import com.change_vision.jude.api.inf.model.IUseCase;
import hu.modeldriven.astah.core.dialog.type.matcher.ClassMatcher;
import hu.modeldriven.astah.core.dialog.type.matcher.StereotypeMatcher;

import java.util.ArrayList;
import java.util.List;

public class ElementTypeSelectorData implements TypeSelectorData {
    @Override
    public List<TypeSelector> asRows() {
        List<TypeSelector> rows = new ArrayList<>();

        rows.add(new DefaultTypeSelector("Activity", new ClassMatcher<>(IAction.class)));
        rows.add(new DefaultTypeSelector("Requirement", new ClassMatcher<>(IRequirement.class)));
        rows.add(new DefaultTypeSelector("Use Case", new ClassMatcher<>(IUseCase.class)));
        rows.add(new DefaultTypeSelector("Block", new StereotypeMatcher("Block")));

        return rows;
    }
}
