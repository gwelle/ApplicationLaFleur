package application.modeles;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import application.classesTechniques.ConnexionBD;
import application.entites.Categorie;
import application.entites.Produit;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Olga
 *
 */
public class Modele {
	
	/**
	 * Authentification de l'utilisateur
	 * @param login
	 * @param mdp
	 * @return
	 * @throws SQLException
	 */
	public static boolean seConnecter(String login, String mdp) throws SQLException {
		
		boolean connexionOk  = false;
		
		System.out.println("Modele::seConnecter()");
		
    	Connection connexion = (Connection) ConnexionBD.getConnexion();
				
		String requete = "SELECT login, mdp FROM `admin` WHERE login = ? AND  mdp= ?;";

		PreparedStatement pstmt = (PreparedStatement) connexion.prepareStatement(requete) ;
		pstmt.setString(1,login);
		pstmt.setString(2,mdp);
		
		ResultSet resultat = pstmt.executeQuery();		
		resultat.next();
		
		if(resultat.getString("login").equals(login) && resultat.getString("mdp").equals(mdp)) {
			
			connexionOk = true ;
		}
		
		return connexionOk;
	}
	
	/**
	 * Retourne la liste des Catégories
	 * @return
	 */
	public static ObservableList<Categorie> getMesCacategories() {
		
		System.out.println("Modele::getMesCacategories()");
	
		List<Categorie> lesCategories = new ArrayList<Categorie>() ;
		
		ObservableList<Categorie> listeCategories = null  ;
		
		Connection connexion = (Connection) ConnexionBD.getConnexion();

		try {
			
			Statement stmt = (Statement) connexion.createStatement();
		
			String requete = "SELECT nom FROM categorie ORDER BY nom;";
		
			ResultSet resultat = stmt.executeQuery(requete);
			
			while(resultat.next()){

				String nomCategorie = resultat.getString("nom");
				lesCategories.add(new Categorie(nomCategorie));
				listeCategories = FXCollections.observableArrayList(lesCategories);
			}
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
			e.getErrorCode();
			e.getSQLState();
		}
		
		return listeCategories;
	}
	

	/**
	 * Vérifier si le nom de la Catégorie est identique avec celui sélectionée pendant l'insertion d'un Produit
	 * @param nomcategorie
	 * @return
	 * @throws SQLException
	 */
	public static int verifierNomCategorie(String nomcategorie) throws SQLException {
		
		System.out.println("Modele::verifierNomCategorie()");
		
		Connection connexion = (Connection) ConnexionBD.getConnexion();

		String requete = "select * from categorie where categorie.nom = ?;";
		 
		PreparedStatement pstmt = (PreparedStatement) connexion.prepareStatement(requete) ;
		pstmt.setString(1,nomcategorie);
	
		ResultSet resultat = pstmt.executeQuery();
		resultat.next();
	
		String nom = resultat.getString(1);	
		
		int idCategorie = 0 ;
		
		if(nom.equals(nomcategorie)) {
				
				idCategorie = resultat.getInt("code_type") ;
				System.out.println("..."+idCategorie);
			}
		
		return idCategorie;
	}
	
	/**
	 * Consulter les produits dont le nom contient un mot clé.
	 * @param mc
	 * @return
	 */
	public static List<Produit> consulterListesDesProduits(String mc) {
	
		System.out.println("Modele::consulterListesDesProduits()");
		
		List<Produit> lesProduits = new ArrayList<Produit>() ;
	
		try {
			
		    Connection  connexion = (Connection) ConnexionBD.getConnexion();
			
			String requete = "SELECT nom,prix,quantiteStock,idCategorie "
					+ "FROM produit "
					+ "WHERE nom LIKE ? ;";
			
			PreparedStatement pstmt = (PreparedStatement) connexion.prepareStatement(requete);
			pstmt.setString(1,"%"+mc+"%"); 
			
			ResultSet resultat =pstmt.executeQuery();
		
			while(resultat.next()){
	
				String nomProduit = resultat.getString("nom");
				float prixProduit = resultat.getFloat("prix");
				int qteStockProduit = resultat.getInt("quantiteStock");
				int idCategorieProduit = resultat.getInt("idCategorie");
				
				lesProduits.add(new Produit(nomProduit,prixProduit,qteStockProduit,idCategorieProduit));

			}
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
			e.getErrorCode();
			e.getSQLState();
		}
		
		return lesProduits;
		
	}
	
