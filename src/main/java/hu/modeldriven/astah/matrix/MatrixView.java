package hu.modeldriven.astah.matrix;

import com.change_vision.jude.api.inf.AstahAPI;
import com.change_vision.jude.api.inf.project.ProjectAccessor;
import com.change_vision.jude.api.inf.project.ProjectAccessorFactory;
import com.change_vision.jude.api.inf.project.ProjectEvent;
import com.change_vision.jude.api.inf.project.ProjectEventListener;
import com.change_vision.jude.api.inf.ui.IPluginExtraTabView;
import com.change_vision.jude.api.inf.ui.ISelectionListener;
import hu.modeldriven.astah.matrix.ui.MatrixPanel;
import hu.modeldriven.core.eventbus.EventBus;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;

public class MatrixView extends JPanel implements IPluginExtraTabView, ProjectEventListener {

    public MatrixView() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        add(createContentPane(), BorderLayout.CENTER);
        addProjectEventListener();
    }

    private void addProjectEventListener() {
        try {
            ProjectAccessor projectAccessor = ProjectAccessorFactory.getProjectAccessor();
            projectAccessor.addProjectEventListener(this);
        } catch (ClassNotFoundException e) {
            e.getMessage();
        }
    }

    private Container createContentPane() {

        Component component;

        try {
            component = AstahAPI.getAstahAPI().getViewManager().getMainFrame();
        } catch (Exception e) {
            component = this;
        }

        EventBus eventBus = new EventBus();
        return new MatrixPanel(component, eventBus);
    }

    @Override
    public void projectChanged(ProjectEvent e) {
        // nothing to do here
    }

    @Override
    public void projectClosed(ProjectEvent e) {
        // nothing to do here
    }

    @Override
    public void projectOpened(ProjectEvent e) {
        // nothing to do here
    }

    @Override
    public void addSelectionListener(ISelectionListener listener) {
        // nothing to do here
    }

    @Override
    public Component getComponent() {
        return this;
    }

    @Override
    public String getDescription() {
        return "Dependency Matrix View";
    }

    @Override
    public String getTitle() {
        return "Dependency Matrix";
    }

    public void activated() {
        // nothing to do here
    }

    public void deactivated() {
        // nothing to do here
    }
}
