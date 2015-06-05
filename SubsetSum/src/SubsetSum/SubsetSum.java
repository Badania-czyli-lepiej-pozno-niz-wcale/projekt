package SubsetSum;

public class SubsetSum extends javax.swing.JFrame {

    private boolean fileFlag;
    SubsetSumHandler helper;
            
    public SubsetSum() {
        helper=new SubsetSumHandler();
        this.setLocationRelativeTo(null);
        initComponents();
        System.out.println("do wykresu");
        for (Double i : helper.doubleResults)
            System.out.println(i+" ");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loadGeneratedSet = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        loadFileButton = new javax.swing.JButton();
        findSolution = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        loadGeneratedSet.setText("Load generated set");
        loadGeneratedSet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadGeneratedSetActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Subset sum problem");

        loadFileButton.setText("Load file set");
        loadFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadFileButtonActionPerformed(evt);
            }
        });

        findSolution.setText("Start");
        findSolution.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findSolutionActionPerformed(evt);
            }
        });

        jButton1.setText("Documentation");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(55, 55, 55)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(loadGeneratedSet, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(loadFileButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .add(49, 49, 49)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(findSolution, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                    .add(jButton1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(60, Short.MAX_VALUE))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(20, 20, 20)
                .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 49, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(31, 31, 31)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(loadFileButton)
                    .add(findSolution))
                .add(18, 18, 18)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(loadGeneratedSet)
                    .add(jButton1))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

        boolean dataFlag;
    private void loadGeneratedSetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadGeneratedSetActionPerformed
        fileFlag=false;
        dataFlag=true;
        helper.openGeneratedSettings();   
    }//GEN-LAST:event_loadGeneratedSetActionPerformed

    private void findSolutionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findSolutionActionPerformed
        if(helper.doubleResults!=null){
            helper.doubleResults.clear();
        }
        if(!dataFlag){
            helper.noData(this);
        }
        else{
            helper.confirmSettings(fileFlag);
            helper.findSolution();
            helper.drawPlot();
            System.out.println("SIZE IN: "+helper.inputNumbers.size());
            System.out.println("SIZE DR: "+helper.doubleResults.size());
        }
        helper.inputNumbers.clear();
        
        
    }//GEN-LAST:event_findSolutionActionPerformed
     
    private void loadFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadFileButtonActionPerformed
        fileFlag=true;
        dataFlag=true;
        helper.readFile(this);
        helper.openFileSettings();
    }//GEN-LAST:event_loadFileButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        helper.showDocumentation(); 
    }//GEN-LAST:event_jButton1ActionPerformed
   
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SubsetSum().setVisible(true);
            }
        });
        
    }   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton findSolution;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton loadFileButton;
    private javax.swing.JButton loadGeneratedSet;
    // End of variables declaration//GEN-END:variables

}
