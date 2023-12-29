package hu.modeldriven.astah.core.model;

import com.change_vision.jude.api.inf.AstahAPI;
import com.change_vision.jude.api.inf.model.IPackage;
import com.change_vision.jude.api.inf.project.ProjectAccessor;

public class AstahModel implements Model {
    @Override
    public IPackage rootPackage() throws ModelAccessException {
        try {
            ProjectAccessor projectAccessor = AstahAPI.getAstahAPI().getProjectAccessor();
            return projectAccessor.getProject();
        } catch (Exception e) {
            throw new ModelAccessException(e);
        }
    }
}
