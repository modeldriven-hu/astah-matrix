package hu.modeldriven.astah.matrix.ui.usecase;

import com.change_vision.jude.api.inf.AstahAPI;
import com.change_vision.jude.api.inf.view.IProjectViewManager;
import com.change_vision.jude.api.inf.view.IViewManager;
import hu.modeldriven.astah.matrix.ui.event.ExceptionOccuredEvent;
import hu.modeldriven.astah.matrix.ui.event.ShowInStructureTreeRequestedEvent;
import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.eventbus.EventHandler;

import java.util.Collections;
import java.util.List;

public class ShowElementInStructureTreeUseCase implements EventHandler<ShowInStructureTreeRequestedEvent> {

    private final EventBus eventBus;

    public ShowElementInStructureTreeUseCase(EventBus eventBus){
        this.eventBus = eventBus;
    }

    @Override
    public void handleEvent(ShowInStructureTreeRequestedEvent event) {
        try {
            AstahAPI api = AstahAPI.getAstahAPI();
            IViewManager manager = api.getViewManager();
            IProjectViewManager projectViewManager = manager.getProjectViewManager();
            projectViewManager.showInStructureTree(event.element());
        } catch (Exception exception) {
            eventBus.publish(new ExceptionOccuredEvent(exception));
        }
    }

    @Override
    public List<Class<? extends Event>> subscribedEvents() {
        return Collections.singletonList(ShowInStructureTreeRequestedEvent.class);
    }
}
