/**
 * 
 */
package application.entites;

import javafx.beans.property.*;

/**
 * @author Olga
 *
 */
public class Produit {
	private int idType ;
	private StringProperty nom ;
	private FloatProperty prix ;
	private IntegerProperty quantiteStock ;
	private IntegerProperty idCategorie ;

	/**
	 * Constructeur
	 * @param nom
	 * @param prix
	 * @param qteStock
	 * @param idCategorie
	 */
	public Produit(String nom, float prix, int qteStock, int idCategorie) {
		this.nom = new SimpleStringProperty(nom);
		this.prix = new SimpleFloatProperty(prix);
		this.quantiteStock = new SimpleIntegerProperty(qteStock);
		this.idCategorie = new SimpleIntegerProperty(idCategorie);
	}

	/**
	 * @return the nom
	 */
	public StringProperty getNom() {
		return nom;
	}

	/**
	 * @return the prix
	 */
	public FloatProperty getPrix() {
		return prix;
	}

	/**
	 * @return the quantiteStock
	 */
	public IntegerProperty getQuantiteStock() {
		return quantiteStock;
	}

	/**
	 * @return the idCategorie
	 */
	public IntegerProperty getIdCategorie() {
		return idCategorie;
	}
	
	/**
	 * @return the idTypeProduit
	 */
	public int getIdTypeProduit() {
		return idType;
	}
	
	
	public String getNomProduit() {
		return nom.get();
	}
	
	public float getPrixProduit() {
		return prix.get();
	}
	
	public int getQuantiteStockProduit() {
		return quantiteStock.get();
	}
	
	public int getNumCategorieProduit() {
		return idCategorie.get();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Produit [nom=" + nom + ", prix=" + prix + ", quantiteStock=" + quantiteStock + ", idCategorie="
				+ idCategorie + "]";
	}
}