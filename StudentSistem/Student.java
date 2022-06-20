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

public class Student {


	public JFrame frame;
	private JComboBox comboBoxSortiranje;
	private JTextField txtdatum_rodjenja;
	private JTextField txtime;
	private JTextField txtprezime;
	private JTextField txttelefon;
	private JTextField txtpol;
	
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Student window = new Student();
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
	Connection connection = null;
	
	private JTable tableStudent;
	private JTextField txtstudent_id;
	public void refreshTable()
	{
		
		try{ 
			String query="select * from Student";
		
		PreparedStatement pst = connection.prepareStatement(query);
		ResultSet rs = pst.executeQuery();
		
		tableStudent.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch(Exception ex) {
			ex.printStackTrace();	}
		}
		
		public void clearFields()
		{
			txtstudent_id.setText(null);
			txttelefon.setText(null);
			txtime.setText(null);
			txtprezime.setText(null);
			txtpol.setText(null);
			txtdatum_rodjenja.setText(null);
		}
		public Student() {
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
			frame.setTitle("Studenti");
			
			JLabel lblpol = new JLabel("pol");
			lblpol.setBounds(10, 141, 126, 14);
			frame.getContentPane().add(lblpol);
			
			JLabel lblime = new JLabel("ime");
			lblime.setBounds(10, 189, 98, 14);
			frame.getContentPane().add(lblime);
			
			JLabel lblprezime = new JLabel("prezime");
			lblprezime.setBounds(10, 241, 98, 14);
			frame.getContentPane().add(lblprezime);
			
			JLabel lbltelefon = new JLabel("telefon");
			lbltelefon.setBounds(10, 298, 98, 14);
			frame.getContentPane().add(lbltelefon);
			
			JLabel labeldatum_rodjenja = new JLabel("datum rodjenja");
			labeldatum_rodjenja.setBounds(10, 358, 98, 14);
			frame.getContentPane().add(labeldatum_rodjenja);
			
			JButton btnDodajStudenta = new JButton("DODAJ");
			btnDodajStudenta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						String query="insert into Student(ime,prezime,telefon,datum_rodjenja,pol, student_id) values(?,?,?,?,?,?)";
						PreparedStatement pst1 = connection.prepareStatement(query);
					
					pst1.setString(1, txtime.getText() );
					pst1.setString(2, txtprezime.getText() );
					
					pst1.setString(3, txttelefon.getText() );
					pst1.setString(4, txtdatum_rodjenja.getText() );
					pst1.setString(5, txtpol.getText());
					pst1.setString(6, txtstudent_id.getText());
					pst1.execute();
					JOptionPane.showMessageDialog(null,"Uspješno ste dodali novog studenta!");
				
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
			
			btnDodajStudenta.setBounds(190, 461, 109, 23);
			frame.getContentPane().add(btnDodajStudenta);
			btnDodajStudenta.setIcon(new ImageIcon(getClass().getResource("/StudentSistem/attach.png")));
			
			JButton btnIzmijeniStudenta = new JButton("IZMIJENI");
			btnIzmijeniStudenta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					
					
	try {
		String query="update Student set ime='"+txtime.getText()+"', prezime= '"+txtprezime.getText()+"', datum_rodjenja = '"+txtdatum_rodjenja.getText()+"', telefon='"+txttelefon.getText()+"', pol='"+txtpol.getText()+"' where student_id='"+txtstudent_id.getText()+"'";
		PreparedStatement pst = connection.prepareStatement(query);
	
	
		
		pst.execute();
		JOptionPane.showMessageDialog(null,"Uspješno ste izmijenili  podatke o studentu!");
	
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
			btnIzmijeniStudenta.setBounds(334, 461, 109, 23);
			frame.getContentPane().add(btnIzmijeniStudenta);
			btnIzmijeniStudenta.setIcon(new ImageIcon(getClass().getResource("/StudentSistem/update icon.png")));
			
			JButton btnObrisiStudenta = new JButton("OBRISI");
			btnObrisiStudenta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					
	try {String query="delete from Student  where student_id='"+txtstudent_id.getText()+"'";
	PreparedStatement pst = connection.prepareStatement(query);
	
	
	
	pst.execute();
	JOptionPane.showMessageDialog(null,"Uspješno ste izbrisali  podatke o studentu");

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
			btnObrisiStudenta.setBounds(486, 461, 109, 23);
			frame.getContentPane().add(btnObrisiStudenta);
			btnObrisiStudenta.setIcon(new ImageIcon(getClass().getResource("/StudentSistem/delete_16x16.gif")));
			
			JButton btnPonistiStudenta = new JButton("PONISTI");
			btnPonistiStudenta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					clearFields();
				}
			});
			btnPonistiStudenta.setBounds(632, 461, 109, 23);
			frame.getContentPane().add(btnPonistiStudenta);
			btnPonistiStudenta.setIcon(new ImageIcon(getClass().getResource("/StudentSistem/erase.png")));
			
			JLabel labelPretraga = new JLabel("Pretraga");
			labelPretraga.setBounds(425, 35, 143, 14);
			frame.getContentPane().add(labelPretraga);
			
			JTextField txtPretragaStudenta = new JTextField();
			txtPretragaStudenta.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					
					try {
						String selection = (String)comboBoxSortiranje.getSelectedItem();
						
						String query="select * from Student where "+selection+"=?";
					
						PreparedStatement pst = connection.prepareStatement(query);
						pst.setString(1,txtPretragaStudenta.getText());
						ResultSet rs=pst.executeQuery();
						tableStudent.setModel(DbUtils.resultSetToTableModel(rs));
						
						
						
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
					}
				}
			});
			txtPretragaStudenta.setBounds(578, 32, 143, 20);
			frame.getContentPane().add(txtPretragaStudenta);
			txtPretragaStudenta.setColumns(10);
			
			 txtime = new JTextField();
			 txtime.setBounds(146, 189, 86, 20);
				frame.getContentPane().add(txtime);
				txtime.setColumns(10);
				
				 txtprezime = new JTextField();
				 txtprezime.addKeyListener(new KeyAdapter() {
				 	@Override
				 	public void keyTyped(KeyEvent e) {
				 		
				 		
				 		char c=e.getKeyChar();
						if(Character.isDigit(c) || c != '.' && !(Character.isLetter(c)))
						 {
						
							e.consume();
							
						 }
				 		
				 	}
				 });
				 txtprezime.setBounds(146, 241, 86, 20);
					frame.getContentPane().add(txtprezime);
					txtprezime.setColumns(10);
					
					 txtdatum_rodjenja = new JTextField();
						txtdatum_rodjenja.setBounds(146, 358, 86, 20);
						frame.getContentPane().add(txtdatum_rodjenja);
						txtdatum_rodjenja.setColumns(10);

						 txttelefon = new JTextField();
						txttelefon.setBounds(146, 295, 86, 20);
						frame.getContentPane().add(txttelefon);
						txttelefon.setColumns(10);
						
						 txtpol = new JTextField();
							txtpol.setBounds(146, 141, 86, 20);
							frame.getContentPane().add(txtpol);
							txtpol.setColumns(10);
					
							JButton btnPrikaziStudente = new JButton("PRIKAZI STUDENTE");
							btnPrikaziStudente.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									
									try{ 
										String query="select * from Student";
									
									PreparedStatement pst = connection.prepareStatement(query);
									ResultSet rs = pst.executeQuery();
									
									tableStudent.setModel(DbUtils.resultSetToTableModel(rs));
									
								}
								catch(Exception ex) {
									ex.printStackTrace();	}
								
							}});
							btnPrikaziStudente.setBounds(297, 84, 166, 23);
							frame.getContentPane().add(btnPrikaziStudente);
							
							JScrollPane scrollPane = new JScrollPane();
							scrollPane.setBounds(297, 118, 575, 327);
							frame.getContentPane().add(scrollPane);
							tableStudent = new JTable();
							tableStudent.addMouseListener(new MouseAdapter() {
								@Override
								public void mouseClicked(MouseEvent e) {
									
									DefaultTableModel model=(DefaultTableModel) tableStudent.getModel();
									int selectedRowIndex = tableStudent.getSelectedRow();
									txtstudent_id.setText(model.getValueAt(selectedRowIndex, 0).toString());
									txtime.setText(model.getValueAt(selectedRowIndex,1).toString());
									txtprezime.setText(model.getValueAt(selectedRowIndex,2).toString());
									txtdatum_rodjenja.setText(model.getValueAt(selectedRowIndex,3).toString());
									txttelefon.setText(model.getValueAt(selectedRowIndex,4).toString());
									txtpol.setText(model.getValueAt(selectedRowIndex,5).toString());
									
									
								}
							});
							scrollPane.setViewportView(tableStudent);
							
							 comboBoxSortiranje = new JComboBox();
							comboBoxSortiranje.setModel(new DefaultComboBoxModel(new String[] {"ime", "prezime", "student_id"}));
							comboBoxSortiranje.setBounds(580, 11, 116, 20);
							frame.getContentPane().add(comboBoxSortiranje);
							
							
							txtstudent_id = new JTextField();
							txtstudent_id.setBounds(10, 85, 86, 20);
							frame.getContentPane().add(txtstudent_id);
							txtstudent_id.setColumns(10);
							JButton btnNewButton = new JButton("Vrati na kurs");
							btnNewButton.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									
									
								//	Kursevi info = new Kursevi();
								//	Kursevi.main(null);
									Kursevi window=new Kursevi();
									window.frame.setVisible(true);
									
								}
							});		
							btnNewButton.setBounds(748, 31, 150, 23);
							btnNewButton.setIcon(new ImageIcon(getClass().getResource("/StudentSistem/back.png")));
							
							frame.getContentPane().add(btnNewButton);
							txtstudent_id.setVisible(true);
							refreshTable();
							clearFields();
						}
					}

