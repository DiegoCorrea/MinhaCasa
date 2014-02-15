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

import Controlador.Comodo.Comodo;
import GUIs.MinhaCasa.Acoes;

public class ComodosGUI{
	Comodo c;
	ArrayList<JTextField> list = new ArrayList<JTextField>();
	int currentElem;
	
	private JFrame frame;

	public ComodosGUI(Comodo c, String type) {
		this.c = c;
		initialize(type);
	}
	
	public ComodosGUI(Comodo c) {
		this.c = c;
		initialize(null);
	}

	private void initialize(String type) {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setPreferredSize(new Dimension(500, 110));
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Cômodo: "+c.getNome());
		frame.getContentPane().add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		JButton btnTravar;
		JButton btnDestravar;
		JButton btnFechar;
		JButton btnAbrir;
		JButton btnRemove;
		JLabel label;
		String lbTitle = "Lista de ";
		if(type == null || type.equals("aparelhos")){
			lbTitle += "aparelhos";
			for(int i=0;i<c.getAparelhos().size();i++){
				label = new JLabel(c.getAparelhos().get(i).getNome());
				label.setBounds(162, 38 + i*27, 136, 14);
				panel.add(label,BorderLayout.CENTER);
		
		//			btnRemove = new JButton("Remover");
		//			btnRemove.setBounds(52, 35 + i*27, 100, 20);
		//			panel.add(btnRemove,BorderLayout.CENTER);
			}
		}else if(type.equals("portas")){
			lbTitle += "portas";
			for(int i=0;i<c.getPortas().size();i++){
				
				label = new JLabel(c.getPortas().get(i).getNome());
				label.setBounds(400, 38 + i*27, 136, 14);
				panel.add(label,BorderLayout.CENTER);
				
				btnAbrir = new JButton("Abrir");
				btnAbrir.setBounds(312, 35 + i*27, 80, 20);
				panel.add(btnAbrir,BorderLayout.CENTER);
				currentElem = i;
				btnAbrir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						(new AgendamentoSaidas(c.getPortas().get(currentElem),Acoes.ABRIR)).show();
						
					}
				});
				
				btnFechar = new JButton("Fechar");
				btnFechar.setBounds(232, 35 + i*27, 80, 20);
				panel.add(btnFechar,BorderLayout.CENTER);
				btnFechar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						(new AgendamentoSaidas(c.getPortas().get(currentElem),Acoes.FECHAR)).show();
					}
				});

				btnDestravar = new JButton("Destravar");
				btnDestravar.setBounds(132, 35 + i*27, 100, 20);
				panel.add(btnDestravar,BorderLayout.CENTER);
				btnDestravar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						(new AgendamentoSaidas(c.getPortas().get(currentElem),Acoes.DESTRAVAR)).show();
					}
				});
				
				btnTravar = new JButton("Travar");
				btnTravar.setBounds(52, 35 + i*27, 80, 20);
				panel.add(btnTravar,BorderLayout.CENTER);
				btnTravar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						(new AgendamentoSaidas(c.getPortas().get(currentElem),Acoes.TRAVAR)).show();
					}
				});
			}
		}else{//janela
			lbTitle += "janelas";
			for(int i=0;i<c.getJanelas().size();i++){

				label = new JLabel(c.getJanelas().get(i).getNome());
				label.setBounds(400, 38 + i*27, 136, 14);
				panel.add(label,BorderLayout.CENTER);
				currentElem = i;
				
				btnAbrir = new JButton("Abrir");
				btnAbrir.setBounds(312, 35 + i*27, 80, 20);
				panel.add(btnAbrir,BorderLayout.CENTER);
				btnAbrir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						(new AgendamentoSaidas(c.getJanelas().get(currentElem),Acoes.ABRIR)).show();
					}
				});
				
				btnFechar = new JButton("Fechar");
				btnFechar.setBounds(232, 35 + i*27, 80, 20);
				panel.add(btnFechar,BorderLayout.CENTER);
				btnFechar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						(new AgendamentoSaidas(c.getJanelas().get(currentElem),Acoes.FECHAR)).show();
					}
				});
				
				btnDestravar = new JButton("Destravar");
				btnDestravar.setBounds(132, 35 + i*27, 100, 20);
				panel.add(btnDestravar,BorderLayout.CENTER);
				btnDestravar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						(new AgendamentoSaidas(c.getJanelas().get(currentElem),Acoes.DESTRAVAR)).show();
					}
				});
				
				
				btnTravar = new JButton("Travar");
				btnTravar.setBounds(52, 35 + i*27, 80, 20);
				panel.add(btnTravar,BorderLayout.CENTER);
				btnTravar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						(new AgendamentoSaidas(c.getJanelas().get(currentElem),Acoes.TRAVAR)).show();
					}
				});
				
			}
		}
		
		JLabel lblListaDeCmodos = new JLabel(lbTitle);
		lblListaDeCmodos.setBounds(10, 11, 321, 14);
		panel.add(lblListaDeCmodos);
		
		
		frame.addWindowListener(new	WindowAdapter() {
			public void windowClosing(WindowEvent e){
			}
		} );
		
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
