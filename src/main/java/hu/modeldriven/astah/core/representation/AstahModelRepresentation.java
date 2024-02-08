package hu.modeldriven.astah.core.representation;

import com.change_vision.jude.api.inf.AstahAPI;
import com.change_vision.jude.api.inf.model.IPackage;
import com.change_vision.jude.api.inf.project.ProjectAccessor;

import java.util.Arrays;

public class AstahModelRepresentation implements ModelRepresentation {
    @Override
    public IPackage rootPackage() throws ModelAccessException {
        try {
            ProjectAccessor projectAccessor = AstahAPI.getAstahAPI().getProjectAccessor();
            return projectAccessor.getProject();
        } catch (Exception e) {
            throw new ModelAccessException(e);
        }
    }

    @Override
    public IPackage findPackageById(String id) throws ModelAccessException {
        try {
            ProjectAccessor projectAccessor = AstahAPI.getAstahAPI().getProjectAccessor();

            return Arrays.stream(
                            projectAccessor.findElements(element -> id.equals(element.getId())))
                    .filter(IPackage.class::isInstance)
                    .map(IPackage.class::cast)
                    .findFirst()
                    .orElse(null);

        } catch (Exception e) {
            throw new ModelAccessException(e);
        }
    }
}
