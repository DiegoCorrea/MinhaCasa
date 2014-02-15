package GUIs;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.db4o.ObjectSet;

import Controlador.Aparelho.Aparelho;
import Controlador.Casa.Casa;
import Controlador.Comodo.Comodo;
import Controlador.Janela.Janela;
import Controlador.Porta.Porta;
import DbHandler.DbHandler;

public class MainGUI extends JFrame {
	private JPanel contentPane;
	Casa casa;

	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { MainGUI frame = new MainGUI();
	 * frame.setVisible(true); } catch (Exception e) { e.printStackTrace(); } }
	 * }); }
	 */

	/**
	 * Create the frame.
	 */
	public String path() {
		URL location = MainGUI.class.getProtectionDomain().getCodeSource()
				.getLocation();
		return ((location.getFile()).toString().replaceFirst("bin/", ""))
				.replaceFirst("/", "");
	}

	public MainGUI(Casa c) throws IOException {
		casa = c;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 780, 600);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(6, 6, 6, 6));
		setContentPane(contentPane);

		String base = path();
		BufferedImage myPicture = ImageIO.read(new File("./images/casa_3.jpg"));

		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 135, 135, 135, 0, 135, 0, 0, 135,
				10, 30, 90, 135, 135, 0 };
		gbl_contentPane.rowHeights = new int[] { 100, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

				JButton btnAdicionarCasa = new JButton("Iniciar Casa");
				btnAdicionarCasa.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ObjectSet<Casa> cs = DbHandler.db.queryByExample(new Casa("casa"));
						if(cs == null || cs.isEmpty()){
							String nm = getValue();
							if (nm != null){
								casa = new Casa(nm);
								DbHandler.db.store(casa);
								MinhaCasa.casa = casa;
							}
						}else{
							casa = cs.get(0);
							MinhaCasa.casa = casa;
						}
					}
				});
				GridBagConstraints gbc_btnAdicionarCasa = new GridBagConstraints();
				gbc_btnAdicionarCasa.fill = GridBagConstraints.BOTH;
				gbc_btnAdicionarCasa.insets = new Insets(0, 0, 5, 5);
				gbc_btnAdicionarCasa.gridx = 3;
				gbc_btnAdicionarCasa.gridy = 0;
				contentPane.add(btnAdicionarCasa, gbc_btnAdicionarCasa);

				JButton btnDetalharComodos = new JButton("Detalhar Comodos");
				btnDetalharComodos.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (casa != null && casa.getComodos() != null
								&& !casa.getComodos().isEmpty()) {
							String[] options = new String[casa.getComodos().size()];
							int i = 0;
							for (Comodo c : casa.getComodos()) {
								options[i] = c.getNome();
								i++;
							}
							Object nm = getValue2(options);
							if (nm != null) {
								OpcoesComodoGUI v = new OpcoesComodoGUI(casa
										.getComodo((String) nm));
								v.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
								v.getContentPane().add(new JPanel());
								v.pack();
								v.setVisible(true);
							}
						}
					}
				});

						GridBagConstraints gbc_btnDetalharComodos = new GridBagConstraints();
						gbc_btnDetalharComodos.fill = GridBagConstraints.BOTH;
						gbc_btnDetalharComodos.insets = new Insets(0, 0, 5, 5);
						gbc_btnDetalharComodos.gridx = 4;
						gbc_btnDetalharComodos.gridy = 0;
						contentPane.add(btnDetalharComodos, gbc_btnDetalharComodos);

						JButton btnDetalharCasa = new JButton("Detalhar Casa");
						btnDetalharCasa.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if (casa != null) {
									CasaGUI v = new CasaGUI(casa);
									v.show();
								}
								// casa = null;
							}
						});
						GridBagConstraints gbc_btnDetalharCasa = new GridBagConstraints();
						gbc_btnDetalharCasa.fill = GridBagConstraints.BOTH;
						gbc_btnDetalharCasa.insets = new Insets(0, 0, 5, 5);
						gbc_btnDetalharCasa.gridx = 5;
						gbc_btnDetalharCasa.gridy = 0;
						contentPane.add(btnDetalharCasa, gbc_btnDetalharCasa);

				JButton btnAddComodo = new JButton("Adicionar Comodo");
				btnAddComodo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (casa != null && casa.getComodos() != null) {
							String nm = getValue();
							if(nm != null){
								casa.getComodos().add(new Comodo(nm));
								DbHandler.atualizarDb();
							}
						}
					}
				});
				if(MinhaCasa.admin.isAdmin()){
								JButton btnModoEconomico = new JButton("Modo economico");
								btnModoEconomico.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										if (MinhaCasa.modoec) {
											System.out.println("Modo econômico ativado");
											MinhaCasa.modoec = false;
										} else {
											System.out.println("Modo econômico desativado");
											MinhaCasa.modoec = true;
										}
									}
								});
								GridBagConstraints gbc_btnModoEconomico = new GridBagConstraints();
								gbc_btnModoEconomico.fill = GridBagConstraints.BOTH;
								gbc_btnModoEconomico.insets = new Insets(0, 0, 5, 5);
								gbc_btnModoEconomico.gridx = 6;
								gbc_btnModoEconomico.gridy = 0;
								contentPane.add(btnModoEconomico, gbc_btnModoEconomico);
				}
					if(MinhaCasa.admin.isAdmin()){
						JButton btnModoSeguranca = new JButton("Modo de Segurança");
						btnModoSeguranca.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if (casa != null)
									if (!MinhaCasa.modoSeg) {
										MinhaCasa.modoSeg = true;
										System.out.println("Ativando modo de segurança");
										for (Comodo c : casa.getComodos()) {
											for (Porta p : c.getPortas()) {
												p.travar();
											}
											for (Janela j : c.getJanelas()) {
												j.travar();
											}
											for (Aparelho a : c.getAparelhos()) {
												a.desligar();
											}
										}
										System.out.println("Portas trancadas");
										System.out.println("Janelas trancadas");
										System.out.println("Aparelhos desligados");
										System.out.println("Modo de segurança ativado");
										DbHandler.atualizarDb();
									} else {
										MinhaCasa.modoSeg = false;
										System.out.println("Modo de segurança desativado");
									}
							}
						});
						GridBagConstraints gbc_btnModoSeguranca = new GridBagConstraints();
						gbc_btnModoSeguranca.fill = GridBagConstraints.BOTH;
						gbc_btnModoSeguranca.insets = new Insets(0, 0, 5, 5);
						gbc_btnModoSeguranca.gridx = 7;
						gbc_btnModoSeguranca.gridy = 0;
						contentPane.add(btnModoSeguranca, gbc_btnModoSeguranca);
				}

				GridBagConstraints gbc_btnAddComodo = new GridBagConstraints();
				gbc_btnAddComodo.gridheight = 3;
				gbc_btnAddComodo.fill = GridBagConstraints.BOTH;
				gbc_btnAddComodo.insets = new Insets(0, 0, 5, 5);
				gbc_btnAddComodo.gridx = 3;
				gbc_btnAddComodo.gridy = 2;
				contentPane.add(btnAddComodo, gbc_btnAddComodo);

				JButton btnAddJanela = new JButton("Adicionar Janela");
				btnAddJanela.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (casa != null && casa.getComodos() != null) {
							String[] options = new String[casa.getComodos().size()];
							int i = 0;
							for (Comodo c1 : casa.getComodos()) {
								options[i] = c1.getNome();
								i++;
							}
							Object j = getValue2(options);
							if (j != null){
								casa.getComodo((String) j).addJanela(
										new Janela(getValue()));
								DbHandler.atualizarDb();
							}
						}
					}
				});
				GridBagConstraints gbc_btnAddJanela = new GridBagConstraints();
				gbc_btnAddJanela.gridheight = 3;
				gbc_btnAddJanela.fill = GridBagConstraints.BOTH;
				gbc_btnAddJanela.insets = new Insets(0, 0, 5, 5);
				gbc_btnAddJanela.gridx = 4;
				gbc_btnAddJanela.gridy = 2;
				contentPane.add(btnAddJanela, gbc_btnAddJanela);

						JButton btnAddPorta = new JButton("Adicionar Porta");
						btnAddPorta.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if (casa != null && casa.getComodos() != null) {
									String[] options = new String[casa.getComodos().size()];
									int i = 0;
									for (Comodo c : casa.getComodos()) {
										options[i] = c.getNome();
										i++;
									}
									Object p = getValue2(options);
									if (p != null){
										casa.getComodo((String) p).addPorta(
												new Porta(getValue()));
										DbHandler.atualizarDb();
									}

								}
							}
						});
						GridBagConstraints gbc_btnAddPorta = new GridBagConstraints();
						gbc_btnAddPorta.gridheight = 3;
						gbc_btnAddPorta.fill = GridBagConstraints.BOTH;
						gbc_btnAddPorta.insets = new Insets(0, 0, 5, 5);
						gbc_btnAddPorta.gridx = 5;
						gbc_btnAddPorta.gridy = 2;
						contentPane.add(btnAddPorta, gbc_btnAddPorta);

				if(MinhaCasa.admin.isAdmin()){
				JButton btnVerMensagens = new JButton("VerMensagens");
				btnVerMensagens.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (casa != null && MinhaCasa.admin != null) {
							for (String msg : MinhaCasa.admin.getMsgs())
								System.out.println(msg);
						}
					}
				});
				GridBagConstraints gbc_btnVerMensagens = new GridBagConstraints();
				gbc_btnVerMensagens.gridwidth = 2;
				gbc_btnVerMensagens.gridheight = 3;
				gbc_btnVerMensagens.insets = new Insets(0, 0, 5, 5);
				gbc_btnVerMensagens.fill = GridBagConstraints.BOTH;
				gbc_btnVerMensagens.gridx = 6;
				gbc_btnVerMensagens.gridy = 2;
				contentPane.add(btnVerMensagens, gbc_btnVerMensagens);
				}
		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		GridBagConstraints gbc_picLabel = new GridBagConstraints();
		gbc_picLabel.gridwidth = 5;
		gbc_picLabel.fill = GridBagConstraints.BOTH;
		gbc_picLabel.insets = new Insets(0, 0, 0, 5);
		gbc_picLabel.gridx = 3;
		gbc_picLabel.gridy = 6;
		getContentPane().add(picLabel, gbc_picLabel);

	}

	private String getValue() {
		return JOptionPane.showInputDialog(this, "Digite o nome:", "",
				JOptionPane.PLAIN_MESSAGE);
	}

	private Object getValue2(String[] options) {
		return JOptionPane.showInputDialog(null, "Escolha um comodo", "Filtro",
				JOptionPane.QUESTION_MESSAGE, null, options, null);
	}

}
