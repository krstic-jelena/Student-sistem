package StudentSistem;

import java.sql.*;
import javax.swing.*;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.BorderLayout;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Zaposleni {

	public JFrame frame;
	private JComboBox comboBoxSortiranje;
	private JTextField txtTelefon;
	private JTextField txtIme;
	private JTextField txtPrezime;
	private JTextField txtKatedra_id;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Zaposleni window = new Zaposleni();
					window.frame.setVisible(false);
					
					smslog window1 = new smslog();
					smslog.main(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
Connection connection=null;
	
	private JTable tableZaposleni;
	private JTextField txtZaposleni_id;
	public void refreshTable()
	{
		
		try{ 
			String query="select * from Zaposleni";
		
		PreparedStatement pst = connection.prepareStatement(query);
		ResultSet rs = pst.executeQuery();
		
		tableZaposleni.setModel(DbUtils.resultSetToTableModel(rs));
		
	}
	catch(Exception ex) {
		ex.printStackTrace();	}
	}
	public void clearFields()
	{
		txtTelefon.setText(null);
		txtIme.setText(null);
		txtPrezime.setText(null);
		txtKatedra_id.setText(null);
		txtZaposleni_id.setText(null);
	}
	public Zaposleni() {
		connection= Connect.java_db();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 204, 102));
		frame.setBounds(100, 100, 916, 533);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Zaposleni");
		
		JLabel lblTelefon = new JLabel("Telefon");
		lblTelefon.setBounds(10, 141, 126, 14);
		frame.getContentPane().add(lblTelefon);
		
		JLabel lblIme = new JLabel("Ime");
		lblIme.setBounds(10, 189, 98, 14);
		frame.getContentPane().add(lblIme);
		
		JLabel lblPrezime = new JLabel("Prezime");
		lblPrezime.setBounds(10, 241, 98, 14);
		frame.getContentPane().add(lblPrezime);
		
		JLabel lblKatedra_id = new JLabel("Katedra_id");
		lblKatedra_id.setBounds(10, 298, 98, 14);
		frame.getContentPane().add(lblKatedra_id);
		
		JButton btnDodajZaposlenog = new JButton("DODAJ");
		btnDodajZaposlenog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				try {
					String query="insert into Zaposleni(Telefon,Ime,Prezime,Katedra_id, Zaposleni_id) values(?,?,?,?, ?)";
					PreparedStatement pst1 = connection.prepareStatement(query);
					pst1.setString(1, txtTelefon.getText() );
					pst1.setString(2, txtIme.getText() );
					
					pst1.setString(3, txtPrezime.getText() );
					pst1.setString(4, txtKatedra_id.getText() );
					pst1.setString(5, txtZaposleni_id.getText() );
					
						pst1.execute();
						JOptionPane.showMessageDialog(null,"Uspješno ste dodali novog zaposlenog!");
					
						pst1.close();
						//rs.close();
						
						
						
					}
					
					catch(Exception e)
					{
						e.printStackTrace();
					}
					refreshTable();
					
					clearFields();
					}		
				
				
			});
		
		btnDodajZaposlenog.setBounds(190, 461, 109, 23);
		frame.getContentPane().add(btnDodajZaposlenog);
		btnDodajZaposlenog.setIcon(new ImageIcon(getClass().getResource("/StudentSistem/attach.png")));
		
		JButton btnIzmijeniZaposlenog = new JButton("IZMIJENI");
		btnIzmijeniZaposlenog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
try {
					
					
					String query="update Zaposleni set Telefon='"+txtTelefon.getText()+"', Ime= '"+txtIme.getText()+"', Prezime = '"+txtPrezime.getText()+"', Katedra_id='"+txtKatedra_id.getText()+"' where Zaposleni_id='"+txtZaposleni_id.getText()+"'";
					PreparedStatement pst = connection.prepareStatement(query);
					
					pst.execute();
					JOptionPane.showMessageDialog(null,"Uspješno ste izmijenili  podatke o zaposlenom!");
				
					pst.close();
					//rs.close();
					
					
					
				}
				
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
refreshTable();
clearFields();
			}
		});
		
		btnIzmijeniZaposlenog.setBounds(334, 461, 109, 23);
		frame.getContentPane().add(btnIzmijeniZaposlenog);
		btnIzmijeniZaposlenog.setIcon(new ImageIcon(getClass().getResource("/StudentSistem/update icon.png")));
		
		JButton btnObrisiZaposlenog = new JButton("OBRISI");
		btnObrisiZaposlenog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
