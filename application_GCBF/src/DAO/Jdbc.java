package DAO; 
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.SQLException; 
public class Jdbc { 
	public static void main(String[] args) throws ClassNotFoundException ,SQLException{
		{ Class.forName("com.mysql.cj.jdbc.Driver"); 
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/banque_db","root","heiji123"); 
		System.out.println("Heiji ! tu as bien conncte avec ta BDD"); } 
}}