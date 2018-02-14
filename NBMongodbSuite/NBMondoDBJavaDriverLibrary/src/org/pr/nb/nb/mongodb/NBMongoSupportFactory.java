/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.nb.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientException;
import com.mongodb.ServerAddress;

/**
 *
 * @author mahakaal
 */
public class NBMongoSupportFactory {

    private NBMongoSupportFactory() {
    }

    public static NBMongoSupportFactory getInstance() {
        return MongoConnectionFactoryHolder.INSTANCE;
    }

    public MongoClient connect(String host, int port, String userName, char[] password) throws MongoClientException {
        ServerAddress address = new ServerAddress(host, port);
        MongoClient client = new MongoClient(address);
        return client;
    }

    private static class MongoConnectionFactoryHolder {

        private static final NBMongoSupportFactory INSTANCE = new NBMongoSupportFactory();
    }
}