try {
					
					
					String query="delete from Zaposleni where Zaposleni_id='"+txtZaposleni_id.getText()+"'";
					PreparedStatement pst = connection.prepareStatement(query);
					

					pst.execute();
					JOptionPane.showMessageDialog(null,"Uspješno ste izbrisali  podatke o zaposlenom!");
				
					pst.close();
					//rs.close();
					
					
					
				}
				
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
refreshTable();
clearFields();
			}
		});
		
		btnObrisiZaposlenog.setBounds(486, 461, 109, 23);
		frame.getContentPane().add(btnObrisiZaposlenog);
		btnObrisiZaposlenog.setIcon(new ImageIcon(getClass().getResource("/StudentSistem/delete_16x16.gif")));
		
		JButton btnPonistiZaposlenog = new JButton("PONISTI");
		btnPonistiZaposlenog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearFields();
			}
		});
		
		btnPonistiZaposlenog.setBounds(632, 461, 109, 23);
		frame.getContentPane().add(btnPonistiZaposlenog);
		btnPonistiZaposlenog.setIcon(new ImageIcon(getClass().getResource("/StudentSistem/erase.png")));
		
		JLabel labelPretraga = new JLabel("Pretraga");
		labelPretraga.setBounds(425, 35, 143, 14);
		frame.getContentPane().add(labelPretraga);
		
		JTextField txtPretragaZaposlenih = new JTextField();
		txtPretragaZaposlenih.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				try {
					
String selection = (String)comboBoxSortiranje.getSelectedItem();
					
					String query="select * from Zaposleni where "+selection+"=?";
					
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1,txtPretragaZaposlenih.getText());
					ResultSet rs=pst.executeQuery();
					tableZaposleni.setModel(DbUtils.resultSetToTableModel(rs));
					
					
					
				}
				
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		});
		
		txtPretragaZaposlenih.setBounds(578, 32, 143, 20);
		frame.getContentPane().add(txtPretragaZaposlenih);
		txtPretragaZaposlenih.setColumns(10);
		
		
		
		 txtTelefon = new JTextField();
		 txtTelefon.addKeyListener(new KeyAdapter() {
		 	@Override
		 	public void keyTyped(KeyEvent e) {
		 		
		 		char c=e.getKeyChar();
				if(Character.isLetter(c) || c != '.' && !(Character.isDigit(c)))
				 {
				
					e.consume();
					
				 }
		 		
		 	}
		 });
		 
		 txtTelefon.setBounds(146, 141, 86, 20);
			frame.getContentPane().add(txtTelefon);
			txtTelefon.setColumns(10);
			
			 txtIme = new JTextField();
			 txtIme.addKeyListener(new KeyAdapter() {
			 	@Override
			 	public void keyTyped(KeyEvent e) {
			 		
			 		
			 		char c=e.getKeyChar();
					if(Character.isDigit(c) || c != '.' && !(Character.isLetter(c)))
					 {
					
						e.consume();
						
					 }
			 		
			 	}
			 });
			txtIme.setBounds(146, 189, 86, 20);
			frame.getContentPane().add(txtIme);
			txtIme.setColumns(10);
			
			 txtPrezime = new JTextField();
			 txtPrezime.addKeyListener(new KeyAdapter() {
			 	@Override
			 	public void keyTyped(KeyEvent e) {
			 		
			 		char c=e.getKeyChar();
					if(Character.isDigit(c) || c != '.' && !(Character.isLetter(c)))
					 {
					
						e.consume();
						
					 }
			 		
			 	}
			 });
			txtPrezime.setBounds(146, 241, 86, 20);
			frame.getContentPane().add(txtPrezime);
			txtPrezime.setColumns(10);
			
			 txtKatedra_id = new JTextField();
			 txtKatedra_id.addKeyListener(new KeyAdapter() {
			 	@Override
			 	public void keyTyped(KeyEvent e) {
			 		
			 		char c=e.getKeyChar();
					if(Character.isLetter(c) || c != '.' && !(Character.isDigit(c)))
					 {
					
						e.consume();
						
					 }
			 		
			 	}
			 });
			
			 txtKatedra_id = new JTextField();
				txtKatedra_id.setBounds(146, 295, 86, 20);
				frame.getContentPane().add(txtKatedra_id);
				txtKatedra_id.setColumns(10);
				
				JButton btnPrikaziZaposlene = new JButton("PRIKAZI ZAPOSLENE");
				btnPrikaziZaposlene.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						try{ 
							String query="select * from Zaposleni";
						
						PreparedStatement pst = connection.prepareStatement(query);
						ResultSet rs = pst.executeQuery();
						
						tableZaposleni.setModel(DbUtils.resultSetToTableModel(rs));
						
					}
					catch(Exception ex) {
						ex.printStackTrace();	}
					
				}});
				
				btnPrikaziZaposlene.setBounds(297, 84, 166, 23);
				frame.getContentPane().add(btnPrikaziZaposlene);
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(297, 118, 575, 327);
				frame.getContentPane().add(scrollPane);
				
				tableZaposleni = new JTable();
				tableZaposleni.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
						DefaultTableModel model=(DefaultTableModel) tableZaposleni.getModel();
						int selectedRowIndex = tableZaposleni.getSelectedRow();
						txtZaposleni_id.setText(model.getValueAt(selectedRowIndex, 1).toString());
						txtTelefon.setText(model.getValueAt(selectedRowIndex,2).toString());
						txtIme.setText(model.getValueAt(selectedRowIndex,3).toString());
						txtPrezime.setText(model.getValueAt(selectedRowIndex,4).toString());
						txtKatedra_id.setText(model.getValueAt(selectedRowIndex,5).toString());
						
					}
				});
				scrollPane.setViewportView(tableZaposleni);
				
				 comboBoxSortiranje = new JComboBox();
					comboBoxSortiranje.setModel(new DefaultComboBoxModel(new String[] { "Ime", "Prezime", "Katedra_id"}));
					comboBoxSortiranje.setBounds(580, 11, 116, 20);
					frame.getContentPane().add(comboBoxSortiranje);
					
					txtZaposleni_id = new JTextField();
					txtZaposleni_id.setBounds(10, 85, 86, 20);
					frame.getContentPane().add(txtZaposleni_id);
					txtZaposleni_id.setColumns(10);
					
					JButton btnNewButton = new JButton("Katedre");
					btnNewButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
//							Katedra info = new Katedra();
							//	Katedra.main(null);
								Katedra window=new Katedra();
								window.frame.setVisible(true);
								
							}
						});
					
					btnNewButton.setBounds(748, 31, 150, 23);
					btnNewButton.setIcon(new ImageIcon(getClass().getResource("/StudentSistem/back.png")));
					frame.getContentPane().add(btnNewButton);
					txtZaposleni_id.setVisible(true);
					refreshTable();
					clearFields();
				}
			}

				