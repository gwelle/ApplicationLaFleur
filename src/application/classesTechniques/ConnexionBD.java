package application.classesTechniques;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * @author Olga
 *
 */
public class ConnexionBD {
	
	private static String pilote = "com.mysql.jdbc.Driver";
	
	private static String url = "jdbc:mysql://localhost:3306/lafleur?useUnicode=yes&characterEncoding=UTF-8";
	private static String user = "root";
	private static String mdp = "";
	
	private static Connection connexion = null ;
	
	/**
	 * Constructeur
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private ConnexionBD() throws ClassNotFoundException, SQLException{
		
		System.out.println("ConnexionBD::ConnexionBD()");

		
		try{
	
			//Chargement du pilote	
			Class.forName(pilote);
			System.out.println("Connexion au serveur BDD");
		}
		
		catch(ClassNotFoundException e){
			e.printStackTrace();
			System.out.println("Vous n'avez pas réussi à vous Coonecter");
		}
		
		try{
			
			//Ouverture de la connexion
			connexion  = DriverManager.getConnection(url,user,mdp);
		}
		
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Une Instance de connexion unique
	 * @return the connexion
	 */
	public static Connection getConnexion() {
		System.out.println("ConnexionBD::getConnexion()");
		
		if(connexion == null){
			 try {
				new ConnexionBD();
			} 
			 
			 catch (ClassNotFoundException e) {
				e.printStackTrace();
			} 
			 
			 catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return connexion;
	}
}