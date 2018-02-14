/*
 * Copyright 2016 Mahakaal.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.pr.nb.mongodb.nodes;

import org.pr.nb.mongodb.nodes.db.actions.ConnectAction;
import com.mongodb.MongoClient;
import java.beans.IntrospectionException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Action;
import org.openide.actions.PropertiesAction;
import org.openide.actions.RenameAction;
import org.openide.nodes.BeanNode;
import org.openide.nodes.Children;
import org.openide.util.actions.SystemAction;
import org.pr.nb.mongodb.data.NBMongoDBInstance;
import org.pr.nb.mongodb.nodes.db.NBMongodbDatabaseNodeFactory;
import org.pr.nb.mongodb.nodes.db.actions.DisconnectAction;
import org.pr.nb.nb.mongodb.NBMongoSupportFactory;

/**
 *
 * @author Mahakaal
 */
public class NBMongoDBInstanceNode extends BeanNode<NBMongoDBInstance> {

    private final NBMongoDBInstance bean;
    private MongoClient client;

    public NBMongoDBInstanceNode(NBMongoDBInstance bean) throws IntrospectionException {
        super(bean);
        this.bean = bean;
    }

    @Override
    public String getName() {
        return bean.getDisplayName();
    }

    @Override
    public Action getPreferredAction() {
        return super.getPreferredAction(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean canDestroy() {
        return true;
    }

    @Override
    public void destroy() throws IOException {
        fireNodeDestroyed();
    }

    @Override
    public Action[] getActions(boolean context) {
        List<Action> actions = new ArrayList<>();
        if (client == null) {
            actions.add(new ConnectAction(this));
        } else {
            actions.add(new DisconnectAction(this));
        }
        actions.add(SystemAction.get(RenameAction.class));
        actions.add(SystemAction.get(PropertiesAction.class));
        Action[] data = new Action[actions.size()];
        return actions.toArray(data);
    }

    public void disconnect() {
        if (client == null) {
            return;
        }
        client.close();
        Children children = super.getChildren();
        children.remove(children.getNodes(true));
    }

    public void connect() {
        client = NBMongoSupportFactory.getInstance().connect(bean.getHostName(), bean.getPortNumber(),null,null);
        super.setChildren(Children.create(new NBMongodbDatabaseNodeFactory(client), true));
    }

}
