package hu.modeldriven.astah.matrix.ui.usecase;

import com.change_vision.jude.api.inf.AstahAPI;
import com.change_vision.jude.api.inf.editor.IModelEditorFactory;
import com.change_vision.jude.api.inf.editor.ITransactionManager;
import com.change_vision.jude.api.inf.exception.BadTransactionException;
import com.change_vision.jude.api.inf.model.INamedElement;
import com.change_vision.jude.api.inf.project.ProjectAccessor;
import hu.modeldriven.astah.core.dialog.typeselector.relationship.Relationship;
import hu.modeldriven.astah.matrix.ui.event.CreateRelationshipRequestedEvent;
import hu.modeldriven.astah.matrix.ui.event.ExceptionOccurredEvent;
import hu.modeldriven.astah.matrix.ui.event.QueryRequestedEvent;
import hu.modeldriven.astah.matrix.ui.event.RelationshipSelectedEvent;
import hu.modeldriven.astah.matrix.ui.table.RelationshipDirection;
import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.eventbus.EventHandler;

import java.util.Arrays;
import java.util.List;

public class CreateRelationUseCase implements EventHandler<Event> {

    private final EventBus eventBus;

    private Relationship relationship;

    public CreateRelationUseCase(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void handleEvent(Event event) {
        if (event instanceof RelationshipSelectedEvent) {
            handleDependencyTypeSelected((RelationshipSelectedEvent) event);
        }

        if (event instanceof CreateRelationshipRequestedEvent) {
            handleCreateRelationshipRequested((CreateRelationshipRequestedEvent) event);
        }
    }

    private void handleDependencyTypeSelected(RelationshipSelectedEvent event) {
        this.relationship = event.relationship();
    }

    private void handleCreateRelationshipRequested(CreateRelationshipRequestedEvent event) {

        try {
            AstahAPI api = AstahAPI.getAstahAPI();
            createRelationship(api, event.direction(), event.sourceElement(), event.targetElement());
        } catch (Exception e) {
            eventBus.publish(new ExceptionOccurredEvent(e));
        }

    }

    private void createRelationship(AstahAPI api, RelationshipDirection direction, INamedElement source, INamedElement target) {

        ProjectAccessor projectAccessor = api.getProjectAccessor();
        IModelEditorFactory factory = projectAccessor.getModelEditorFactory();
        ITransactionManager transactionManager = projectAccessor.getTransactionManager();

        try {
            transactionManager.beginTransaction();

            switch (direction) {
                case COLUMN_TO_ROW:
                    relationship.createRelationship(factory, relationship.name(), source, target);
                    break;

                case ROW_TO_COLUMN:
                    relationship.createRelationship(factory, relationship.name(), target, source);
                    break;
                default:
                    break;
            }

            transactionManager.endTransaction();

            eventBus.publish(new QueryRequestedEvent());

        } catch (BadTransactionException e) {
            transactionManager.abortTransaction();
            eventBus.publish(new ExceptionOccurredEvent(e));
        } catch (Exception e) {
            eventBus.publish(new ExceptionOccurredEvent(e));
        }
    }

    @Override
    public List<Class<? extends Event>> subscribedEvents() {
        return Arrays.asList(CreateRelationshipRequestedEvent.class,
                RelationshipSelectedEvent.class);
    }
}
