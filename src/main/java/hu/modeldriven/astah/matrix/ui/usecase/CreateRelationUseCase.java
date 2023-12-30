package hu.modeldriven.astah.matrix.ui.usecase;

import com.change_vision.jude.api.inf.AstahAPI;
import com.change_vision.jude.api.inf.editor.ITransactionManager;
import com.change_vision.jude.api.inf.exception.BadTransactionException;
import com.change_vision.jude.api.inf.project.ProjectAccessor;
import hu.modeldriven.astah.core.dialog.type.DependencyTypeSelectorData;
import hu.modeldriven.astah.matrix.ui.event.CreateRelationshipRequestedEvent;
import hu.modeldriven.astah.matrix.ui.event.DependencyTypeSelectedEvent;
import hu.modeldriven.astah.matrix.ui.event.ExceptionOccuredEvent;
import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.eventbus.EventHandler;

import java.util.Arrays;
import java.util.List;

public class CreateRelationUseCase implements EventHandler<Event> {

    private final EventBus eventBus;

    private final DependencyTypeSelectorData data;

    private String typeName;

    public CreateRelationUseCase(EventBus eventBus) {
        this.eventBus = eventBus;
        this.data = new DependencyTypeSelectorData();
    }

    @Override
    public void handleEvent(Event event) {
        if (event instanceof DependencyTypeSelectedEvent) {
            handleDependencyTypeSelected((DependencyTypeSelectedEvent) event);
        }

        if (event instanceof CreateRelationshipRequestedEvent) {
            handleCreateRelationshipRequested((CreateRelationshipRequestedEvent) event);
        }
    }

    private void handleDependencyTypeSelected(DependencyTypeSelectedEvent event) {
        this.typeName = event.name();
    }

    private void handleCreateRelationshipRequested(CreateRelationshipRequestedEvent event) {

        try {
            AstahAPI api = AstahAPI.getAstahAPI();
            ProjectAccessor projectAccessor = api.getProjectAccessor();

            ITransactionManager transactionManager = projectAccessor.getTransactionManager();

            try {
                transactionManager.beginTransaction();

                switch (event.direction()) {
                    case ROW_TO_COLUMN:
                        data.createRelationship(typeName, projectAccessor, event.sourceElement(), event.targetElement());

                    case COLUMN_TO_ROW:
                        data.createRelationship(typeName, projectAccessor, event.targetElement(), event.sourceElement());
                }

                transactionManager.endTransaction();
            } catch (BadTransactionException e) {
                transactionManager.abortTransaction();
                throw new RuntimeException(e);
            }

        } catch (Exception e) {
            eventBus.publish(new ExceptionOccuredEvent(e));
        }

    }

    @Override
    public List<Class<? extends Event>> subscribedEvents() {
        return Arrays.asList(CreateRelationshipRequestedEvent.class,
                DependencyTypeSelectedEvent.class);
    }
}
