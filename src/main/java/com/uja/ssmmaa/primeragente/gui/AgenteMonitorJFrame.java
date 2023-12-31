/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssmmaa.primeragente.gui;

import com.uja.ssmmaa.primeragente.agentes.AgenteMonitor;

/**
 *
 * @author danie
 */
public class AgenteMonitorJFrame extends javax.swing.JFrame {

    private AgenteMonitor myAgent;
    private FinalizacionDialog finalizacion;

    /**
     * Creates new form AgenteMonitorJFrame
     */
    public AgenteMonitorJFrame(AgenteMonitor myAgent) {
        initComponents();
        this.myAgent = myAgent;

        this.setTitle(this.myAgent.getName());
    }

    public void presentarSalida(String msg) {
        salidaTexto.append(msg);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        salidaTexto = new javax.swing.JTextArea();
        botonFin = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        salidaTexto.setEditable(false);
        salidaTexto.setColumns(20);
        salidaTexto.setRows(5);
        salidaTexto.setFocusable(false);

        botonFin.setText("Finalizar");
        botonFin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonFinActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(379, Short.MAX_VALUE)
                .addComponent(botonFin)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(salidaTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(67, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 328, Short.MAX_VALUE)
                .addComponent(botonFin))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(salidaTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(35, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonFinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonFinActionPerformed
        // TODO add your handling code here:
        myAgent.doDelete();
    }//GEN-LAST:event_botonFinActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonFin;
    private javax.swing.JTextArea salidaTexto;
    // End of variables declaration//GEN-END:variables
}
