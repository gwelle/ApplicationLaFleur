/**
 * 
 */
package application.controleurs;

import application.entites.Categorie;
import application.entites.Produit;
import application.modeles.Modele;
import application.vues.Vues;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * @author Olga
 *
 */
public class ControleurVueSelectioneeUneCategorie implements EventHandler<ActionEvent> {
	
	Vues vueSelectioneeUneCategorie ;
	Stage fenetre ;

	/**
	 * Constructeur
	 * @param vue
	 */
	public ControleurVueSelectioneeUneCategorie(Vues vue,Stage fenetre) {
		System.out.println("ControleurVueSelectioneeUneCategorie::ControleurVueSelectioneeUneCategorie()");
		this.vueSelectioneeUneCategorie = vue;
		this.fenetre = fenetre ;
		this.ecouterEvenements();
	}

	/**
	 * Ecouter les Evenements
	 */
	private void ecouterEvenements() {
		System.out.println("ControleurVueSelectioneeUneCategorie::ecouterEvenements()");
		this.vueSelectioneeUneCategorie.getUneCategorie().addEventHandler(ActionEvent.ACTION,this);
		this.vueSelectioneeUneCategorie.getbValiderUneCategorie().addEventHandler(ActionEvent.ACTION,this);
		this.vueSelectioneeUneCategorie.getbRetourAccueilPage().addEventHandler(ActionEvent.ACTION,this);

	}

	@Override
	public void handle(ActionEvent event) {
		
		 System.out.println("ControleurVueSelectioneeUneCategorie::handle()");
		
		 if(event.getSource() == vueSelectioneeUneCategorie.getbRetourAccueilPage()) {
			
			this.fenetre.setScene(this.vueSelectioneeUneCategorie.getSceneVuePrincipal());
			this.fenetre.setTitle("Bienvenue sur l'application LaFleur");
			this.vueSelectioneeUneCategorie.activerItemsMenus();
			this.vueSelectioneeUneCategorie.getComboLesCategories().setValue(null);

		}
		 
		 else if(event.getSource() == vueSelectioneeUneCategorie.getbValiderUneCategorie()) {
			 
			 	this.fenetre.setScene(vueSelectioneeUneCategorie.getSceneVueConsulterLesProduitsByCategories());
				this.fenetre.setTitle("La liste des Produits selon une Catégorie sélectionée");
				
												
				ComboBox<Categorie> comboLesCategories = vueSelectioneeUneCategorie.getComboLesCategories();
				String comboString = comboLesCategories.getValue().getNom();
				
				TableView<Produit> tvProduits = vueSelectioneeUneCategorie.getTvProduitsByCategories();
				tvProduits.setItems(Modele.getProduitsCategorie(comboString));
				
				comboLesCategories.setValue(null);
			}
	}
}