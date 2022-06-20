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

public class UKursevi {
	public JFrame frame;
	private JTextField txtZaposleni_id;
	private JTextField txtStudent_id;
	private JTextField txtNaziv;
	private JTextField txtBodovi;
	private JTextField txtOpis;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UKursevi window = new UKursevi();
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
	private JTable Kursevi;
	private JComboBox comboBoxProjekti;
	private JComboBox comboBoxStudent;
	private JComboBox comboBoxSortiranje;
	private JTextField txtKurs_id;
	
	public void refreshTable()
	{
		try{ 
			String query="select * from Kurs";
		
		PreparedStatement pst = connection.prepareStatement(query);
		ResultSet rs = pst.executeQuery();
		
	Kursevi.setModel(DbUtils.resultSetToTableModel(rs));
		
	}catch(Exception ex) {
		ex.printStackTrace();	}
	}
	
public UKursevi() {
		
		connection= Connect.java_db();
		initialize();
	}
/**
 * Initialize the contents of the frame.
 */

private void initialize() {
	frame = new JFrame();
	frame.getContentPane().setBackground(new Color(224, 224, 224));
	frame.setBounds(100, 100, 916, 533);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().setLayout(null);
	frame.setTitle("Kursevi");
	

	JLabel labelPretraga = new JLabel("Pretraga");
	labelPretraga.setBounds(418, 35, 77, 14);
	frame.getContentPane().add(labelPretraga);
	
	JTextField txtPretragaKursa = new JTextField();
	txtPretragaKursa.addKeyListener(new KeyAdapter() {
		@Override
		public void keyReleased(KeyEvent arg0) {
			try {
		
			String selection = (String)comboBoxSortiranje.getSelectedItem();
			
			String query="select * from Kurs where "+selection+"=?";
							
							
							PreparedStatement pst = connection.prepareStatement(query);
							pst.setString(1,txtPretragaKursa.getText());
							ResultSet rs=pst.executeQuery();
							Kursevi.setModel(DbUtils.resultSetToTableModel(rs));
							
							
							
						}
						
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
				});
	txtPretragaKursa.setBounds(505, 32, 216, 20);
	frame.getContentPane().add(txtPretragaKursa);
	txtPretragaKursa.setColumns(10);
	
	
	 txtZaposleni_id = new JTextField();
	 txtZaposleni_id.addKeyListener(new KeyAdapter() {
	 	@Override
	 	public void keyTyped(KeyEvent e) {
	 		
	 		char c=e.getKeyChar();
			if(Character.isLetter(c) || c != '.' && !(Character.isDigit(c)))
			 {
			
				e.consume();
				
			 }
	 	}
	 });
	 txtZaposleni_id.setBounds(146, 242, 86, 20);
		frame.getContentPane().add(txtZaposleni_id);
		txtZaposleni_id.setColumns(10);
		txtZaposleni_id.setVisible(false);
		
		 txtStudent_id = new JTextField();
		 txtStudent_id.addKeyListener(new KeyAdapter() {
		 	@Override
		 	public void keyTyped(KeyEvent e) {
		 		
		 		char c=e.getKeyChar();
				if(Character.isLetter(c) || c != '.' && !(Character.isDigit(c)))
				 {
				
					e.consume();
					
				 }
		 	}
		 });
		 
		 txtStudent_id.setBounds(146, 165, 86, 20);
			frame.getContentPane().add(txtStudent_id);
			txtStudent_id.setColumns(10);
			txtStudent_id.setVisible(false);
		
			
		
			 txtNaziv = new JTextField();
				txtNaziv.setBounds(146, 285, 86, 20);
				frame.getContentPane().add(txtNaziv);
				txtNaziv.setColumns(10);
				txtNaziv.setVisible(false);
				
				 txtOpis = new JTextField();
					txtOpis.setBounds(146, 199, 86, 20);
					frame.getContentPane().add(txtOpis);
					txtOpis.setColumns(10);
					txtOpis.setVisible(false);
				
				 txtBodovi = new JTextField();
				 txtBodovi.addKeyListener(new KeyAdapter() {
				 	@Override
				 	public void keyTyped(KeyEvent e) {
				 		
				 		char c=e.getKeyChar();
						if(Character.isLetter(c) || c != '.' && !(Character.isDigit(c)))
						 {
						
							e.consume();
							
						 }
				 	}
				 });
				txtBodovi.setBounds(146, 325, 86, 20);
				frame.getContentPane().add(		txtBodovi);
				txtBodovi.setColumns(10);
				txtBodovi.setVisible(false);
				
				JButton btnPrikaziKurseve = new JButton("PRIKAZI KURSEVE");
				btnPrikaziKurseve.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						try{ 
							String query="select * from Kurs";
						
						PreparedStatement pst = connection.prepareStatement(query);
						ResultSet rs = pst.executeQuery();
						
						Kursevi.setModel(DbUtils.resultSetToTableModel(rs));
						
					}
					catch(Exception ex) {
						ex.printStackTrace();	}
					
				}});
				btnPrikaziKurseve.setBounds(275, 95, 220, 23);
				frame.getContentPane().add(btnPrikaziKurseve);
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(50, 120, 800, 300);
				frame.getContentPane().add(scrollPane);
				
				Kursevi = new JTable();
				Kursevi.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						

						DefaultTableModel model=(DefaultTableModel) Kursevi.getModel();
						int selectedRowIndex = Kursevi.getSelectedRow();
						txtKurs_id.setText(model.getValueAt(selectedRowIndex, 0).toString());
						txtZaposleni_id.setText(model.getValueAt(selectedRowIndex,1).toString());
						txtStudent_id.setText(model.getValueAt(selectedRowIndex,2).toString());
						txtNaziv.setText(model.getValueAt(selectedRowIndex,3).toString());
						txtOpis.setText(model.getValueAt(selectedRowIndex,4).toString());
						txtBodovi.setText(model.getValueAt(selectedRowIndex,5).toString());
						
					}
				});
				scrollPane.setViewportView(Kursevi);
				
				
				JButton btnPocetna = new JButton("Vrati na studente!");
				btnPocetna.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
					//	UStudent info = new UStudent();
					//	UStudent.main(null);
						
						UStudent window=new UStudent();
						window.frame.setVisible(true);
					}
					
				});
				btnPocetna.setBounds(730, 31, 170, 30);
				frame.getContentPane().add(btnPocetna);
				btnPocetna.setIcon(new ImageIcon(getClass().getResource("/StudentSistem/back.png")));
				
				
				 comboBoxSortiranje = new JComboBox();
					comboBoxSortiranje.setModel(new DefaultComboBoxModel(new String[] {"Naziv_kursa", "Kurs_id"}));
					comboBoxSortiranje.setBounds(505, 0, 70, 20);
					frame.getContentPane().add(comboBoxSortiranje);
					
					txtKurs_id = new JTextField();
					txtKurs_id.setBounds(10, 145, 86, 20);
					frame.getContentPane().add(txtKurs_id);
					txtKurs_id.setColumns(10);
					txtKurs_id.setVisible(false);
					refreshTable();
					
}}
				

