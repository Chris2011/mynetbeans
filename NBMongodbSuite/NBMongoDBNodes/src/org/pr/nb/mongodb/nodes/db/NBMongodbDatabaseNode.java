/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.mongodb.nodes.db;

import com.mongodb.client.MongoDatabase;
import java.beans.IntrospectionException;
import org.openide.nodes.BeanNode;
import org.openide.nodes.Children;

/**
 *
 * @author mahakaal
 */
public class NBMongodbDatabaseNode extends BeanNode<MongoDatabase>{
    private final MongoDatabase bean;
    public NBMongodbDatabaseNode(MongoDatabase bean) throws IntrospectionException {
        super(bean);
        this.bean = bean;
        super.setChildren(Children.create(new NBMongodbCollectionFactory(bean), true));
        setName(bean.getName());
    }
}
