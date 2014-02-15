package GUIs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controlador.Casa.Casa;

public class CasaGUI{
	Casa casa;
	ArrayList<JTextField> list = new ArrayList<JTextField>();
	
	private JFrame frame;

	public CasaGUI(Casa agent) {
		this.casa = agent;
		initialize();
	}
	
	public void show(){
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 357, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setPreferredSize(new Dimension(450, 110));
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Casa: "+casa.getNome());
		frame.getContentPane().add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel label;
		for(int i=0;i<casa.getComodos().size();i++){
			label = new JLabel(casa.getComodos().get(i).getNome());
			label.setBounds(162, 38 + i*27, 136, 14);
			panel.add(label,BorderLayout.CENTER);

//			btnRemove = new JButton("Remover");
//			btnRemove.setBounds(52, 35 + i*27, 100, 20);
//			panel.add(btnRemove,BorderLayout.CENTER);
		}
		
		JButton btnSet = new JButton("Remover Casa");
		btnSet.setBounds(113, 196, 118, 23);
		panel.add(btnSet);
		
		JLabel lblListaDeCmodos = new JLabel("Lista de c\u00F4modos:");
		lblListaDeCmodos.setBounds(10, 11, 106, 14);
		panel.add(lblListaDeCmodos);
		
		btnSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MinhaCasa.removerCasa();
				dispose();
			}
		});
		
		frame.addWindowListener(new	WindowAdapter() {
			public void windowClosing(WindowEvent e){
			}
		} );
	}
	
	public void dispose(){
		frame.dispose();
	}
}
