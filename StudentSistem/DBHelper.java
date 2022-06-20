package StudentSistem;
import java.sql.*;

import javax.swing.JLabel;


public class DBHelper {

	public static ResultSet naziviZaposlenih() {
		
		ResultSet rs = null;
		Connect sc = new Connect();
		Connection connection = sc.java_db();
				
		connection=Connect.java_db();
		
		try {
			
			String query="select zaposleni_id, ime, prezime from Zaposleni";
			PreparedStatement pst = connection.prepareStatement(query);
			rs = pst.executeQuery();
			
		} 
		catch(Exception e) {
			System.out.println(e);	
			return null;
		}
		
		return rs;
	}
public static ResultSet  naziviKatedre() {
		
		ResultSet rs = null;
		Connect sc = new Connect();
		Connection connection = sc.java_db();
				
		connection=Connect.java_db();
		
		try {
			
			String query1="select naziv_katedre from Katedra";
			PreparedStatement pst = connection.prepareStatement(query1);
			rs = pst.executeQuery();
			
		} 
		catch(Exception e) {
			System.out.println(e);	
			return null;
		}
		
		return rs;
	}
public static ResultSet naziviProjekta() {
	
	ResultSet rs = null;
	Connect sc = new Connect();
	Connection connection = sc.java_db();
			
	connection=Connect.java_db();
	
	try {
		
		String query="select naziv_projekta from Projekti";
		PreparedStatement pst = connection.prepareStatement(query);
		rs = pst.executeQuery();
		
	} 
	catch(Exception e) {
		System.out.println(e);	
		return null;
	}
	
	return rs;
}
public static ResultSet naziviKurseva()
{
	
	ResultSet rs1 = null;
	Connect sc = new Connect();
	Connection connection = sc.java_db();
			
	connection=Connect.java_db();
	
	try {
		
		String query="select projekt_id, naziv_projekta from Projekti";
		PreparedStatement pst = connection.prepareStatement(query);
		rs1= pst.executeQuery();
		
	} 
	catch(Exception e) {
		System.out.println(e);	
		return null;
	}
	
	return rs1;
}
public static ResultSet naziviStudenata()
{
	
	ResultSet rs2 = null;
	Connect sc = new Connect();
	Connection connection = sc.java_db();
			
	connection=Connect.java_db();
	
	try {
		
		String query="select ime + ' ' + prezime + ' '  as ImePrezime, studen_id from Student order by prezime, ime";
		PreparedStatement pst = connection.prepareStatement(query);
		rs2= pst.executeQuery();
		
		
	} 
	catch(Exception e) {
		System.out.println(e);	
		return null;
	}
	
	return rs2;
}

public static ResultSet tabelaIzvjestaj()
{
	
	ResultSet rs2 = null;
	Connect sc = new Connect();
	Connection connection = sc.java_db();
			
	connection=Connect.java_db();
	
	
	try {
		
		String query="select kurs_id,opis,ocjena from Izvjestaji where izvjestaj_id=";
		PreparedStatement pst = connection.prepareStatement(query);
		rs2= pst.executeQuery();
		
		
	} 
	catch(Exception e) {
		System.out.println(e);	
		return null;
	}
	
	return rs2;
}
public static ResultSet ocjena(String naziv) {
	
	
	ResultSet rsc = null;
	Connect sc = new Connect();
	Connection connection = sc.java_db();
			
	connection=Connect.java_db();
	
	try {
		
		String query="select ocjena from Izvjestaji where naziv = "+ naziv;
		PreparedStatement pst = connection.prepareStatement(query);
		rsc = pst.executeQuery();
		
	} 
	catch(Exception e) {
		System.out.println(e);	
		return null;
	}
	
	return rsc;
}
public static int kreirajNoviIzvjestaj(int kurs_id)
{
	
	int izvjestaj_id = 0;
	
	
	
	ResultSet rsc = null;
	Connect sc = new Connect();
	Connection connection = sc.java_db();
			
	connection=Connect.java_db();
	
	try {
		
		String query="insert into Izvjestaji(kurs_id, opis, ocjena) values( ?, ?, ?)";
		PreparedStatement pst = connection.prepareStatement(query);
		pst.setInt(1, kurs_id);
		pst.executeUpdate();
		System.out.println("izvrsen: " + query);
		
		String query2="select max(izvjestaj_id) as izvjestaj_id from Izvjestaj";
		PreparedStatement pst2 = connection.prepareStatement(query2);
		
		rsc = pst2.executeQuery();
		System.out.println("izvrsen: " + query2);
		rsc.next();
		izvjestaj_id = rsc.getInt("RacunId");
		
	} 
	catch(Exception e) {
		e.printStackTrace();
		
		return 0;
	}
	/*@Override
	public String toString() {
		return  izvjestaj_id;*/
	
	return izvjestaj_id;
	
	}
public static int dodajStavkuIzvjestaju(int izvjestaj_id, int kurs_id, int ocjena, char opis ) {
	
	
	int stavkaizvjestaj_id=0;
	ResultSet rsc1 = null;
	ResultSet rsc2=null;
	Connect sc = new Connect();
	Connection connection = sc.java_db();
			
	connection=Connect.java_db();
	
	try {
		String query="insert into Izvjestaji(izvjestaj_id, kurs_id, opis, ocjena) values( ?, ?, ?, ?)";
		PreparedStatement pst = connection.prepareStatement(query);
		pst.setInt(1, izvjestaj_id);
		pst.setInt(2, kurs_id);
		pst.setInt(3, opis);
		pst.setInt(4, ocjena);

		pst.executeUpdate();
		
		System.out.println("izvrsen: " + query);
		System.out.println("izvjestaj_id:"+ izvjestaj_id);
		System.out.println("kurs_id:"+ kurs_id);
		System.out.println("opis:"+ opis);
		System.out.println("ocjena:"+ ocjena);
	}
	catch(Exception e) {
		e.printStackTrace();
		
		return 0;
	}
	return izvjestaj_id;
}
public static ResultSet stavkeIzvjestaja(int izvjestaj_id) {
	
	ResultSet rs = null;
	Connect sc = new Connect();
	Connection connection = sc.java_db();
			
	connection=Connect.java_db();
			
			try {
				String query="select i.izvjestaj_id, s.Naziv as ime, k.Naziv as naziv_kursa \r\n" + 
			"from Izvjestaji i \r\n" + 
			"left join Student s  on s.student_id = k.student_id\r\n" + 
			"left join Kurs k on k.kurs_id = i.kurs_id\r\n" + 
			"where i.izvjestaj_id = ? order by izvjestaj_id";
	PreparedStatement pst = connection.prepareStatement(query);
	pst.setInt(1, izvjestaj_id);
	
	rs= pst.executeQuery();
	
	System.out.println("izvrsen query stavka izvjestaja za izvjestaj" + izvjestaj_id);
	
	
} 
catch(Exception e) {
	System.out.println(e);	
	return null;
}

return rs;


}
	
	
	



	
}
