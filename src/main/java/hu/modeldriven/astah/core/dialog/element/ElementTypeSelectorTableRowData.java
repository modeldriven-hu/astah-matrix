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

public class ElementTypeSelectorTableRowData implements Supplier<List<ElementTypeSelectorTableRow>> {

    @Override
    public List<ElementTypeSelectorTableRow> get() {
        List<ElementTypeSelectorTableRow> rows = new ArrayList<>();

        rows.add(new ElementTypeSelectorTableRow("Activity", new ClassMatcher(IAction.class)));
        rows.add(new ElementTypeSelectorTableRow("Requirement", new ClassMatcher(IRequirement.class)));
        rows.add(new ElementTypeSelectorTableRow("Use Case", new ClassMatcher(IUseCase.class)));
        rows.add(new ElementTypeSelectorTableRow("Block", new CombinedMatcher(
                new ClassMatcher(IClass.class),
                new StereotypeMatcher("block"))
        ));

        return rows;
    }
}
