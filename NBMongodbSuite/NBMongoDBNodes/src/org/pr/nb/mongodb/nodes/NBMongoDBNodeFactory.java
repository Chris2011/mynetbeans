/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.mongodb.nodes;

import java.beans.IntrospectionException;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import org.apache.commons.lang.StringUtils;
import org.json.simple.parser.ParseException;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.modules.Places;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.nodes.NodeAdapter;
import org.openide.util.Exceptions;
import org.pr.nb.mongodb.component.PropertiesNotifier;
import org.pr.nb.mongodb.component.PropertyNames;
import org.pr.nb.mongodb.data.NBMongoDBInstance;

/**
 *
 * @author mahakaal
 */
class NBMongoDBNodeFactory extends ChildFactory.Detachable<NBMongoDBInstance> {

    //location of registered mongo db instances in user config directory
    private static final String CONFIG_DATABASES_MONGO_DB = "config/Databases/MongoDB";

    List<NBMongoDBInstance> registeredInstances;

    public NBMongoDBNodeFactory() {
        PropertiesNotifier.addPropertyChangeListener(PropertyNames.NEW_MONGODB_INSTANCE, new NodeEventWrapper());
    }

    @Override
    protected boolean createKeys(List<NBMongoDBInstance> list) {
        if (registeredInstances == null) {
            registeredInstances = loadStoredInstances();
        }
        list.addAll(registeredInstances);
        return true;
    }

    @Override
    protected Node createNodeForKey(NBMongoDBInstance key) {
        try {
            return new NBMongoDBInstanceNode(key);
        } catch (IntrospectionException ex) {
            Exceptions.printStackTrace(ex);
        }
        return null;
    }
    
    

    private List<NBMongoDBInstance> loadStoredInstances() {
        List<NBMongoDBInstance> retValue = new ArrayList<>();
        try {

            FileObject mongoDBInstanceDirectory = getConfigStore();
            FileObject[] registeredInstancesJSONFiles = mongoDBInstanceDirectory.getChildren();
            for (FileObject registeredInstanceJSONFile : registeredInstancesJSONFiles) {
                NBMongoDBInstance instance = NBMongoDBInstance.deserialize(registeredInstanceJSONFile);
                retValue.add(instance);
            }
        } catch (IOException | ParseException ex) {
            Exceptions.printStackTrace(ex);
        }
        return retValue;
    }

    private FileObject getConfigStore() throws IOException {
        File userDir = Places.getUserDirectory();
        FileObject userDirFileObject = FileUtil.toFileObject(FileUtil.normalizeFile(userDir));
        return FileUtil.createFolder(userDirFileObject, CONFIG_DATABASES_MONGO_DB);
    }
    
   private class NodeEventWrapper extends NodeAdapter{
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if(StringUtils.equals(PropertyNames.NEW_MONGODB_INSTANCE.name(),evt.getPropertyName())){
                NBMongoDBInstance instance = (NBMongoDBInstance) evt.getNewValue();
                registeredInstances.add(instance);
                Executors.newSingleThreadExecutor().execute(() -> {
                    try {
                        instance.serialize(getConfigStore());
                    } catch (IOException ex) {
                        Exceptions.printStackTrace(ex);
                    }
                });
            }
            refresh(true);
        }
       
   }
    
}
