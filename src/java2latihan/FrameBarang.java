/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java2latihan;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.RowSorter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author DEV
 */
public class FrameBarang extends javax.swing.JFrame {

    private Statement stat;
    private ResultSet rs;
    DefaultTableModel model;
    private String judulKolom[]={"No.","Kode Barang","Nama Barang","Kategori","Satuan","Harga","Jumlah"};
    private String[][] dataBarang;
    String objKategori[]={"Alat Tulis","Kertas","Buku","ATK"};
    
    /**
     * Creates new form FrameBarang
     */
    public FrameBarang() {
        initComponents();
        setModeltabel();
        view_data();
        
        btnCetak.setVisible(false);
        for (String object : objKategori) {
            cbkategori.addItem(object);
        }
         
        tblBarang.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
        @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = tblBarang.getSelectedRow();
                if(row!=-1){
                    String tKode =(tblBarang.getModel().getValueAt(row, 1).toString());
                    String tNama =(tblBarang.getModel().getValueAt(row, 2).toString());
                    String tKategori =(tblBarang.getModel().getValueAt(row, 3).toString());
                    String tSatuan =(tblBarang.getModel().getValueAt(row, 4).toString());
                    String tHarga =(tblBarang.getModel().getValueAt(row, 5).toString());
                    String tJumlah =(tblBarang.getModel().getValueAt(row, 6).toString());

                    txtkode.setText(tKode);
                    txtnamabarang.setText(tNama);
                    txtharga.setText(tHarga);
                    txtjumlah.setText(tJumlah);
                    btnUbah.setEnabled(true);
                    btnHapus.setEnabled(true);
                    btnSimpan.setEnabled(false);

                    cbkategori.setSelectedItem(tKategori);
                    if (tSatuan.equals("pcs")) {
                        rbpcs.setSelected(true);
                    }else if(tSatuan.equals("box")){
                        rbbox.setSelected(true);
                    }else{
                        rbrim.setSelected(true);
                    }
                }
            }
        });
        
        awal();
    }
    
    private void setModeltabel(){
        // membuat tampilan model tabel
        model = new DefaultTableModel(dataBarang,judulKolom);
        tblBarang.setModel(model);
    }
    
    private void awal(){
        txtkode.setText("");
        txtnamabarang.setText("");
        txtharga.setText("");
        txtjumlah.setText("");
        cbkategori.setSelectedIndex(0);
        rbpcs.setSelected(false);
        rbbox.setSelected(false);
        rbrim.setSelected(false);
        txtkode.requestFocus();
        tblBarang.getSelectionModel().clearSelection();
        btnUbah.setEnabled(false);
        btnHapus.setEnabled(false);
        btnSimpan.setEnabled(true);
    }
    
    private void view_data(){
        
        model.getDataVector().removeAllElements();
        //menampilkan data database kedalam tabel
        try {
            int no=1;
            String sql = "select * from barang";
            koneksi objkoneksi=new koneksi();
            Connection con=objkoneksi.bukakoneksi();
            stat = con.createStatement();
            rs = stat.executeQuery(sql);
            while(rs.next()){
                model.addRow(new Object[]{no++,rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)});
            }
            
        } catch (Exception e) {
        }
    }
    
    
    private void cetak(String nmfile){
        try{
            koneksi objkoneksi=new koneksi();
            Connection con=objkoneksi.bukakoneksi();
            File report_file = new File(nmfile);
            JasperReport jasperReport =(JasperReport) JRLoader.loadObject(report_file);
            JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport, null,con);
            JasperViewer.viewReport(jasperPrint,false);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroupSatuan = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtkode = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBarang = new javax.swing.JTable();
        txtnamabarang = new javax.swing.JTextField();
        cbkategori = new javax.swing.JComboBox<>();
        rbpcs = new javax.swing.JRadioButton();
        rbbox = new javax.swing.JRadioButton();
        rbrim = new javax.swing.JRadioButton();
        txtharga = new javax.swing.JTextField();
        txtjumlah = new javax.swing.JTextField();
        btnSimpan = new javax.swing.JButton();
        btnUbah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        btnCetak = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("FORM BARANG ");

        jLabel2.setText("Kode Barang : ");

        jLabel3.setText("Nama Barang :");

        jLabel4.setText("Kategori :");

        jLabel5.setText("Satuan : ");

        jLabel6.setText("Harga :");

        jLabel7.setText("Jumlah :");

        tblBarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBarangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBarang);

        cbkategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- pilih kategori --" }));

        btnGroupSatuan.add(rbpcs);
        rbpcs.setText("pcs");
        rbpcs.setOpaque(false);

        btnGroupSatuan.add(rbbox);
        rbbox.setText("box");
        rbbox.setOpaque(false);

        btnGroupSatuan.add(rbrim);
        rbrim.setText("rim");
        rbrim.setOpaque(false);

        txtharga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txthargaKeyTyped(evt);
            }
        });

        txtjumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtjumlahActionPerformed(evt);
            }
        });
        txtjumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtjumlahKeyTyped(evt);
            }
        });

        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnUbah.setText("Ubah");
        btnUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahActionPerformed(evt);
            }
        });

        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnBatal.setText("Batal");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

        btnCetak.setText("CETAK");
        btnCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetakActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(308, 308, 308))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(btnSimpan)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnBatal)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnUbah)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnHapus))
                                            .addComponent(txtnamabarang, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbkategori, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 165, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtjumlah, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtharga, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(rbpcs)
                                                .addGap(18, 18, 18)
                                                .addComponent(rbbox)))
                                        .addGap(18, 18, 18)
                                        .addComponent(rbrim)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txtkode, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(83, 83, 83)
                        .addComponent(btnCetak)))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnBatal, btnHapus, btnSimpan, btnUbah});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtkode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtnamabarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(cbkategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(rbpcs)
                    .addComponent(rbbox)
                    .addComponent(rbrim))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtharga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtjumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpan)
                    .addComponent(btnUbah)
                    .addComponent(btnHapus)
                    .addComponent(btnBatal)
                    .addComponent(btnCetak))
                .addGap(13, 13, 13)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnBatal, btnHapus, btnSimpan, btnUbah});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtjumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtjumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtjumlahActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        if(cbkategori.getSelectedIndex()!=0){
            String satuan;
            if(rbpcs.isSelected()){
                satuan = "pcs";
            }else if(rbbox.isSelected()){
                satuan = "box";
            }else if(rbrim.isSelected()){
                satuan = "rim";
            }else{
                satuan = null;
            }

            if(satuan != null){
                try{
                    koneksi objkoneksi=new koneksi();
                    Connection con=objkoneksi.bukakoneksi();
                    String sql="INSERT INTO barang VALUES ('"+txtkode.getText()+"','"+txtnamabarang.getText()+"','"+cbkategori.getSelectedItem()+"','"+satuan+"','"+txtharga.getText()+"','"+txtjumlah.getText()+"')";
                    stat = con.createStatement();
                    stat.execute(sql);

                    JOptionPane.showMessageDialog(null, "Data Berhasil di Input");
                    view_data();
                }
                catch (Exception e)
                { 
                    System.out.println("gagal="+e.getMessage());
                }
            }else{
                JOptionPane.showMessageDialog(null, "Pilih satuan barang","notifikasi",2);
            }
            
        }else{
            JOptionPane.showMessageDialog(null, "Pilih kategori barang","gagal",2);
        }
        
    
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        // TODO add your handling code here:
        if(cbkategori.getSelectedIndex()!=0){
            String satuan;
            if(rbpcs.isSelected()){
                satuan = "pcs";
            }else if(rbbox.isSelected()){
                satuan = "box";
            }else if(rbrim.isSelected()){
                satuan = "rim";
            }else{
                satuan = null;
            }

            try{
                koneksi objkoneksi=new koneksi();
                Connection con=objkoneksi.bukakoneksi();
                String sql="UPDATE barang SET namabarang='"+txtnamabarang.getText()+"', kategori='"+cbkategori.getSelectedItem()+"',"
                        + " satuan='"+satuan+"', harga='"+txtharga.getText()+"', jumlah='"+txtjumlah.getText()+"' where kodebarang='"+txtkode.getText()+"' ";
                stat = con.createStatement();
                stat.executeUpdate(sql);

                javax.swing.JOptionPane.showMessageDialog(null, "Data Berhasil diubah");
                awal();
                view_data();
            }
            catch (Exception e)
            { 
                System.out.println("gagal="+e.getMessage());
            }
        }else{
            JOptionPane.showMessageDialog(null, "Pilih kategori barang","notifikasi",2);
        }
    }//GEN-LAST:event_btnUbahActionPerformed

    private void tblBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBarangMouseClicked
        // TODO add your handling code here:
        int row =tblBarang.getSelectedRow();
        
        
        
        
        
    }//GEN-LAST:event_tblBarangMouseClicked

    private void txthargaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txthargaKeyTyped
        // TODO add your handling code here:
        char cDigit = evt.getKeyChar();
        if(txtharga.getText().length()<10){
            if(!Character.isDigit(cDigit)){
                evt.consume();
            }
        }else{
            evt.consume();
        }
    }//GEN-LAST:event_txthargaKeyTyped

    private void txtjumlahKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtjumlahKeyTyped
        // TODO add your handling code here:
        char cDigit = evt.getKeyChar();
        if(txtjumlah.getText().length()<5){
            if(!Character.isDigit(cDigit)){
                evt.consume();
            }
        }else{
            evt.consume();
        }
    }//GEN-LAST:event_txtjumlahKeyTyped

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        // TODO add your handling code here:
        awal();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        try{
            koneksi objkoneksi=new koneksi();
            Connection con=objkoneksi.bukakoneksi();
            String sql="DELETE from barang where kodebarang='"+txtkode.getText()+"' ";
            stat = con.createStatement();
            stat.executeUpdate(sql);
            
            JOptionPane.showMessageDialog(null, "Data Berhasil dihapus");
            awal();
            view_data();
        }
        catch (Exception e)
        { 
            System.out.println("gagal="+e.getMessage());
        }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetakActionPerformed
        // TODO add your handling code here:
        cetak("./src/java2latihan/report/report1.jasper");
        
    }//GEN-LAST:event_btnCetakActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrameBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameBarang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnCetak;
    private javax.swing.ButtonGroup btnGroupSatuan;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnUbah;
    private javax.swing.JComboBox<String> cbkategori;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rbbox;
    private javax.swing.JRadioButton rbpcs;
    private javax.swing.JRadioButton rbrim;
    private javax.swing.JTable tblBarang;
    private javax.swing.JTextField txtharga;
    private javax.swing.JTextField txtjumlah;
    private javax.swing.JTextField txtkode;
    private javax.swing.JTextField txtnamabarang;
    // End of variables declaration//GEN-END:variables
}
