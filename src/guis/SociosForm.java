
package guis;

import entidades.Socio;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import persistencia.ISociosDAO;

public class SociosForm extends javax.swing.JFrame {

    private final ISociosDAO sociosDAO;
    
    /**
     * Creates new form SociosForm
     */
    public SociosForm(ISociosDAO sociosDAO) {
        initComponents();
        this.sociosDAO = sociosDAO;
        this.llenarTabla();
    }
    
    private void guardar(){
        //To Do: COMPROBAR SI SE AGREGA O ACTUALIZA 
        if(this.txtIdSocio.getText().isEmpty()){
        this.agregar();
        }else{
            this.actualizar();
        }
    }
    
    private void agregar(){
        String nombre = this.txtNombre.getText();
        String curp = this.txtCurp.getText();
        
        Socio socioNuevo = new Socio(nombre,curp);
        this.sociosDAO.agregar(socioNuevo);
        boolean seAgregoSocio= this.sociosDAO.agregar(socioNuevo);
        if(seAgregoSocio){
            JOptionPane.showMessageDialog(this,"se agrego el socio","informacion"
                    , JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(this,"no se agrego el socio","error"
                    , JOptionPane.INFORMATION_MESSAGE);
        }
        this.limpiarFormulario();
        this.llenarTabla();
    }
    
    private void actualizar(){
        //FALTA ACTUALIZAR LA DAO
        Long idSocio = Long.parseLong(this.txtIdSocio.getText());
        String nombre = this.txtNombre.getText();
        String curp = this.txtCurp.getText();
        
        Socio socioNuevo = new Socio(nombre,curp);
        this.sociosDAO.agregar(socioNuevo);
        boolean seAgregoSocio= this.sociosDAO.actualizar(socioNuevo);
        if(seAgregoSocio){
            JOptionPane.showMessageDialog(this,"se actualizo el socio","informacion"
                    , JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(this,"no se actualizo el socio","error"
                    , JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void llenarTabla(){
        List<Socio> listaSocios = this.sociosDAO.consultarTodos();
        DefaultTableModel modelo= (DefaultTableModel)this.jTableSocios.getModel();
        modelo.setRowCount(0);
        listaSocios.forEach(socio -> {
            Object[] fila = new Object[3];
            fila[0]= socio.getIdSocio();
            fila[1]= socio.getNombre();
            fila[2]= socio.getCurp();
            modelo.addRow(fila);
        });
        
    }
    
    private void limpiarFormulario(){
        this.txtIdSocio.setText(null);
        this.txtNombre.setText("");
        this.txtCurp.setText("");
    }
    
    private void eliminar(){
        //
        Long idSocioSeleccionado = this.getIdSocioSeleccionado();
        if(idSocioSeleccionado== null){
            JOptionPane.showMessageDialog(this,"debes seleccionar un socio", "error", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        int opcionSeleccionada = JOptionPane.showConfirmDialog(this, "¿Seguro que deseas eliminar al socio?",
                "Confirmacion", JOptionPane.YES_NO_OPTION);
        if(opcionSeleccionada == JOptionPane.CANCEL_OPTION){
            return;
        }
        
        boolean seEliminoSocio= this.sociosDAO.eliminar(idSocioSeleccionado);
        if(seEliminoSocio){
            JOptionPane.showMessageDialog(this,"se elimino el socio", "informacion", JOptionPane.INFORMATION_MESSAGE);
            this.llenarTabla();
        }else{
            JOptionPane.showMessageDialog(this,"no se pudo eliminar el socio", "error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private Long getIdSocioSeleccionado(){
        int indiceFilaSeleccionado = this.jTableSocios.getSelectedRow();
        if(indiceFilaSeleccionado==1){
         DefaultTableModel modelo= (DefaultTableModel)this.jTableSocios.getModel();
         int indiceColumnaId=0;
         Long idSocioSeleccionado =(Long)modelo.getValueAt(indiceFilaSeleccionado, indiceColumnaId);
         return idSocioSeleccionado;
        }else{
            return null;
        }
    }
    
    private void editar(){
        Long idSocioSeleccionado= this.getIdSocioSeleccionado();
        if(idSocioSeleccionado == null ){
            JOptionPane.showMessageDialog(this, "debes seleccionar un socio",
                    "informacion", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        Socio socio = this.sociosDAO.consultar(idSocioSeleccionado);
        if(socio != null){
            llenarFormulario(socio);
        }else{
            JOptionPane.showMessageDialog(this, "debes seleccionar un socio",
                    "informacion", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
    }
    
    private void llenarFormulario(Socio socio){
        this.txtIdSocio.setText(Long.toString(socio.getIdSocio()));
        this.txtNombre.setText(socio.getNombre());
        this.txtCurp.setText(socio.getCurp());
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblIdSocio = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblCurp = new javax.swing.JLabel();
        txtIdSocio = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtCurp = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        pnlTablaSocios = new javax.swing.JScrollPane();
        jTableSocios = new javax.swing.JTable();
        btEditar = new javax.swing.JButton();
        btEliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Administración de Socios");

        lblIdSocio.setText("Id Socio");

        lblNombre.setText("Nombre");

        lblCurp.setText("Curp");

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jTableSocios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "id Socio", "Nombre", "Curp"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        pnlTablaSocios.setViewportView(jTableSocios);

        btEditar.setText("Editar");
        btEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditarActionPerformed(evt);
            }
        });

        btEliminar.setText("Eliminar");
        btEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblIdSocio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                    .addComponent(lblCurp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addComponent(btnCancelar))
                    .addComponent(txtIdSocio, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombre)
                    .addComponent(txtCurp))
                .addGap(18, 18, 18)
                .addComponent(pnlTablaSocios, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTablaSocios, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btEditar)
                                .addGap(18, 18, 18)
                                .addComponent(btEliminar))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblIdSocio)
                                    .addComponent(txtIdSocio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblNombre)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblCurp)
                                    .addComponent(txtCurp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnGuardar)
                                    .addComponent(btnCancelar))))
                        .addGap(0, 126, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        this.guardar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        this.limpiarFormulario();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEliminarActionPerformed
        // TODO add your handling code here:
        this.eliminar();
    }//GEN-LAST:event_btEliminarActionPerformed

    private void btEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditarActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_btEditarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btEditar;
    private javax.swing.JButton btEliminar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JTable jTableSocios;
    private javax.swing.JLabel lblCurp;
    private javax.swing.JLabel lblIdSocio;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JScrollPane pnlTablaSocios;
    private javax.swing.JTextField txtCurp;
    private javax.swing.JTextField txtIdSocio;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
