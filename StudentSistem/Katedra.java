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

public class Katedra {

	public JFrame frame;
	private JTextField txtNaziv_katedre;
	
public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Katedra window = new Katedra();
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

private JTable tableKatedra;
private JTextField txtKatedra_id;
private JComboBox comboBoxZaposleni;

public void popuniComboBoxZaposleni()
{	

	ResultSet rsZaposleni = DBHelper.naziviZaposlenih();
	try {
		while(rsZaposleni.next())
		{	
			comboBoxZaposleni.addItem(rsZaposleni.getString("Ime"));
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	comboBoxZaposleni.setSelectedIndex(-1);
	
}

public void refreshTable()
{
	try{ 
		String query="select * from Katedra";
	
	PreparedStatement pst = connection.prepareStatement(query);
	ResultSet rs = pst.executeQuery();
	
	tableKatedra.setModel(DbUtils.resultSetToTableModel(rs));
	
}
catch(Exception ex) {
	ex.printStackTrace();	}

}

private void clearFields()
{
	txtKatedra_id.setText(null);
	txtNaziv_katedre.setText(null);
	
}

public Katedra() {
	
	connection= Connect.java_db();
	initialize();
}

private void initialize() {
	frame = new JFrame();
	frame.getContentPane().setBackground(new Color(255, 255, 102));
	frame.setBounds(100, 100, 916, 533);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().setLayout(null);
	frame.setTitle("Katedre");
	
	JLabel lblNaziv_katedre = new JLabel("Naziv_katedre");
	lblNaziv_katedre.setBounds(10, 335, 98, 14);
	frame.getContentPane().add(lblNaziv_katedre);
	
	JButton btnDodajKatedru = new JButton("DODAJ");
	btnDodajKatedru.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			try {
				String query="insert into Katedra(Katedra_id, Naziv_katedre) values(?, ?)";
				PreparedStatement pst1 = connection.prepareStatement(query);
				pst1.setString(1, txtKatedra_id.getText() );
			pst1.setString(2, txtNaziv_katedre.getText() );
			
			pst1.execute();
			JOptionPane.showMessageDialog(null,"Uspješno ste dodali novu katedru!");
		
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
	
	btnDodajKatedru.setBounds(190, 461, 109, 23);
	frame.getContentPane().add(btnDodajKatedru);
	btnDodajKatedru.setIcon(new ImageIcon(getClass().getResource("/StudentSistem/attach.png")));
	
	JButton btnIzmijeniKatedru= new JButton("IZMIJENI");
	btnIzmijeniKatedru.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			
			try {
				
				
				String query="update Katedra set Naziv_katedre= '"+txtNaziv_katedre.getText()+"' where Katedra_id='"+txtKatedra_id.getText()+"'";
				PreparedStatement pst = connection.prepareStatement(query);
				
				pst.execute();
				JOptionPane.showMessageDialog(null,"Uspješno ste izmijenili Katedru!");
			
				pst.close();
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
	
	btnIzmijeniKatedru.setBounds(334, 461, 109, 23);
	frame.getContentPane().add(btnIzmijeniKatedru);
	btnIzmijeniKatedru.setIcon(new ImageIcon(getClass().getResource("/StudentSistem/update icon.png")));
	
	JButton btnObrisiKatedru= new JButton("OBRISI");
	btnObrisiKatedru.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			try {
				
				
				String query="delete from Katedra where Katedra_id='"+txtKatedra_id.getText()+"'";
				PreparedStatement pst = connection.prepareStatement(query);
			
				pst.execute();
				JOptionPane.showMessageDialog(null,"Uspješno ste izbrisali katedru!");
			
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
	btnObrisiKatedru.setBounds(486, 461, 109, 23);
	frame.getContentPane().add(btnObrisiKatedru);
	btnObrisiKatedru.setIcon(new ImageIcon(getClass().getResource("/StudentSistem/delete_16x16.gif")));
			
	JButton btnPonistiKatedru = new JButton("PONISTI");
	btnPonistiKatedru.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			clearFields();
		}
	});
	btnPonistiKatedru.setBounds(632, 461, 109, 23);
	frame.getContentPane().add(btnPonistiKatedru);
	btnPonistiKatedru.setIcon(new ImageIcon(getClass().getResource("/StudentSistem/erase.png")));


	JLabel labelPretraga = new JLabel("Pretraga");
	labelPretraga.setBounds(362, 35, 133, 14);
	frame.getContentPane().add(labelPretraga);
	
	JTextField txtPretragaKatedri = new JTextField();
	txtPretragaKatedri.addKeyListener(new KeyAdapter() {
		@Override
		public void keyReleased(KeyEvent e) {
			
			try {
				
				String query="select * from Katedra where Naziv_katedre=?";
				PreparedStatement pst = connection.prepareStatement(query);
				pst.setString(1,txtPretragaKatedri.getText());
				ResultSet rs=pst.executeQuery();
				tableKatedra.setModel(DbUtils.resultSetToTableModel(rs));
				
}
			
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
	});
	txtPretragaKatedri.setBounds(505, 32, 216, 20);
	frame.getContentPane().add(txtPretragaKatedri);
	txtPretragaKatedri.setColumns(10);
	
	 txtNaziv_katedre = new JTextField();
		txtNaziv_katedre.setBounds(146, 332, 86, 20);
		frame.getContentPane().add(		txtNaziv_katedre);
		txtNaziv_katedre.setColumns(10);
		
		JButton btnPrikaziKatedre = new JButton("PRIKAZI KATEDRE");
		btnPrikaziKatedre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{ 
					String query="select * from Katedra";
				
				PreparedStatement pst = connection.prepareStatement(query);
				ResultSet rs = pst.executeQuery();
				
				tableKatedra.setModel(DbUtils.resultSetToTableModel(rs));
				
			}
			catch(Exception ex) {
				ex.printStackTrace();	}
			
		}});
		
		btnPrikaziKatedre.setBounds(286, 101, 230, 23);
		frame.getContentPane().add(btnPrikaziKatedre);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(286, 153, 485, 281);
		frame.getContentPane().add(scrollPane);
		
		tableKatedra = new JTable();
		tableKatedra.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				DefaultTableModel model=(DefaultTableModel) tableKatedra.getModel();
				int selectedRowIndex = tableKatedra.getSelectedRow();

				txtKatedra_id.setText(model.getValueAt(selectedRowIndex,0).toString());
				txtNaziv_katedre.setText(model.getValueAt(selectedRowIndex,2).toString());
				
			}
		});
		scrollPane.setViewportView(tableKatedra);
		
		JButton btnDodajNovogZaposlenog = new JButton("Dodaj novog zaposlenog");
		btnDodajNovogZaposlenog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

			//	Zaposleni info = new Zaposleni();
			//	Zaposleni.main(null);
				
				Zaposleni window = new Zaposleni();
				window.frame.setVisible(true);	
			
			}
			
		});
		
		btnDodajNovogZaposlenog.setBounds(10, 31, 199, 23);
		frame.getContentPane().add(btnDodajNovogZaposlenog);
		
		 comboBoxZaposleni = new JComboBox();
		comboBoxZaposleni.setToolTipText("");
		comboBoxZaposleni.setBounds(10, 96, 222, 20);
		frame.getContentPane().add(comboBoxZaposleni);
		
		JLabel lblPrikaziZaposlene = new JLabel("Zaposleni");
		lblPrikaziZaposlene.setBounds(85, 76, 78, 14);
		frame.getContentPane().add(lblPrikaziZaposlene);
		
		txtKatedra_id = new JTextField();
		txtKatedra_id.setBounds(51, 219, 86, 20);
		frame.getContentPane().add(txtKatedra_id);
		txtKatedra_id.setColumns(10);
		
		JButton btnNewButton = new JButton("Pocetna strana");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			//	Izvjestaj info = new Izvjestaj();
			//	Izvjestaj.main(null);
				
				
				Izvjestaj window=new Izvjestaj();
				window.frame.setVisible(true);
			}
		});
		btnNewButton.setBounds(764, 31, 126, 23);
		btnNewButton.setIcon(new ImageIcon(getClass().getResource("/StudentSistem/back.png")));
		frame.getContentPane().add(btnNewButton);
		txtKatedra_id.setVisible(true);
		
		refreshTable();
		clearFields();
		
		popuniComboBoxZaposleni();
		
	}
	}
