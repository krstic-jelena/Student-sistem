package StudentSistem;

import java.sql.*;
import javax.swing.*;
import java.awt.EventQueue;
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

public class Kursevi {
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
					Kursevi window = new Kursevi();
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
	//private JComboBox comboBoxProjekti;
	public void popuniComboBoxProjekti()
	{	

		ResultSet rsProjekti = DBHelper.naziviKurseva();
		try {
			while(rsProjekti.next())
			{	
				comboBoxProjekti.addItem(rsProjekti.getString("Naziv"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		comboBoxProjekti.setSelectedIndex(-1);
		
	}
	
	public void popuniComboBoxStudent()
	{	

		ResultSet rsStudent = DBHelper.naziviStudenata();
		try {
			while(rsStudent.next())
			{	
				comboBoxProjekti.addItem(rsStudent.getString("ImePrezime"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		comboBoxStudent.setSelectedIndex(-1);
		
	}
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
	private void clearFields()
	{
		txtKurs_id.setText(null);
		txtZaposleni_id.setText(null);
		txtStudent_id.setText(null);
		txtNaziv.setText(null);
		txtOpis.setText(null);
		txtBodovi.setText(null);
		
	}
public Kursevi() {
		
		connection= Connect.java_db();
		initialize();
	}
/**
 * Initialize the contents of the frame.
 */



private void initialize() {
	frame = new JFrame();
	frame.getContentPane().setBackground(new Color(153, 204, 255));
	frame.setBounds(100, 100, 916, 533);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().setLayout(null);
	frame.setTitle("Kursevi");
	
	JLabel lblZaposleni_id = new JLabel("Zaposleni_id");
	lblZaposleni_id.setBounds(10, 245, 126, 14);
	frame.getContentPane().add(lblZaposleni_id);
	
	JLabel lblStudent_id = new JLabel("Student_id");
	lblStudent_id.setBounds(10, 165, 126, 14);
	frame.getContentPane().add(lblStudent_id);
	
	
	JLabel lblNaziv = new JLabel("Naziv");
	lblNaziv.setBounds(10, 295, 126, 14);
	frame.getContentPane().add(lblNaziv);
	
	JLabel lblOpis = new JLabel("Opis");
	lblOpis.setBounds(10, 200, 126, 14);
	frame.getContentPane().add(lblOpis);
	
	JLabel lblBodovi = new JLabel("Bodovi");
	lblBodovi.setBounds(10, 335, 126, 14);
	frame.getContentPane().add(lblBodovi);
	
	JButton btnDodajKurs = new JButton("DODAJ");
	btnDodajKurs.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			
			
try {String query="insert into Kurs(Kurs_id,Student_id, Zaposleni_id,  Naziv_kursa,  Opis, Bodovi) values(?, ?,?,?,?,?)";
PreparedStatement pst1 = connection.prepareStatement(query);

pst1.setString(1, txtKurs_id.getText() );
pst1.setString(2, txtStudent_id.getText() );
pst1.setString(3, txtZaposleni_id.getText() );
pst1.setString(4, txtNaziv.getText() );
pst1.setString(5, txtOpis.getText() );
pst1.setString(6, txtBodovi.getText() );


pst1.execute();
JOptionPane.showMessageDialog(null,"Uspješno ste dodali novi kurs!");

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
	btnDodajKurs.setBounds(190, 461, 109, 23);
	frame.getContentPane().add(btnDodajKurs);
	btnDodajKurs.setIcon(new ImageIcon(getClass().getResource("/StudentSistem/attach.png")));
	
	JButton btnIzmijeniKurs= new JButton("IZMIJENI");
	btnIzmijeniKurs.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {try {
			
			
			String query="update Kurs set Zaposleni_id='"+txtZaposleni_id.getText()+"', Student_id='"+txtStudent_id.getText()+"', Naziv_kursa= '"+txtNaziv.getText()+"', Bodovi='"+txtBodovi.getText()+"', Opis='"+txtOpis.getText()+"' where Kurs_id='"+txtKurs_id.getText()+"'";
			PreparedStatement pst = connection.prepareStatement(query);
		
		
			
			pst.execute();
			JOptionPane.showMessageDialog(null,"Uspješno ste izmijenili  kurs!");
		
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
	btnIzmijeniKurs.setBounds(334, 461, 109, 23);
	frame.getContentPane().add(btnIzmijeniKurs);
	btnIzmijeniKurs.setIcon(new ImageIcon(getClass().getResource("/StudentSistem/update icon.png")));
	
	JButton btnObrisiKurs = new JButton("OBRISI");
	btnObrisiKurs.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				
				
				String query="delete from Kurs where Kurs_id='"+txtKurs_id.getText()+"'";
				PreparedStatement pst = connection.prepareStatement(query);
			
			
				
				pst.execute();
				JOptionPane.showMessageDialog(null,"Uspješno ste izbrisali kurs!");
			
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
	btnObrisiKurs.setBounds(486, 461, 109, 23);
	frame.getContentPane().add(btnObrisiKurs);
	btnObrisiKurs.setIcon(new ImageIcon(getClass().getResource("/StudentSistem/delete_16x16.gif")));
	
	JButton btnPonistiKurs = new JButton("PONISTI");
	btnPonistiKurs.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			clearFields();
		}
	});
	btnPonistiKurs.setBounds(632, 461, 109, 23);
	frame.getContentPane().add(btnPonistiKurs);
	btnPonistiKurs.setIcon(new ImageIcon(getClass().getResource("/StudentSistem/erase.png")));
	
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
	 txtZaposleni_id.setBounds(146, 245, 126, 20);
		frame.getContentPane().add(txtZaposleni_id);
		txtZaposleni_id.setColumns(10);
		
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
		 
		 txtStudent_id.setBounds(146, 165, 126, 20);
			frame.getContentPane().add(txtStudent_id);
			txtStudent_id.setColumns(10);
		
			
		
			 txtNaziv = new JTextField();
				txtNaziv.setBounds(146, 295, 126, 20);
				frame.getContentPane().add(txtNaziv);
				txtNaziv.setColumns(10);
				
				 txtOpis = new JTextField();
					txtOpis.setBounds(146, 200, 126, 20);
					frame.getContentPane().add(txtOpis);
					txtOpis.setColumns(10);
				
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
				txtBodovi.setBounds(146, 335, 126, 20);
				frame.getContentPane().add(		txtBodovi);
				txtBodovi.setColumns(10);
				

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
				scrollPane.setBounds(278, 128, 612, 321);
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
				JButton btnDodajNoviProjekat = new JButton("Dodaj novi projekat");
				btnDodajNoviProjekat.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						

					//	Projekti info = new Projekti();
					//	Projekat.main(null);
						
						Projekti window = new Projekti();
						window.frame.setVisible(true);	
					
					}
					
				});
				btnDodajNoviProjekat.setBounds(10, 65, 199, 23);
				frame.getContentPane().add(btnDodajNoviProjekat);
				 comboBoxProjekti = new JComboBox();
					comboBoxProjekti.setToolTipText("");
					comboBoxProjekti.setBounds(10, 96, 222, 20);
					frame.getContentPane().add(comboBoxProjekti);
					
					
				
				
				JButton btnDodajNovogStudenta = new JButton("Dodaj novog studenta");
				btnDodajNovogStudenta.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						

					//	Student info = new Student();
					//	Student.main(null);
						
						Student window = new Student();
						window.frame.setVisible(true);	
					
					}
					
				});
				
				btnDodajNovogStudenta.setBounds(10, 375, 199, 23);
				frame.getContentPane().add(btnDodajNovogStudenta);
				
				
				
				JButton btnPocetna = new JButton("POCETNA STRANA");
				btnPocetna.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
					//	Izvjestaj info = new Izvjestaj();
					//	Izvjestaj.main(null);
						
						Izvjestaj window=new Izvjestaj();
						window.frame.setVisible(true);
					}
					
				});
				btnPocetna.setBounds(750, 31, 180, 35);
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
					txtKurs_id.setVisible(true);
					refreshTable();
					clearFields();
					
					popuniComboBoxProjekti();
					
					
				}

			}

				