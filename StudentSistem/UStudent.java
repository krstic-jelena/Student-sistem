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

public class UStudent {


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
					UStudent window = new UStudent();
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
	private JComboBox comboBoxUKursevi;
	
	public void popuniComboBoxUKursevi()
	{	

		ResultSet rsUKursevi = DBHelper.naziviKurseva();
		try {
			while(rsUKursevi.next())
			{	
				comboBoxUKursevi.addItem(rsUKursevi.getString("Naziv"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		comboBoxUKursevi.setSelectedIndex(-1);
		
	}
	
	
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
		
	
	
	public UStudent() {
		connection= Connect.java_db();
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(132, 120, 120));
		frame.setBounds(100, 100, 916, 533);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Studenti");
		
		JLabel labelPretraga = new JLabel("Pretrazi studente");
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
			txtime.setVisible(false);
			
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
				txtprezime.setVisible(false);
				
				 txtdatum_rodjenja = new JTextField();
					txtdatum_rodjenja.setBounds(146, 358, 86, 20);
					frame.getContentPane().add(txtdatum_rodjenja);
					txtdatum_rodjenja.setColumns(10);
					txtdatum_rodjenja.setVisible(false);

					 txttelefon = new JTextField();
					txttelefon.setBounds(146, 295, 86, 20);
					frame.getContentPane().add(txttelefon);
					txttelefon.setColumns(10);
					txttelefon.setVisible(false);
					
					 txtpol = new JTextField();
						txtpol.setBounds(146, 141, 86, 20);
						frame.getContentPane().add(txtpol);
						txtpol.setColumns(10);
						txtpol.setVisible(false);
				
	
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
		btnPrikaziStudente.setBounds(50, 84, 166, 23);
		frame.getContentPane().add(btnPrikaziStudente);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 120, 800, 300);
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
		
		JButton btnPrikaziKurseve = new JButton("Prikazi ponudjene kurseve");
		btnPrikaziKurseve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

			//	UKursevi info = new uKursevi();
		
				
				UKursevi window = new UKursevi();
				window.frame.setVisible(true);	
			
			}
			
		});
		btnPrikaziKurseve.setBounds(600, 84, 199, 23);
		frame.getContentPane().add(btnPrikaziKurseve);
		
		 comboBoxUKursevi = new JComboBox<String>();
			
			

		
		
		 comboBoxSortiranje = new JComboBox();
			comboBoxSortiranje.setModel(new DefaultComboBoxModel(new String[] {"ime", "prezime", "student_id"}));
			comboBoxSortiranje.setBounds(580, 11, 116, 20);
			frame.getContentPane().add(comboBoxSortiranje);
			
			
			txtstudent_id = new JTextField();
			txtstudent_id.setBounds(10, 85, 86, 20);
			frame.getContentPane().add(txtstudent_id);
			txtstudent_id.setColumns(10);
			txtstudent_id.setVisible(false);
			refreshTable();
		
			popuniComboBoxUKursevi();
		}
	}