package hu.modeldriven.astah.matrix;


import com.change_vision.jude.api.inf.AstahAPI;
import com.change_vision.jude.api.inf.exception.ProjectNotFoundException;
import com.change_vision.jude.api.inf.project.ProjectAccessor;
import com.change_vision.jude.api.inf.ui.IPluginActionDelegate;
import com.change_vision.jude.api.inf.ui.IWindow;
import hu.modeldriven.astah.matrix.ui.MatrixScreen;

import javax.swing.JOptionPane;

public class TemplateAction implements IPluginActionDelegate {

    public Object run(IWindow window) throws UnExpectedException {
        try {
            AstahAPI api = AstahAPI.getAstahAPI();
            ProjectAccessor projectAccessor = api.getProjectAccessor();
            projectAccessor.getProject();

            MatrixScreen matrixScreen = new MatrixScreen(window.getParent());
            matrixScreen.show();

        } catch (ProjectNotFoundException e) {
            String message = "Project is not opened.Please open the project or create new project.";
            JOptionPane.showMessageDialog(window.getParent(), message, "Warning", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(window.getParent(), "Unexpected error has occurred.", "Alert", JOptionPane.ERROR_MESSAGE);
            throw new UnExpectedException();
        }
        return null;
    }


}
