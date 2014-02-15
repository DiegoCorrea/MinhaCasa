package GUIs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controlador.Agendador.Agendador;
import Controlador.Janela.Janela;
import Controlador.Porta.Porta;
import DbHandler.DbHandler;
import GUIs.MinhaCasa.Acoes;

public class AgendamentoSaidas{
	ArrayList<JTextField> list = new ArrayList<JTextField>();
	private Object o;
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private Acoes opcao;
	
	public AgendamentoSaidas(Object b,Acoes opcao) {
		o = b;
		this.opcao = opcao;
		initialize();
	}
	
	String textFromOption(){
		switch(opcao){
		case TRAVAR:
			return "Travar";
		case DESTRAVAR:
			return "Destravar";
		case FECHAR:
			return "Fechar";
		case ABRIR:
			return "Abrir";
		}
		return "";
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 288, 298);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setPreferredSize(new Dimension(500, 110));
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Agendamento");
		frame.getContentPane().add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnAgora = new JButton(textFromOption()+" agora");
		btnAgora.setBounds(65, 195, 137, 23);
		panel.add(btnAgora);
		
		JButton btnAgendar = new JButton("Agendar");
		btnAgendar.setBounds(65, 127, 137, 23);
		panel.add(btnAgendar);
		
		textField = new JTextField();
		textField.setBounds(65, 45, 137, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(65, 91, 137, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblParaAgendarPreencha = new JLabel("Para agendar, preencha os campos abaixo. ");
		lblParaAgendarPreencha.setBounds(10, 11, 252, 14);
		panel.add(lblParaAgendarPreencha);
		
		final JLabel lblData = new JLabel("Data:");
		lblData.setBounds(9, 48, 46, 14);
		panel.add(lblData);
		
		final JLabel lblHorario = new JLabel("Hor\u00E1rio:");
		lblHorario.setBounds(10, 94, 46, 14);
		panel.add(lblHorario);
		
		JLabel lblParaAoImediata = new JLabel("Para a\u00E7\u00E3o imediata:");
		lblParaAoImediata.setBounds(10, 163, 122, 14);
		panel.add(lblParaAoImediata);
		
		btnAgendar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
					try {
						Date date = format.parse(lblData.getText()+" "+lblHorario.getText());
						Agendador.agendar(o, date,textFromOption());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		});
		
		btnAgora.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				realizarAcao();
			}
		});
	}
	
	protected void realizarAcao() {
		switch(opcao){
		case TRAVAR:
			if(o instanceof Porta)	
				((Porta)o).travar();	
			else ((Janela)o).travar();
			break;
		case DESTRAVAR:
			if(o instanceof Porta)	
				((Porta)o).destravar();	
			else ((Janela)o).destravar();
			break;
		case FECHAR:
			if(o instanceof Porta)	
			((Porta)o).fechar();	
		else ((Janela)o).fechar();
			break;
		case ABRIR:
		if(o instanceof Porta)	
			((Porta)o).abrir();	
		else ((Janela)o).abrir();
		}
		DbHandler.atualizarDb();
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
