package hu.modeldriven.astah.core.representation;

import com.change_vision.jude.api.inf.AstahAPI;
import com.change_vision.jude.api.inf.model.IElement;
import com.change_vision.jude.api.inf.model.IPackage;
import com.change_vision.jude.api.inf.project.ProjectAccessor;
import com.change_vision.jude.api.inf.view.IDiagramViewManager;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public boolean isElementSelected() throws ModelAccessException {
        return getSelection() != null;
    }

    @Override
    public IElement getSingleSelection() throws ModelAccessException {
        return getSelection().stream().findFirst().orElse(null);
    }

    @Override
    public List<IElement> getSelection() throws ModelAccessException {
        try {
            ProjectAccessor projectAccessor = AstahAPI.getAstahAPI().getProjectAccessor();
            IDiagramViewManager viewManager = projectAccessor.getViewManager().getDiagramViewManager();

            if (viewManager.getCurrentDiagram() != null &&
                    viewManager.getSelectedElements() != null &&
                    viewManager.getSelectedElements().length > 0) {

                return Arrays.stream(viewManager.getSelectedElements())
                        .collect(Collectors.toList());
            }

            return Collections.emptyList();
        } catch (Exception e) {
            throw new ModelAccessException(e);
        }
    }
}
