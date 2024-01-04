package hu.modeldriven.astah.matrix.ui.usecase;

import com.change_vision.jude.api.inf.AstahAPI;
import com.change_vision.jude.api.inf.editor.ITransactionManager;
import com.change_vision.jude.api.inf.exception.BadTransactionException;
import com.change_vision.jude.api.inf.model.INamedElement;
import com.change_vision.jude.api.inf.project.ProjectAccessor;
import hu.modeldriven.astah.core.dialog.type.DependencyTypeSelectorData;
import hu.modeldriven.astah.matrix.ui.event.CreateRelationshipRequestedEvent;
import hu.modeldriven.astah.matrix.ui.event.DependencyTypeSelectedEvent;
import hu.modeldriven.astah.matrix.ui.event.ExceptionOccuredEvent;
import hu.modeldriven.astah.matrix.ui.event.QueryRequestedEvent;
import hu.modeldriven.astah.matrix.ui.table.RelationshipDirection;
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
            createRelations(api, event.direction(), event.sourceElement(), event.targetElement());

        } catch (Exception e) {
            eventBus.publish(new ExceptionOccuredEvent(e));
        }

    }

    private void createRelations(AstahAPI api, RelationshipDirection direction, INamedElement source, INamedElement target) {

        ProjectAccessor projectAccessor = api.getProjectAccessor();
        ITransactionManager transactionManager = projectAccessor.getTransactionManager();

        try {
            transactionManager.beginTransaction();

            switch (direction) {
                case COLUMN_TO_ROW:
                    data.createRelationship(typeName, projectAccessor, source, target);
                    break;
                case ROW_TO_COLUMN:
                    data.createRelationship(typeName, projectAccessor, target, source);
                    break;
                default:
                    break;
            }

            transactionManager.endTransaction();

            eventBus.publish(new QueryRequestedEvent());

        } catch (BadTransactionException e) {
            transactionManager.abortTransaction();
            eventBus.publish(new ExceptionOccuredEvent(e));
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
