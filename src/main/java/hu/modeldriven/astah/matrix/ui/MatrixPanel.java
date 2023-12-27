/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.modeldriven.astah.matrix.ui;

import hu.modeldriven.astah.core.model.DummyModel;
import hu.modeldriven.astah.core.model.Model;
import hu.modeldriven.astah.matrix.ui.event.ColumnPackageSelectionRequestedEvent;
import hu.modeldriven.astah.matrix.ui.event.ColumnTypeSelectionRequestedEvent;
import hu.modeldriven.astah.matrix.ui.event.RowPackageSelectionRequestedEvent;
import hu.modeldriven.astah.matrix.ui.event.RowTypeSelectionRequestedEvent;
import hu.modeldriven.astah.matrix.ui.usecase.*;
import hu.modeldriven.core.eventbus.EventBus;

import java.awt.Component;

public class MatrixPanel extends AbstractMatrixPanel {

    private final Component parentComponent;
    private final EventBus eventBus;

    public MatrixPanel(Component parentComponent, EventBus eventBus) {
        super();
        this.parentComponent = parentComponent;
        this.eventBus = eventBus;
        initComponents();
    }

    private void initComponents() {

        Model model = new DummyModel();

        this.eventBus.subscribe(new DisplayRowTypeNameUseCase(rowTypeTextField));
        this.eventBus.subscribe(new DisplayRowTypeSelectorUseCase(parentComponent, eventBus));

        this.eventBus.subscribe(new DisplayColumnTypeNameUseCase(columnTypeTextField));
        this.eventBus.subscribe(new DisplayColumnTypeSelectorUseCase(parentComponent, eventBus));

        this.eventBus.subscribe(new DisplayRowPackageNameUseCase(rowPackageTextField));
        this.eventBus.subscribe(new DisplayRowPackageSelectorUseCase(parentComponent, eventBus, model));

        this.eventBus.subscribe(new DisplayColumnPackageNameUseCase(columnPackageTextField));
        this.eventBus.subscribe(new DisplayColumnPackageSelectorUseCase(parentComponent, eventBus, model));

        rowTypeSelectButton.addActionListener(actionEvent -> {
            this.eventBus.publish(new RowTypeSelectionRequestedEvent());
        });

        rowPackageSelectButton.addActionListener(actionEvent -> {
            this.eventBus.publish(new RowPackageSelectionRequestedEvent());
        });

        columnTypeSelectButton.addActionListener(actionEvent -> {
            this.eventBus.publish(new ColumnTypeSelectionRequestedEvent());
        });

        columnPackageSelectButton.addActionListener(actionEvent -> {
            this.eventBus.publish(new ColumnPackageSelectionRequestedEvent());
        });

    }

}
