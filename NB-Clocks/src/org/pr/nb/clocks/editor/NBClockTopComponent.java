/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pr.nb.clocks.editor;

import java.awt.BorderLayout;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.ExplorerUtils;
import org.openide.explorer.view.BeanTreeView;
import org.openide.explorer.view.OutlineView;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;
import org.pr.nb.clocks.nodes.NBClocksNodeFactory;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//org.pr.nb.clocks.editor//NBClock//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "NBClockTopComponent",
        iconBase = "org/pr/nb/clocks/editor/clock.png",
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window",
        id = "org.pr.nb.clocks.editor.NBClockTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_NBClockAction",
        preferredID = "NBClockTopComponent"
)
@Messages({
    "CTL_NBClockAction=NetBeans Clocks",
    "CTL_NBClockTopComponent=NetBeans Clocks Window",
    "HINT_NBClockTopComponent=NetBeans Clocks Window"
})
public final class NBClockTopComponent extends TopComponent implements
        ExplorerManager.Provider {

    public NBClockTopComponent() {
        initComponents();
        setName(Bundle.CTL_NBClockTopComponent());
        setToolTipText(Bundle.HINT_NBClockTopComponent());
        associateLookup(ExplorerUtils.createLookup(expMgr, getActionMap()));
        expMgr.setRootContext(new AbstractNode(Children.create(
                new NBClocksNodeFactory(), true)));
        setLayout(new BorderLayout());
        OutlineView outlineView = new OutlineView("Clocks");
        outlineView.getOutline().setRootVisible(false);
        add(outlineView, BorderLayout.CENTER);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    @Override
    public ExplorerManager getExplorerManager() {
        return expMgr;
    }
    private final ExplorerManager expMgr = new ExplorerManager();
}
