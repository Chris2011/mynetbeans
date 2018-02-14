/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.mongodb.nodes.db;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import java.beans.IntrospectionException;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;

/**
 *
 * @author mahakaal
 */
public class NBMongodbCollectionFactory extends ChildFactory.Detachable<MongoCollection<Document>>{

    private final MongoDatabase database;
    private List<MongoCollection<Document>> cachedCollections;
    public NBMongodbCollectionFactory(MongoDatabase bean) {
        database = bean;
    }

    @Override
    protected boolean createKeys(List<MongoCollection<Document>> toPopulate) {
        if(cachedCollections == null)cachedCollections = new ArrayList<>();
        MongoIterable<String> colNames = database.listCollectionNames();
        for (String colName : colNames) {
            cachedCollections.add(database.getCollection(colName));
        }
        toPopulate.addAll(cachedCollections);
        return true;
    }

    @Override
    protected Node createNodeForKey(MongoCollection<Document> key) {
        try {
            return new NBMongodbCollectionNode(key);
        } catch (IntrospectionException ex) {
            Exceptions.printStackTrace(ex);
        }
        return null;
    }
    
    
}
