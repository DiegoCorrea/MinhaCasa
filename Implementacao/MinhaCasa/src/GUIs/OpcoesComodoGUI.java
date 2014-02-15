package GUIs;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.Aparelho.Aparelho;
import Controlador.Comodo.Comodo;
import Controlador.Janela.Janela;
import Controlador.ObjetoCasa.ObjetoCasa;
import Controlador.Porta.Porta;
import DbHandler.DbHandler;

public class OpcoesComodoGUI extends JFrame {

	private JPanel contentPane;
	Comodo comodo;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI frame = new MainGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public OpcoesComodoGUI(Comodo c) {
		comodo = c;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1091, 101);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(6, 6, 6, 6));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton verJanelas = new JButton("Ver Janelas");
		verJanelas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ComodosGUI v = new ComodosGUI(comodo,"janelas");
				v.show();
			}
		});
		contentPane.add(verJanelas);
		
		JButton verPortas = new JButton("Ver Portas");
		verPortas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ComodosGUI v = new ComodosGUI(comodo,"portas");
				v.show();
			}
		});
		contentPane.add(verPortas);

		JButton verAparelhos = new JButton("Ver Aparelhos");
		verAparelhos.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ComodosGUI v = new ComodosGUI(comodo,"aparelhos");
				v.show();
			}
		});
		contentPane.add(verAparelhos);
		
		JButton btnDestravarPorta = new JButton("Relatório de Portas");
		btnDestravarPorta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comodo!=null && comodo.getPortas()!=null){
					System.out.println("----------------------Relatório PORTAS------------------------");
					String text;
					for(int i=0;i<comodo.getPortas().size();i++){
						text = "Porta: "+comodo.getPortas().get(i).getNome();
						if(comodo.getPortas().get(i).getTravada())
							text +=" - Travada";
						else
							text +=" - Destravada";
						System.out.println(text);
					}
					System.out.println("--------------------------------------------------------------");
				}	
			}
		});
		contentPane.add(btnDestravarPorta);
		
		JButton btnDestravarJanela = new JButton("Relatório de janelas");
		btnDestravarJanela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comodo!=null && comodo.getJanelas()!=null){
					System.out.println("----------------------Relatório JANELAS------------------------");
					String text;
					for(int i=0;i<comodo.getJanelas().size();i++){
						text = "Janela: "+comodo.getJanelas().get(i).getNome();
						if(comodo.getPortas().get(i).getTravada())
							text +=" - Travada";
						else
							text +=" - Destravada";
						System.out.println(text);
					}
					System.out.println("---------------------------------------------------------------");
				}
			}
		});
		contentPane.add(btnDestravarJanela);
		
		if(MinhaCasa.admin.isAdmin()){
		JButton btnRelatorioConsumo = new JButton("Relatório economico");
		btnRelatorioConsumo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("------------------------------Relatório de CONSUMO----------------------------------");
				if(MinhaCasa.modoec)
					System.out.println("Casa em modo econômico");
				else
					System.out.println("Casa em modo normal");
				for(int i=0;i<comodo.getAparelhos().size();i++){
					
					System.out.println("Aparelho: "+comodo.getAparelhos().get(i).getNome()+"Consumo: "+comodo.getAparelhos().get(i).getConsumo());
				}
				System.out.println("------------------------------------------------------------------------------------");
			}
		});
		contentPane.add(btnRelatorioConsumo);
		}
		
		if(MinhaCasa.admin.isAdmin()){
		JButton btnRelatorioDur = new JButton("Relatório de durabilidade");
		btnRelatorioDur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("------------------------------Relatório DE DURABILIDADE----------------------------------");
				for(int i=0;i<comodo.getAparelhos().size();i++){
					System.out.println("Aparelho: "+comodo.getAparelhos().get(i).getNome()+" - Durabilidade: "+comodo.getAparelhos().get(i).getDurabilidade());
				}
				System.out.println("-----------------------------------------------------------------------------------------");
			}
		});
		contentPane.add(btnRelatorioDur);
		}
		
		JButton btnAddAparelho = new JButton("Adicionar Aparelho");
		btnAddAparelho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nm = getValue();
				if(nm != null){
					comodo.getAparelhos().add(new Aparelho(nm));
					DbHandler.atualizarDb();
				}
			}
		});
		contentPane.add(btnAddAparelho);
		
		if(MinhaCasa.admin.isAdmin()){
			JButton btnAlertarMalFunc = new JButton("Alertar mal funcionamento");
			btnAlertarMalFunc.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int leng = comodo.getAparelhos().size()+comodo.getJanelas().size()+comodo.getPortas().size();
					ObjetoCasa[] options = new ObjetoCasa[leng];
					int i = 0;
					for (Janela j : comodo.getJanelas()) {
						options[i] = j;
						i++;
					}
					for (Porta p : comodo.getPortas()) {
						options[i] = p;
						i++;
					}
					for (Aparelho a : comodo.getAparelhos()) {
						options[i] = a;
						i++;
					}
					ObjetoCasa nm = (ObjetoCasa) getValue3(options);
					
					if(nm!=null){
						ObjetoCasa.alertarDefeito(nm);
					}
				}
			});
			contentPane.add(btnAlertarMalFunc);
		}
	}
	
    private String getValue(){
        return JOptionPane.showInputDialog(
            this,
            "Digite o nome:",
            "",
            JOptionPane.PLAIN_MESSAGE);
    }
    
    private Object getValue2(String[] options,String str){
    	return JOptionPane.showInputDialog(null, "Escolha uma "+str, "Filtro",
    	        JOptionPane.QUESTION_MESSAGE, null, options , null);
    }
    private Object getValue3(ObjetoCasa[] options){
    	return JOptionPane.showInputDialog(null, "Selecione o elemento", "Elemento defeituoso",
    	        JOptionPane.QUESTION_MESSAGE, null, options , null);
    }
    
}