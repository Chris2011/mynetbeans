/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.mongodb.nodes.db;

import com.mongodb.client.MongoCollection;
import java.beans.IntrospectionException;
import org.bson.Document;
import org.openide.nodes.BeanNode;

/**
 *
 * @author mahakaal
 */
public class NBMongodbCollectionNode extends BeanNode<MongoCollection<Document>> {

    private final MongoCollection<Document> bean;

    public NBMongodbCollectionNode(MongoCollection<Document> bean) throws IntrospectionException {
        super(bean);
        this.bean = bean;
        setName(bean.getNamespace().getCollectionName());
    }
    
}
