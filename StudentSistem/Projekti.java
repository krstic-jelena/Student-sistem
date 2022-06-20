package StudentSistem;
import java.sql.Connection;
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

public class Projekti {
	public JFrame frame;
	private JComboBox comboBoxSortiranje;
	private JTextField txtnaziv_projekta;
	private JTextField txtdatum_projekta;
	private JTextField txtkurs_id;
	private JTextField txtbodovi;

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Projekti window = new Projekti();
					window.frame.setVisible(false);
					smslog window1 = new smslog();
					smslog.main(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		// TODO Auto-generated method stub
	}
		Connection connection=null;
		private JTable tableProjekti;
		private JTextField txtprojekt_id;
		
		public void refreshTable()
		{

			try{ 
				String query="select * from Projekti";
			
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			tableProjekti.setModel(DbUtils.resultSetToTableModel(rs));
			
		}
		catch(Exception ex) {
			ex.printStackTrace();	}
		}
		private void clearFields()
		{
			txtprojekt_id.setText(null);
			txtkurs_id.setText(null);
			txtnaziv_projekta.setText(null);
			
			txtdatum_projekta.setText(null);
			txtbodovi.setText(null);
		}
		public Projekti() {
			
			connection= Connect.java_db();
			initialize();
		}
		
		private void initialize() {
			frame = new JFrame();
			frame.getContentPane().setBackground(new Color(204, 255, 255));
			frame.setBounds(100, 100, 916, 533);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(null);
			frame.setTitle("Projekti");
			
			JLabel lblKurs_id = new JLabel("kurs_id");
			lblKurs_id.setBounds(10, 160, 98, 14);
			frame.getContentPane().add(lblKurs_id);
			
		
			
			JLabel lblnaziv_projekta = new JLabel("naziv_projekta");
			lblnaziv_projekta.setBounds(10, 200, 98, 14);
			frame.getContentPane().add(lblnaziv_projekta);
			
			JLabel lbldatum_projekta = new JLabel("datum_projekta");
			lbldatum_projekta.setBounds(10, 251, 98, 14);
			frame.getContentPane().add(lbldatum_projekta);
			
			JLabel lblbodovi = new JLabel("bodovi");
			lblbodovi.setBounds(10, 225, 98, 14);
			frame.getContentPane().add(lblbodovi);
			
			JButton btnDodajProjekat = new JButton("DODAJ");
			btnDodajProjekat.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					try {
						String query="insert into Projekti(projekt_id, kurs_id, naziv_projekta,datum_projekta, bodovi) values(?,?, ?, ?,?)";
						PreparedStatement pst1 = connection.prepareStatement(query);
					
					
					pst1.setString(1, txtprojekt_id.getText() );
					
					pst1.setString(2, txtkurs_id.getText() );
					pst1.setString(3, txtnaziv_projekta.getText() );
					pst1.setString(4, txtdatum_projekta.getText() );
					pst1.setString(5, txtbodovi.getText() );
					
						pst1.execute();
						JOptionPane.showMessageDialog(null,"Uspješno ste dodali novi projekat!");
					
						pst1.close();
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
			btnDodajProjekat.setBounds(190, 461, 109, 23);
			frame.getContentPane().add(btnDodajProjekat);
			btnDodajProjekat.setIcon(new ImageIcon(getClass().getResource("/StudentSistem/attach.png")));
			
			JButton btnIzmijeniProjekat= new JButton("IZMIJENI");
			btnIzmijeniProjekat.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						
						
						String query="update Projekti set  naziv_projekta= '"+txtnaziv_projekta.getText()+"',datum_projekta= '"+txtdatum_projekta.getText()+"', bodovi= '"+txtbodovi.getText()+"' where projekt_id='"+txtprojekt_id.getText()+"'";
						PreparedStatement pst = connection.prepareStatement(query);
					
					
						
						pst.execute();
						JOptionPane.showMessageDialog(null,"Uspješno ste izmijenili projekat!");
					
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
			btnIzmijeniProjekat.setBounds(334, 461, 109, 23);
			frame.getContentPane().add(btnIzmijeniProjekat);
			btnIzmijeniProjekat.setIcon(new ImageIcon(getClass().getResource("/StudentSistem/update icon.png")));
			
			
			JButton btnObrisiProjekat = new JButton("OBRISI");
			btnObrisiProjekat.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						
						
						String query="delete from Projekti  where projekt_id='"+txtprojekt_id.getText()+"'";
						PreparedStatement pst = connection.prepareStatement(query);
					
					
						
						pst.execute();
						JOptionPane.showMessageDialog(null,"Uspješno ste izbrisali projekat!");
					
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

			btnObrisiProjekat.setBounds(486, 461, 109, 23);
			frame.getContentPane().add(btnObrisiProjekat);
			btnObrisiProjekat.setIcon(new ImageIcon(getClass().getResource("/StudentSistem/delete_16x16.gif")));
			
			
			JButton btnPonistiProjekat = new JButton("PONISTI");
			btnPonistiProjekat.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					clearFields();
				}
			});
			btnPonistiProjekat.setBounds(632, 461, 109, 23);
			frame.getContentPane().add(btnPonistiProjekat);
			btnPonistiProjekat.setIcon(new ImageIcon(getClass().getResource("/StudentSistem/erase.png")));
			
			
			JLabel labelPretraga = new JLabel("Pretraga");
			labelPretraga.setBounds(362, 35, 133, 14);
			frame.getContentPane().add(labelPretraga);
			
			JTextField txtPretragaProjekta= new JTextField();
			txtPretragaProjekta.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					
					try {
						
						String selection = (String)comboBoxSortiranje.getSelectedItem();
						
						String query="select * from Projekti where "+selection+"=?";
						PreparedStatement pst = connection.prepareStatement(query);
						pst.setString(1,txtPretragaProjekta.getText());
						ResultSet rs=pst.executeQuery();
						tableProjekti.setModel(DbUtils.resultSetToTableModel(rs));
						
						
						
					}
					
					catch(Exception ex)
					{
						ex.printStackTrace();
					}
				}
			});
			txtPretragaProjekta.setBounds(505, 32, 216, 20);
			frame.getContentPane().add(txtPretragaProjekta);
			txtPretragaProjekta.setColumns(10);
			
			  txtnaziv_projekta = new JTextField();
				txtnaziv_projekta.setBounds(146, 200, 86, 20);
				frame.getContentPane().add(txtnaziv_projekta);
				txtnaziv_projekta.setColumns(10);
				
				 txtdatum_projekta = new JTextField();
					txtdatum_projekta.setBounds(146, 251, 86, 20);
					frame.getContentPane().add(txtdatum_projekta);
					txtdatum_projekta.setColumns(10);
				
				 txtbodovi = new JTextField();
				 txtbodovi.addKeyListener(new KeyAdapter() {
				 	@Override
				 	public void keyTyped(KeyEvent e) {
				 		
				 		char c=e.getKeyChar();
						if(Character.isLetter(c) || c != '.' && !(Character.isDigit(c)))
						 {
						
							e.consume();
							
						 }
				 	}
				 });
				 txtbodovi.setBounds(146, 225, 86, 20);
					frame.getContentPane().add(txtbodovi);
					txtbodovi.setColumns(10);
					
					 txtkurs_id = new JTextField();
					 txtkurs_id.addKeyListener(new KeyAdapter() {
					 	@Override
					 	public void keyTyped(KeyEvent e) {
					 		
					 		char c=e.getKeyChar();
							if(Character.isLetter(c) || c != '.' && !(Character.isDigit(c)))
							 {
							
								e.consume();
								
							 }
					 	}
					 });
					 txtkurs_id.setBounds(146, 160, 86, 20);
						frame.getContentPane().add(txtkurs_id);
						txtkurs_id.setColumns(10);
						

					
					JButton btnPrikaziProjekat = new JButton("PRIKAZI PROJEKAT");
					btnPrikaziProjekat.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							try{ 
								String query="select * from projekti";
							
							PreparedStatement pst = connection.prepareStatement(query);
							ResultSet rs = pst.executeQuery();
							
							tableProjekti.setModel(DbUtils.resultSetToTableModel(rs));
							
						}
						catch(Exception ex) {
							ex.printStackTrace();	}
						
					}});	
					btnPrikaziProjekat.setBounds(275, 95, 230, 23);
					frame.getContentPane().add(btnPrikaziProjekat);
					
					//tableProjekti = new JTable();
					JScrollPane scrollPane = new JScrollPane();
					scrollPane.setBounds(278, 147, 601, 294);
					frame.getContentPane().add(scrollPane);
					
					tableProjekti = new JTable();
					tableProjekti.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							DefaultTableModel model=(DefaultTableModel) tableProjekti.getModel();
							int selectedRowIndex = tableProjekti.getSelectedRow();
							txtprojekt_id.setText(model.getValueAt(selectedRowIndex, 0).toString());
							
							txtnaziv_projekta.setText(model.getValueAt(selectedRowIndex,1).toString());
						
							txtdatum_projekta.setText(model.getValueAt(selectedRowIndex,2).toString());
							txtbodovi.setText(model.getValueAt(selectedRowIndex,3).toString());
							txtkurs_id.setText(model.getValueAt(selectedRowIndex, 4).toString());
							
							
						}
					});
					scrollPane.setViewportView(tableProjekti);
					
					comboBoxSortiranje = new JComboBox();
					comboBoxSortiranje.setModel(new DefaultComboBoxModel(new String[] {"naziv_projekta", "projekt_id"}));
					comboBoxSortiranje.setBounds(505, 1, 98, 20);
					frame.getContentPane().add(comboBoxSortiranje);
					
					txtprojekt_id = new JTextField();
					txtprojekt_id.setBounds(10, 145, 86, 20);
					frame.getContentPane().add(txtprojekt_id);
					txtprojekt_id.setColumns(10);
	
					JButton btnNewButton = new JButton("Vrati na kurs");
					btnNewButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							
							
							//Kursevi info = new Kursevi();
							//Kursevi.main(null);}
							Kursevi window=new Kursevi();
							window.frame.setVisible(true);}
						
					});
					
					btnNewButton.setBounds(757, 31, 160, 23);
					btnNewButton.setIcon(new ImageIcon(getClass().getResource("/StudentSistem/back.png")));
					
					
					frame.getContentPane().add(btnNewButton);
					txtprojekt_id.setVisible(true);
					refreshTable();
					clearFields();
					
					
					

					
					

				}	
				}
