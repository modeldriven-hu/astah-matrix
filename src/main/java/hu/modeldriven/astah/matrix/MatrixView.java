package hu.modeldriven.astah.matrix;

import com.change_vision.jude.api.inf.project.ProjectAccessor;
import com.change_vision.jude.api.inf.project.ProjectAccessorFactory;
import com.change_vision.jude.api.inf.project.ProjectEvent;
import com.change_vision.jude.api.inf.project.ProjectEventListener;
import com.change_vision.jude.api.inf.ui.IPluginExtraTabView;
import com.change_vision.jude.api.inf.ui.ISelectionListener;
import hu.modeldriven.astah.matrix.ui.MatrixPanel;
import hu.modeldriven.core.eventbus.EventBus;

import javax.swing.JPanel;
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
        EventBus eventBus = new EventBus();
        MatrixPanel panel = new MatrixPanel(this, eventBus);
        return panel;
    }

    @Override
    public void projectChanged(ProjectEvent e) {
    }

    @Override
    public void projectClosed(ProjectEvent e) {
    }

    @Override
    public void projectOpened(ProjectEvent e) {
    }

    @Override
    public void addSelectionListener(ISelectionListener listener) {
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

    }

    public void deactivated() {

    }
}
