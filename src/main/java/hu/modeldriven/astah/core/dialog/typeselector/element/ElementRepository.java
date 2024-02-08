package hu.modeldriven.astah.core.dialog.typeselector.element;

import hu.modeldriven.astah.core.dialog.typeselector.Type;
import hu.modeldriven.astah.core.dialog.typeselector.TypeRepository;

import java.util.Arrays;
import java.util.List;

public class ElementRepository implements TypeRepository {
    @Override
    public List<Type> types() {

        return Arrays.asList(
                new ActivityElement(),
                new RequirementElement(),
                new UseCaseElement(),
                new BlockElement()
        );
    }
}
