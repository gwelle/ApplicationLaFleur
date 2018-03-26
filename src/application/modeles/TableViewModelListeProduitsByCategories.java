/**
 * 
 */
package application.modeles;

import application.entites.Produit;
import application.vues.Vues;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.TableColumn;

/**
 * @author Olga
 *
 */
public class TableViewModelListeProduitsByCategories {
	
	private TableColumn<Produit, String> colonneNom = new TableColumn<Produit,String>("Nom");
	private TableColumn<Produit, Float> colonnePrix = new TableColumn<Produit,Float>("Prix");
	private TableColumn<Produit, Integer> colonneQteStock = new TableColumn<Produit,Integer>("Quantité du Stock");
	private TableColumn<Produit, Integer> colonneIdCategorie = new TableColumn<Produit,Integer>("N°Catégorie");
	
	Vues vue ;

	/**
	 * 
	 * @param vues
	 */
	public TableViewModelListeProduitsByCategories(Vues vues) {
		System.out.println("TableViewModelListeProduitsByCategories::TableViewModelListeProduitsByCategories()");
		this.vue = vues ;
		
	}


	/**
	 * @return the colonneNom
	 */
	public TableColumn<Produit, String> getColonneNom() {
		
        colonneNom.setMinWidth(248);
		colonneNom.setStyle("-fx-alignment: CENTER;");
        colonneNom.setCellValueFactory(param -> {
        	 Produit produit = param.getValue() ;
        return new SimpleObjectProperty<String>(produit.getNomProduit());
        });
		return colonneNom;
	}

	/**
	 * @return the colonnePrix
	 */
	public TableColumn<Produit, Float> getColonnePrix() {
		
		colonnePrix.setMinWidth(249);
		colonnePrix.setStyle("-fx-alignment: CENTER;");
        colonnePrix.setCellValueFactory(param -> {
        	Produit produit = param.getValue() ;
        return new SimpleObjectProperty<Float>(produit.getPrixProduit());
        });
		
        return colonnePrix;
	}
	/**
	 * @return the colonneQteStock
	 */
	public TableColumn<Produit, Integer> getColonneQteStock() {
		
		colonneQteStock.setMinWidth(249);
		colonneQteStock.setStyle("-fx-alignment: CENTER;");

        colonneQteStock.setCellValueFactory(param ->{
        	Produit produit = param.getValue() ;
        return new SimpleObjectProperty<Integer>(produit.getQuantiteStockProduit());
        });
		
        return colonneQteStock;
	}
	/**
	 * @return the colonneIdCategorie
	 */
	public TableColumn<Produit, Integer> getColonneIdCategorie() {
		
		colonneIdCategorie.setMinWidth(249);
		colonneIdCategorie.setStyle("-fx-alignment: CENTER;");
        colonneIdCategorie.setCellValueFactory(param->{
        	Produit produit = param.getValue() ;
       return new SimpleObjectProperty<Integer>(produit.getNumCategorieProduit());
        });
        
		return colonneIdCategorie;
	}	

}
