package hu.modeldriven.astah.core.dialog.typeselector.relationship;

import hu.modeldriven.astah.core.dialog.typeselector.Type;
import hu.modeldriven.astah.core.dialog.typeselector.TypeRepository;

import java.util.Arrays;
import java.util.List;

public class RelationshipRepository implements TypeRepository {

    @Override
    public List<Type> types() {
        return Arrays.asList(
                new AllocateRelationship(),
                new CopyRelationship(),
                new DependencyRelationship(),
                new DeriveRqtRelationship(),
                new RefineRelationship(),
                new SatisfyRelationship(),
                new TraceRelationship(),
                new VerifyRelationship()
        );

    }
}
