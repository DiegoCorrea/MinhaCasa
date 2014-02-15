package GUIs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Controlador.Usuarios.User;

public class UsuarioGUI{
	ArrayList<JTextField> list = new ArrayList<JTextField>();
	private JFrame frame;
	private JTextField username;
	private JPasswordField password;
	
	public UsuarioGUI() {
		initialize();
	}
	

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 288, 298);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setPreferredSize(new Dimension(500, 110));
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Acesso ao sistema");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setBounds(65, 139, 137, 23);
		panel.add(btnEntrar);
		
		username = new JTextField();
		username.setBounds(65, 45, 137, 20);
		panel.add(username);
		username.setColumns(10);
		
		password = new JPasswordField();
		password.setBounds(65, 91, 137, 20);
		panel.add(password);
		password.setColumns(10);
		
		final JLabel lblUsuario = new JLabel("Usuário:");
		lblUsuario.setBounds(9, 48, 53, 14);
		panel.add(lblUsuario);
		
		final JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(10, 94, 46, 14);
		panel.add(lblSenha);
		
		final JLabel lblInvalido = new JLabel("Usu\u00E1rio ou senha inv\u00E1lidos");
		lblInvalido.setForeground(Color.RED);
		lblInvalido.setBounds(65, 191, 193, 14);
		panel.add(lblInvalido);
		lblInvalido.setVisible(false);
		
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblInvalido.setVisible(false);					
				//se contem no banco
				if(username.getText().equals("admin") && password.getText().equals("admin")){
					try {
						MinhaCasa.admin = new User();
						MinhaCasa.admin.setAdmin(true);
						MinhaCasa.start();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				//fecha e ativa visualização
					dispose();
				}else if(username.getText().equals("user1") && username.getText().equals("user1")){
					try {
						MinhaCasa.admin = new User();
						MinhaCasa.start();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				//fecha e ativa visualização
					dispose();
				} else{
					lblInvalido.setVisible(true);
				}
				//se não, avisa login inválido
			}
		});
	}

	public void show(){
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void dispose(){
		frame.dispose();
	}
}
