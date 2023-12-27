package hu.modeldriven.astah.core.dialog.type;

import com.change_vision.jude.api.inf.model.*;
import hu.modeldriven.astah.core.dialog.type.matcher.ClassMatcher;
import hu.modeldriven.astah.core.dialog.type.matcher.CombinedMatcher;
import hu.modeldriven.astah.core.dialog.type.matcher.StereotypeMatcher;

import java.util.ArrayList;
import java.util.List;

public class DependencyTypeSelectorData implements TypeSelectorData{
    @Override
    public List<TypeSelector> asRows() {
        List<TypeSelector> rows = new ArrayList<>();

        rows.add(new TypeSelector("Dependency", new ClassMatcher(IDependency.class)));

        return rows;
    }
}
