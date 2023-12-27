/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.modeldriven.astah.matrix.ui;

import hu.modeldriven.astah.core.dialog.element.ElementTypeSelectorDialog;
import hu.modeldriven.astah.matrix.ui.event.RowElementTypeSelectedEvent;
import hu.modeldriven.core.eventbus.EventBus;

import java.awt.Component;

/**
 * @author zsolt
 */
public class MatrixPanel extends AbstractMatrixPanel {

    private final Component parentComponent;
    private final EventBus eventBus;

    public MatrixPanel(Component parentComponent, EventBus eventBus){
        super();
        this.parentComponent = parentComponent;
        this.eventBus = eventBus;
        initComponents();
    }

    private void initComponents(){
        rowTypeSelectButton.addActionListener(actionEvent -> {

            ElementTypeSelectorDialog dialog = new ElementTypeSelectorDialog(
                    parentComponent,
                    es -> eventBus.publish(
                            new RowElementTypeSelectedEvent(
                                es.name(),
                                es.matcher()
                    ))
            );

            dialog.show();
        });
    }

}
