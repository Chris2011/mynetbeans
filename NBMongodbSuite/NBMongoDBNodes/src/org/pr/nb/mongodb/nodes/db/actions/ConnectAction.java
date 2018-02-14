/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.mongodb.nodes.db.actions;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import javax.swing.AbstractAction;
import javax.swing.Action;
import org.openide.util.NbBundle;
import org.pr.nb.mongodb.nodes.NBMongoDBInstanceNode;

/**
 *
 * @author mahakaal
 */
@NbBundle.Messages({
    "CONNECT_ACTION_NAME=Connect"
}
)
public class ConnectAction extends AbstractAction {

    NBMongoDBInstanceNode node;
    public ConnectAction(NBMongoDBInstanceNode node) {
        this.node = node;
        super.putValue(Action.NAME, Bundle.CONNECT_ACTION_NAME());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        node.connect();
    }

}
