/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.mongodb.nodes.db.actions;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import org.openide.util.NbBundle;
import org.pr.nb.mongodb.nodes.NBMongoDBInstanceNode;

/**
 *
 * @author mahakaal
 */
@NbBundle.Messages({
    "DISCONNECT_ACTION_NAME=Disconnect"
})
public class DisconnectAction extends AbstractAction {

    NBMongoDBInstanceNode node;
    public DisconnectAction(NBMongoDBInstanceNode node) {
        this.node = node;
        super.putValue(Action.NAME, org.pr.nb.mongodb.nodes.db.actions.Bundle.DISCONNECT_ACTION_NAME());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        node.disconnect();
    }
    
}
