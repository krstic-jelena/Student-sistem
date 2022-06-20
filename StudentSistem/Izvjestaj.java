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

public class Izvjestaj {
	public JFrame frame;
	private JTextField txtKurs_id;
	private JTextField txtOpis;
	private JTextField txtOcjena;
	

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Izvjestaj window = new Izvjestaj();
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
	private JTable Izvjestaj;
	private JComboBox comboBoxKursevi;
	private JComboBox comboBoxKatedra;
	private JComboBox comboBoxSortiranje;
	private JTextField txtIzvjestaj_id;
	
	public void popuniComboBoxKursevi()
	{	
		ResultSet rsKursevi = DBHelper.naziviKurseva();
		try {
			while(rsKursevi.next())
			{	
				comboBoxKursevi.addItem(rsKursevi.getString("Naziv"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		comboBoxKursevi.setSelectedIndex(-1);
		
	}
	
	public void popuniComboBoxKatedra()
	{	
		ResultSet rsKatedra = DBHelper.naziviKatedre();
		try {
			while(rsKatedra.next())
			{	
				comboBoxKatedra.addItem(rsKatedra.getString("Naziv_katedre"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		comboBoxKursevi.setSelectedIndex(-1);
		
	}
	public void refreshTable()
	{
		try{ 
			String query="select * from Izvjestaji";
		
		PreparedStatement pst = connection.prepareStatement(query);
		ResultSet rs = pst.executeQuery();
		
	Izvjestaj.setModel(DbUtils.resultSetToTableModel(rs));
		
	}catch(Exception ex) {
		ex.printStackTrace();	}
	}
	private void clearFields()
	{
		txtKurs_id.setText(null);
		txtIzvjestaj_id.setText(null);
		txtOpis.setText(null);
		txtOcjena.setText(null);
		
	}
	
public Izvjestaj() {
		
		connection= Connect.java_db();
		initialize();
		
		
	}
/**
 * Initialize the contents of the frame.
 */

private void initialize() {
	frame = new JFrame();
	frame.getContentPane().setBackground(new Color(0, 153, 255));
	frame.setBounds(100, 100, 916, 533);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().setLayout(null);
	frame.setTitle("Izvjestaji");

	JLabel lblKurs_id = new JLabel("Kurs_id");
	lblKurs_id.setBounds(10, 200, 126, 14);
	frame.getContentPane().add(lblKurs_id);
	

	
	JLabel lblOpis = new JLabel("Opis");
	lblOpis.setBounds(10, 290, 98, 14);
	frame.getContentPane().add(lblOpis);
	
	JLabel lblOcjena = new JLabel("Ocjena");
	lblOcjena.setBounds(10, 245, 98, 14);
	frame.getContentPane().add(lblOcjena);
	


	
	JButton btnDodajIzvjestaj = new JButton("DODAJ");
	btnDodajIzvjestaj.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			
			
try {String query="insert into Izvjestaji(izvjestaj_id, Ocjena,Opis, kurs_id) values(?,?,?, ?)";
PreparedStatement pst1 = connection.prepareStatement(query);

pst1.setString(1, txtIzvjestaj_id.getText() );
pst1.setString(2, txtOcjena.getText() );
pst1.setString(3, txtOpis.getText() );
pst1.setString(4, txtKurs_id.getText() );

pst1.execute();
JOptionPane.showMessageDialog(null,"Uspješno ste dodali novi izvjestaj!");

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
	
	btnDodajIzvjestaj.setBounds(190, 461, 109, 23);
	frame.getContentPane().add(btnDodajIzvjestaj);
	btnDodajIzvjestaj.setIcon(new ImageIcon(getClass().getResource("/StudentSistem/attach.png")));
	
	JButton btnIzmijeniIzvjestaj= new JButton("IZMIJENI");
	btnIzmijeniIzvjestaj.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {try {
			
			
			String query="update Izvjestaji set Kurs_id='"+txtKurs_id.getText()+"', Ocjena='"+txtOcjena.getText()+"', Opis='"+txtOpis.getText()+"' where Izvjestaj_id='"+txtIzvjestaj_id.getText()+"'";
			PreparedStatement pst = connection.prepareStatement(query);
		
		
			
			pst.execute();
			JOptionPane.showMessageDialog(null,"Uspješno ste izmijenili  Izvjestaj!");
		
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
	
	btnIzmijeniIzvjestaj.setBounds(334, 461, 109, 23);
	frame.getContentPane().add(btnIzmijeniIzvjestaj);
	btnIzmijeniIzvjestaj.setIcon(new ImageIcon(getClass().getResource("/StudentSistem/update icon.png")));
	
	JButton btnObrisiIzvjestaj = new JButton("OBRISI");
	btnObrisiIzvjestaj.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				
				
				String query="delete from Izvjestaji where Izvjestaj_id='"+txtIzvjestaj_id.getText()+"'";
				PreparedStatement pst = connection.prepareStatement(query);
			
			
				
				pst.execute();
				JOptionPane.showMessageDialog(null,"Uspješno ste izbrisali izvjestaj!");
			
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
	btnObrisiIzvjestaj.setBounds(486, 461, 109, 23);
	frame.getContentPane().add(btnObrisiIzvjestaj);
	btnObrisiIzvjestaj.setIcon(new ImageIcon(getClass().getResource("/StudentSistem/delete_16x16.gif")));
	
	JButton btnPonistiIzvjestaj = new JButton("PONISTI");
	btnPonistiIzvjestaj.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			clearFields();
		}
	});
	btnPonistiIzvjestaj.setBounds(632, 461, 109, 23);
	frame.getContentPane().add(btnPonistiIzvjestaj);
	btnPonistiIzvjestaj.setIcon(new ImageIcon(getClass().getResource("/StudentSistem/erase.png")));
	
	
	
	JButton btnSlika = new JButton("");
	btnSlika.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			clearFields();
		}
	});
	btnSlika.setBounds(720, 1, 180, 80);
	frame.getContentPane().add(btnSlika);
	btnSlika.setIcon(new ImageIcon(getClass().getResource("/StudentSistem/header.PNG")));
	
	
	JLabel labelPretraga = new JLabel("Pretraga");
	labelPretraga.setBounds(418, 35, 77, 14);
	frame.getContentPane().add(labelPretraga);
	

	
	JTextField txtPretragaIzvjestaja = new JTextField();
	txtPretragaIzvjestaja.addKeyListener(new KeyAdapter() {
		@Override
		public void keyReleased(KeyEvent arg0) {
			try {
		
			String selection = (String)comboBoxSortiranje.getSelectedItem();
			
			String query="select * from Izvjestaji where "+selection+"=?";
							
							
							PreparedStatement pst = connection.prepareStatement(query);
							pst.setString(1,txtPretragaIzvjestaja.getText());
							ResultSet rs=pst.executeQuery();
							Izvjestaj.setModel(DbUtils.resultSetToTableModel(rs));
							
							
							
						}
						
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
				});
	
	txtPretragaIzvjestaja.setBounds(505, 32, 216, 20);
	frame.getContentPane().add(txtPretragaIzvjestaja);
	txtPretragaIzvjestaja.setColumns(10);
	
	 txtKurs_id = new JTextField();
	 txtKurs_id.addKeyListener(new KeyAdapter() {
	 	@Override
	 	public void keyTyped(KeyEvent e) {
	 		
	 		char c=e.getKeyChar();
			if(Character.isLetter(c) || c != '.' && !(Character.isDigit(c)))
			 {
			
				e.consume();
				
			 }
	 	}
	 });
	 txtKurs_id.setBounds(146, 200, 86, 20);
		frame.getContentPane().add(txtKurs_id);
		txtKurs_id.setColumns(10);
		
		 txtOcjena = new JTextField();
		 txtOcjena.addKeyListener(new KeyAdapter() {
		 	@Override
		 	public void keyTyped(KeyEvent e) {
		 		
		 		char c=e.getKeyChar();
				if(Character.isLetter(c) || c != '.' && !(Character.isDigit(c)))
				 {
				
					e.consume();
					
				 }
		 	}
		 });
		txtOcjena.setBounds(146, 245, 86, 20);
		frame.getContentPane().add(		txtOcjena);
		txtOcjena.setColumns(10);
		
		 txtOpis = new JTextField();
			txtOpis.setBounds(146, 290, 86, 20);
			frame.getContentPane().add(txtOpis);
			txtOpis.setColumns(10);
			
			JButton btnPrikaziIzvjestaje = new JButton("PRIKAZI IZVJESTAJE");
			btnPrikaziIzvjestaje.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					try{ 
						String query="select * from Izvjestaji";
					
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					
					Izvjestaj.setModel(DbUtils.resultSetToTableModel(rs));
					
				}
				catch(Exception ex) {
					ex.printStackTrace();	}
				
			}});
			btnPrikaziIzvjestaje.setBounds(275, 95, 220, 23);
			frame.getContentPane().add(btnPrikaziIzvjestaje);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(278, 128, 612, 321);
			frame.getContentPane().add(scrollPane);
			

			
			Izvjestaj = new JTable();
			Izvjestaj.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					

					DefaultTableModel model=(DefaultTableModel) Izvjestaj.getModel();
					int selectedRowIndex = Izvjestaj.getSelectedRow();
					txtKurs_id.setText(model.getValueAt(selectedRowIndex, 0).toString());
					txtOcjena.setText(model.getValueAt(selectedRowIndex,1).toString());
					txtOpis.setText(model.getValueAt(selectedRowIndex,2).toString());
					txtIzvjestaj_id.setText(model.getValueAt(selectedRowIndex,3).toString());
					
				}
			});
			
			scrollPane.setViewportView(Izvjestaj);
			JButton btnDodajNoviKurs = new JButton("Dodaj novi kurs");
			btnDodajNoviKurs.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					

				//	Kursevi info = new Kursevi();
				//	Projekat.main(null);
					
					Kursevi window = new Kursevi();
					window.frame.setVisible(true);	
				
				}
				
			});
			btnDodajNoviKurs.setBounds(10, 31, 199, 23);
			frame.getContentPane().add(btnDodajNoviKurs);
			
			 comboBoxKursevi = new JComboBox<String>();
				comboBoxKursevi.setToolTipText("");
				comboBoxKursevi.setBounds(10, 96, 222, 20);
				frame.getContentPane().add(comboBoxKursevi);
				
				JLabel lblPrikaziKurseve = new JLabel("Kursevi");
				lblPrikaziKurseve.setBounds(85, 76, 78, 14);
				frame.getContentPane().add(lblPrikaziKurseve);
				
				JButton btnDodajNovuKatedru = new JButton("Dodaj novu katedru");
				btnDodajNovuKatedru.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						

					//	Katedra info = new Katedra();
					//	Zaposleni.main(null);
						
						Katedra window = new Katedra();
						window.frame.setVisible(true);	
					
					}
					
				});
				
				btnDodajNovuKatedru.setBounds(10, 335, 199, 23);
				frame.getContentPane().add(btnDodajNovuKatedru);
				
				 comboBoxKatedra = new JComboBox<String>();
					comboBoxKatedra.setToolTipText("");
					comboBoxKatedra.setBounds(10, 400, 222, 20);
					frame.getContentPane().add(comboBoxKatedra);
					
					JLabel lblPrikaziKatedre = new JLabel("Katedre");
					lblPrikaziKatedre.setBounds(85, 385, 78, 14);
					frame.getContentPane().add(lblPrikaziKatedre);
				
		
				
				 comboBoxSortiranje = new JComboBox<Object>();
					comboBoxSortiranje.setModel(new DefaultComboBoxModel<Object>(new String[] {"Izvjestaj_id", "Kurs_id"}));
					comboBoxSortiranje.setBounds(505, 0, 70, 20);
					frame.getContentPane().add(comboBoxSortiranje);
					
					txtIzvjestaj_id = new JTextField();
					txtIzvjestaj_id.setBounds(10, 170, 86, 20);
					frame.getContentPane().add(txtIzvjestaj_id);
					txtIzvjestaj_id.setColumns(10);
					txtIzvjestaj_id.setVisible(true);
					refreshTable();
					clearFields();
					
					popuniComboBoxKursevi();
					
					popuniComboBoxKatedra();
				}

			}

				
						
			
					
					
	
	