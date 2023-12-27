/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.modeldriven.astah.matrix.ui;

import hu.modeldriven.astah.matrix.ui.event.RowElementTypeSelectionRequestedEvent;
import hu.modeldriven.astah.matrix.ui.usecase.DisplayRowTypeNameUseCase;
import hu.modeldriven.astah.matrix.ui.usecase.DisplayRowTypeSelectorUseCase;
import hu.modeldriven.core.eventbus.EventBus;

import java.awt.Component;

/**
 * @author zsolt
 */
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

        this.eventBus.subscribe(new DisplayRowTypeNameUseCase(rowTypeTextField));
        this.eventBus.subscribe(new DisplayRowTypeSelectorUseCase(parentComponent, eventBus));

        rowTypeSelectButton.addActionListener(actionEvent -> {
            this.eventBus.publish(new RowElementTypeSelectionRequestedEvent());
        });
    }

}