	/**
	 * Ajouter un produit par rapport à une Catégorie
	 * @param produit
	 * @param nomCategorie
	 */
	public static boolean ajouterUnProduit(Produit produit,String nomCategorie) {
		
		boolean creation = false ;
		
		System.out.println("Modele::ajouterUnProduit()");
		
		Connection connexion = (Connection) ConnexionBD.getConnexion();
		
		String requete  = "INSERT INTO produit(id_type,nom,prix,quantitEStock,idCategorie) VALUES(?,?,?,?,"
				+ "(SELECT categorie.code_type FROM categorie WHERE categorie.nom = ?));" ;
		
		try {
			
			PreparedStatement pstmt = (PreparedStatement) connexion.prepareStatement(requete);
			pstmt.setInt(1, produit.getIdTypeProduit());
			pstmt.setString(2, produit.getNomProduit());
			pstmt.setFloat(3, produit.getPrixProduit());
			pstmt.setInt(4, produit.getQuantiteStockProduit());
			pstmt.setString(5, nomCategorie);
			pstmt.executeUpdate();
			
			creation = true ;
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return creation ;
	}
	
	
	/**
	 * Ajouter une Categorie
	 * @param categorie
	 */
	public static boolean ajouterUneCategorie(Categorie categorie) {
		
		boolean creation = false ;
		
		System.out.println("Modele::ajouterUneCategorie()");
		
		Connection connexion = (Connection) ConnexionBD.getConnexion();
		
		String requete = "INSERT INTO categorie(code_type,nom) VALUES(?,?);" ;
		
		try {
			
			PreparedStatement pstmt = (PreparedStatement) connexion.prepareStatement(requete);
			pstmt.setInt(1, categorie.getCode_type());
			pstmt.setString(2, categorie.getNom());
			pstmt.executeUpdate();
			
			creation = true ;

		} 
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return creation ; 
	}
	
	/**
	 * Retourne la liste des Produits
	 * @return
	 */
	public static ObservableList<Produit> getMesProduits() {
		
		System.out.println("Modele::getMesProduits()");
	
		List<Produit> lesProduits = new ArrayList<Produit>() ;
		
		ObservableList<Produit> listeProduits = null  ;
		
		Connection connexion = (Connection) ConnexionBD.getConnexion();

		try {
			
			Statement stmt = (Statement) connexion.createStatement();
		
			String requete = "SELECT * FROM produit ORDER BY idCategorie;";
		
			ResultSet resultat = stmt.executeQuery(requete);
			
			while(resultat.next()){
				
				String nomProduit = resultat.getString("nom");
				float prixProduit = resultat.getFloat("prix");
				int qteStockProduit = resultat.getInt("quantiteStock");
				int idCat = resultat.getInt("idCategorie");

				lesProduits.add(new Produit(nomProduit,prixProduit,qteStockProduit,idCat));
				listeProduits = FXCollections.observableArrayList(lesProduits);
			}
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
			e.getErrorCode();
			e.getSQLState();
		}
		
		return listeProduits;
	}
	
	/**
	 * Retourne la liste des Produits en fonction du nom de la Categorie
	 * @param nomCategorie
	 * @return
	 */
	public  static ObservableList<Produit> getProduitsCategorie(String nomCategorie) {
		
		System.out.println("Modele::geetProduitsCategorie()");
	
		List<Produit> lesProduits = new ArrayList<Produit>() ;
		ObservableList<Produit> listeProduits = null;
		
		Connection connexion = (Connection) ConnexionBD.getConnexion();

		try {
			
				String requete = "SELECT  p.nom, p.prix , p.quantiteStock, p.idCategorie\r\n" + 
						"FROM produit p INNER JOIN categorie c ON c.code_type = p.idCategorie\r\n" + 
						"WHERE c.nom = ?";

				
				PreparedStatement pstmt = (PreparedStatement) connexion.prepareStatement(requete);
				pstmt.setString(1, nomCategorie);
				
				ResultSet resultat = pstmt.executeQuery();
				
				while(resultat.next()){
					
					String nomProduit = resultat.getString("p.nom");
					float prixProduit = resultat.getFloat("p.prix");
					int qteStockProduit = resultat.getInt("p.quantiteStock");
					int idCat = resultat.getInt("p.idCategorie");
	
					lesProduits.add(new Produit(nomProduit,prixProduit,qteStockProduit,idCat));
					listeProduits = FXCollections.observableArrayList(lesProduits);
			}
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
			e.getErrorCode();
			e.getSQLState();
		}
		
		return listeProduits;
	}
}