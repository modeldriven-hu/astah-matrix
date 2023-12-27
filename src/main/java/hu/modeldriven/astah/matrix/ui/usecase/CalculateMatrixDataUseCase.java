package hu.modeldriven.astah.matrix.ui.usecase;

import com.change_vision.jude.api.inf.model.IPackage;
import hu.modeldriven.astah.core.dialog.type.matcher.TypeMatcher;
import hu.modeldriven.astah.matrix.ui.event.*;
import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.eventbus.EventHandler;

import java.util.Arrays;
import java.util.List;

public class CalculateMatrixDataUseCase implements EventHandler {

    private final EventBus eventBus;
    private final QueryInfo queryInfo;

    public CalculateMatrixDataUseCase(EventBus eventBus) {
        this.eventBus = eventBus;
        this.queryInfo = new QueryInfo();
    }

    @Override
    public void handleEvent(Event event) {
        if (event instanceof ColumnPackageSelectedEvent){
            queryInfo.columnPackage = ((ColumnPackageSelectedEvent)event).selectedPackage();
        }

        if (event instanceof ColumnTypeSelectedEvent){
            queryInfo.columnTypeMatcher = ((ColumnTypeSelectedEvent)event).elementMatcher();
        }

        if (event instanceof RowPackageSelectedEvent){
            queryInfo.rowPackage = ((RowPackageSelectedEvent)event).selectedPackage();
        }

        if (event instanceof RowTypeSelectedEvent){
            queryInfo.rowTypeMatcher = ((RowTypeSelectedEvent)event).elementMatcher();
        }

        if (event instanceof DependencyTypeSelectedEvent){
            queryInfo.dependencyTypeMatcher = ((DependencyTypeSelectedEvent)event).elementMatcher();
        }

        if (event instanceof QueryRequestedEvent){
            createMatrix();
        }

    }

    private void createMatrix(){
        System.out.println("Creating matrix");
        // TODO send a new event
    }

    @Override
    public List<Class<? extends Event>> subscribedEvents() {
        return Arrays.asList(
                QueryRequestedEvent.class,
                ColumnPackageSelectedEvent.class,
                ColumnTypeSelectedEvent.class,
                RowPackageSelectedEvent.class,
                RowTypeSelectedEvent.class,
                DependencyTypeSelectedEvent.class);
    }

    class QueryInfo{
        IPackage columnPackage;
        TypeMatcher columnTypeMatcher;

        IPackage rowPackage;
        TypeMatcher rowTypeMatcher;

        TypeMatcher dependencyTypeMatcher;

    }

}
