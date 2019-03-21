/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIs;

import DTO.Post;
import ServerAndClient.ForumClient;
import java.io.File;
import java.sql.Date;
import java.time.Year;
import java.util.Calendar;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Absalom Herrera
 */
public class CreatePostGUI extends javax.swing.JFrame {

    private String user;
    private File img = null;
    private ClientMainGUI2 cmg;

    /**
     * Creates new form CreatePostGUI
     */
    public CreatePostGUI(String user, ClientMainGUI2 cmg) {
        this.user = user;
        this.cmg = cmg;
        // TODO: Style, Non empty fields, image attachment and no image recognition, cancel button, date
        // TODO2: letting know client that the post have been added, exiting this GUI back to main
        initComponents();
        this.setLocationRelativeTo(null);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
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

        titleLabel = new javax.swing.JLabel();
        msgLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        msgTextArea = new javax.swing.JTextArea();
        topicLabel = new javax.swing.JLabel();
        titleTextField = new javax.swing.JTextField();
        topicTextField = new javax.swing.JTextField();
        postButton = new javax.swing.JToggleButton();
        attachButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        titleLabel.setText("Title:");

        msgLabel.setText("Post:");

        msgTextArea.setColumns(20);
        msgTextArea.setRows(5);
        jScrollPane1.setViewportView(msgTextArea);

        topicLabel.setText("Topic:");

        postButton.setText("Post");
        postButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                postButtonActionPerformed(evt);
            }
        });

        attachButton.setText("Attach Image");
        attachButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                attachButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(topicLabel)
                                .addGap(18, 18, 18)
                                .addComponent(topicTextField))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(msgLabel)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(titleLabel)
                                .addGap(18, 18, 18)
                                .addComponent(titleTextField))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(171, 171, 171)
                        .addComponent(postButton)))
                .addContainerGap(31, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(attachButton)
                .addGap(146, 146, 146))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titleLabel)
                    .addComponent(titleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(msgLabel)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(topicLabel)
                    .addComponent(topicTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(attachButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(postButton)
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void postButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_postButtonActionPerformed
        Post p = new Post();
        p.setTitle(titleTextField.getText());
        p.setMessage(msgTextArea.getText());
        p.setTopic(topicTextField.getText());
        p.setUser(user);
        
        if (img != null){
            p.setPath_img(img.getPath());
        }

        
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DATE);
        //int year = Year.now().getValue();
        
        
        Date d = new Date(day, month, year);
        
        System.err.println(d.toString());
        
        ForumClient fc = new ForumClient();
        fc.createPost(p);
        cmg.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_postButtonActionPerformed

    private void attachButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_attachButtonActionPerformed
        JFileChooser jfc;
        FileFilter imageFilter = new FileNameExtensionFilter(
                "Image files", ImageIO.getReaderFileSuffixes());
        jfc = new JFileChooser();
        jfc.addChoosableFileFilter(imageFilter);
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (jfc.isMultiSelectionEnabled()) {
            jfc.setMultiSelectionEnabled(false);
        }
        int r = jfc.showOpenDialog(null);
        if (r == JFileChooser.APPROVE_OPTION) {
            img = jfc.getSelectedFile();
        }
    }//GEN-LAST:event_attachButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton attachButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel msgLabel;
    private javax.swing.JTextArea msgTextArea;
    private javax.swing.JToggleButton postButton;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JTextField titleTextField;
    private javax.swing.JLabel topicLabel;
    private javax.swing.JTextField topicTextField;
    // End of variables declaration//GEN-END:variables
}
