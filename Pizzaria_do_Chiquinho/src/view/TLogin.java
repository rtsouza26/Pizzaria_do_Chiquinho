package view;

import javax.swing.JOptionPane;
import conexaoBD.LoginBD;
import principal.Funcionarios;

public class TLogin extends javax.swing.JFrame  {
	
	private javax.swing.JButton jBcancela;
	private javax.swing.JButton jBok;
	private javax.swing.JTextField jFlogin;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLogin;
	private javax.swing.JPasswordField jPFsenha;
	private javax.swing.JLabel jSenha;

	public TLogin() {
        initComponents();
    }
	
	private void initComponents() {

	        jPFsenha = new javax.swing.JPasswordField();
	        jFlogin = new javax.swing.JTextField();
	        jLogin = new javax.swing.JLabel();
	        jSenha = new javax.swing.JLabel();
	        jBok = new javax.swing.JButton();
	        jBcancela = new javax.swing.JButton();
	        jLabel1 = new javax.swing.JLabel();

	        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	        setTitle("Pizzaria do Chiquinho");
	        setBackground(java.awt.SystemColor.textHighlight);

	        jLogin.setFont(new java.awt.Font("African", 1, 24)); // NOI18N
	        jLogin.setText("Login");

	        jSenha.setFont(new java.awt.Font("African", 1, 24)); // NOI18N
	        jSenha.setText("Senha");

	        jBok.setText("OK");
	        jBok.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	jBokActionPerformed(evt);
	            }
	        });

	        jBcancela.setText("Cancelar");
	        jBcancela.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                jBcancelaActionPerformed(evt);
	            }
	        });

	        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Chiquinho.jpg"))); // NOI18N

	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jLabel1)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                        .addComponent(jFlogin, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addGap(23, 23, 23))
	                    .addGroup(layout.createSequentialGroup()
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                            .addGroup(layout.createSequentialGroup()
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                                .addComponent(jPFsenha, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
	                            .addGroup(layout.createSequentialGroup()
	                                .addGap(18, 18, 18)
	                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                    .addComponent(jSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                    .addComponent(jLogin)
	                                    .addGroup(layout.createSequentialGroup()
	                                        .addComponent(jBcancela)
	                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
	                                        .addComponent(jBok, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))))
	                        .addContainerGap(22, Short.MAX_VALUE))))
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addGap(23, 23, 23)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(layout.createSequentialGroup()
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addGroup(layout.createSequentialGroup()
	                                .addGap(36, 36, 36)
	                                .addComponent(jFlogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                            .addComponent(jLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
	                        .addGap(18, 18, 18)
	                        .addComponent(jSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addComponent(jPFsenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addGap(25, 25, 25)
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                            .addComponent(jBcancela)
	                            .addComponent(jBok)))
	                    .addComponent(jLabel1))
	                .addContainerGap(23, Short.MAX_VALUE))
	        );

	        pack();
	    }// </editor-fold>                        
	
	
	private void jBokActionPerformed(java.awt.event.ActionEvent evt) {                                     
        LoginBD funcbd= new LoginBD();
        Funcionarios funcheck = null;
       
		
		if((funcheck=funcbd.checkLogin(jFlogin.getText(),String.valueOf(jPFsenha.getPassword())))!=null){
         // new Principal().setVisible(true);
			this.dispose();
			if(funcheck.getTipo().equals("Administrador")){
				JOptionPane.showMessageDialog(null,"Login efetivado com sucesso como Administrador");
			}if(funcheck.getTipo().equals("Atendente")){
				JOptionPane.showMessageDialog(null,"Login efetivado com sucesso como Atendente");
			}if (funcheck.getTipo().equals("Cozinha")){
				JOptionPane.showMessageDialog(null,"Login efetivado com sucesso como Cozinha");
			}
			
			
		}else{
			JOptionPane.showMessageDialog(null,"Login ou Senha errados");
		}
        
    }   
	
	private void jBcancelaActionPerformed(java.awt.event.ActionEvent evt) {                                          
	        // TODO add your handling code here:
	        
	        System.exit(0);
	    }   
	
	
	
}
