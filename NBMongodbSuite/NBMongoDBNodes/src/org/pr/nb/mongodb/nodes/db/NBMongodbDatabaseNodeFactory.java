/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.mongodb.nodes.db;

import com.mongodb.MongoClient;
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
public class NBMongodbDatabaseNodeFactory extends ChildFactory.Detachable<MongoDatabase>{

    private final MongoClient client;
    private List<MongoDatabase> cachedDatabases;

    public NBMongodbDatabaseNodeFactory(MongoClient client) {
        this.client = client;
    }

    @Override
    protected boolean createKeys(List<MongoDatabase> toPopulate) {
        if(cachedDatabases == null) cachedDatabases = new ArrayList<>();
        MongoIterable<String> dbNames = client.listDatabaseNames();
        for (String dbName : dbNames) {
            cachedDatabases.add(client.getDatabase(dbName));
        }
        toPopulate.addAll(cachedDatabases);
        return true;
    }

    @Override
    protected Node createNodeForKey(MongoDatabase key) {
        try {
            return new NBMongodbDatabaseNode(key);
        } catch (IntrospectionException ex) {
            Exceptions.printStackTrace(ex);
        }
        return null;
    }
    
    
}
