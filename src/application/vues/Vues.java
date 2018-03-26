package application.vues;

import application.controleurs.*;
import application.entites.*;
import application.modeles.*;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Vues extends Application  {
	
	//VUE PRINCIPAL
	MenuBar barreMenus = new MenuBar();
	
	Menu menuFichier = new Menu("Fichier");
	MenuItem seConnecter = new MenuItem("Se Connecter");
	MenuItem seDeconnecter = new MenuItem("Se Déconnecter");
	SeparatorMenuItem separateur = new SeparatorMenuItem();
	MenuItem quitter = new MenuItem("Quitter");
	
	Menu menuAjout = new Menu("Insertion");
	MenuItem produit = new MenuItem("Produit");
	MenuItem categorie = new MenuItem("Catégorie");
	MenuItem categorieproduit = new MenuItem("Catégorie/Produit");
	
	Menu menuConsultation = new Menu("Consultation");
	MenuItem categories = new MenuItem("Les Catégories");
	MenuItem uneCategorie = new MenuItem("Une Catégorie contenant ses Produits");
	SeparatorMenuItem separateur1 = new SeparatorMenuItem();
	MenuItem lesProduitsCodeCat = new MenuItem("Les Produits sachant N°Cat");
	MenuItem lesProduitsMC = new MenuItem("Les Produits dont le nom commence par un MC");
	
	//VUES CONNECTER
	Label lblTitre = new Label("Veuillez vous connecter : ");
	Label lblLogin = new Label("Identifiant : ");
	Label lblMdp = new Label("Mot de Passe : ");
	TextField tfLogin = new TextField();
	PasswordField pfMdp = new PasswordField();
	Button bValiderConnexion = new Button("Valider");
	Button bAnnulerConnexion = new Button("Annuler");
	Button bRetourViewAccueil = new Button("Retour");
	HBox lesBoutons = new HBox(12);
	
	//VUE LISTE CATEGORIES
	ListView<Categorie> listViewCategorie = new ListView<Categorie>();
	Button bRetourPageAccueil = new Button("Retour");
		
	//VUE INSERER CATEGORIE
	Label lblNomCategorie = new Label("Nom de la Categorie : ");
	TextField tfNomCategorie = new TextField();
	Button bValiderCategorie = new Button("Valider");
	Button bAnnulerCategorie = new Button("Annuler");
	Button bRetourPagePrincipal = new Button("Retour");
	
	//VUE INSERER PRODUIT
	Label lblProduit = new Label("Nom du Produit : ");
	Label lblPrix = new Label("Prix du Produit : ");
	Label lblQteStock = new Label("Quantité du Stock : ");
	TextField tfNomProduit = new TextField();
	TextField tfPrix = new TextField();
	TextField tfQteStock = new TextField();
	Button bValiderProduit = new Button("Valider");
	Button bAnnulerProduit = new Button("Annuler");
	Button bRetourFirstVue = new Button("Retour");
	Label lblNomCatChoisi = new Label("Nom de la Catégorie : ");
	ComboBox<Categorie> ComboCategories = new ComboBox<Categorie>();
	
	//VUE CONSULTER CATEGORIE CONTENANT SES PRODUITS
	ComboBox<Categorie> ComboLesCategories = new ComboBox<Categorie>();
	Button bValiderUneCategorie = new Button("Valider");
	Button bRetourAccueilPage = new Button("Retour");
	
	//VUE LISTE PRODUITS APRES SELECTION D'UNE CATEGORIE AVEC TABLEMODEL
	TableView<Produit> tvProduitsByCategories = new TableView<Produit>();
	TableViewModelListeProduitsByCategories modelTablePCat = new TableViewModelListeProduitsByCategories(this);
	Button bRetourPrincipalPageAccueil = new Button("Retour") ;
		
	//VUE CONSULTER LA LISTE DES PRODUITS CONNAISSANT LE N°CATEGORIE AVEC TABLEMODEL
	TableView<Produit> tvProduits = new TableView<Produit>();
	TableViewModelListeProduits modelTableListeP = new TableViewModelListeProduits(this);
	Button bRetourPrincipalPage = new Button("Retour") ;
		
	//VUE RECHERCHER DES PRODUITS AVEC UN MOT CLE AVEC TABLEMODEL
	TableView<Produit> tvProduitsMC = new TableView<Produit>();
	TableViewModelListeProduitsMC modelTableListeProduitsMC = new TableViewModelListeProduitsMC(this);
	TextField tfRechercheMC = new TextField();
	Button bRetourPP = new Button("Retour") ;
	
	//FENETRE COURANT
	Stage window  = new Stage();
	
	//LAYOUT
	StackPane layoutVuePrincipal = new StackPane(); 
	GridPane layoutVueConnecter = new GridPane();
	HBox layoutVueListeCategories = new HBox(20);
	GridPane layoutVueInsererCategorie = new GridPane();
	GridPane layoutVueInsererProduit = new GridPane();
	GridPane layoutVueConsulterUneCategorie = new GridPane();
	VBox layoutVueConsulterLesProduits = new VBox(12);
	VBox layoutVueConsulterLesProduitByCategorie = new VBox(12);
	VBox layoutVueConsulterLesProduitsMC = new VBox(12);
	
	//SCENES
	Scene sceneVuePrincipal = new Scene(layoutVuePrincipal,1000,350);
	Scene sceneVueAuthentification = new Scene(layoutVueConnecter,1000,350);
	Scene sceneVueListeCategories = new Scene(layoutVueListeCategories,1000,350);
	Scene sceneVueInsererCategorie = new Scene(layoutVueInsererCategorie,1000,350);
	Scene sceneVueInsererProduit = new Scene(layoutVueInsererProduit,1000,350);
	Scene sceneVueConsulterUneCategorie = new Scene(layoutVueConsulterUneCategorie,1000,350);
	Scene sceneVueConsulterLesProduits = new Scene(layoutVueConsulterLesProduits,1000,350);
	Scene sceneVueConsulterLesProduitsByCategories = new Scene(layoutVueConsulterLesProduitByCategorie,1000,350);
	Scene sceneVueConsulterLesProduitsMC = new Scene(layoutVueConsulterLesProduitsMC,1000,350);

	//CONTROLEURS
	ControleurVuePrincipal controleurPrincipal ;
	ControleurVueAuthentification controleurAuthentification ;
	ControleurVueConsulterLesCategories controleurVueListeCategories;
	ControleurVueInsererCategorie controleurVueInsererCategorie ;
	ControleurVueInsererProduit controleurVueInsererProduit ;
	ControleurVueSelectioneeUneCategorie controleurConsulterUneCategorie;
	ControleurConsulterLesProduits controleurConsulterLesProduits;
	ControleurVueConsulterLesProduitsByCategories controleurVueListesProduitsByCategories ;
	ControleurVueListeProduitsMC controleurVueListeProduitsMc ;
	
	
	@Override
	public void start(Stage primaryStage) {
		
		System.out.println("Vues::Start()");
		
		this.window = primaryStage ;
		
		controleurPrincipal = new ControleurVuePrincipal(this, primaryStage);
		controleurAuthentification = new ControleurVueAuthentification(this, primaryStage);
		controleurVueListeCategories = new ControleurVueConsulterLesCategories(this, primaryStage);
		controleurVueInsererCategorie = new ControleurVueInsererCategorie(this, primaryStage);
		controleurVueInsererProduit = new ControleurVueInsererProduit(this, primaryStage);
		controleurConsulterUneCategorie = new ControleurVueSelectioneeUneCategorie(this,primaryStage);
		controleurConsulterLesProduits = new ControleurConsulterLesProduits(this,primaryStage);
		controleurVueListesProduitsByCategories = new ControleurVueConsulterLesProduitsByCategories(this, primaryStage);
		controleurVueListeProduitsMc = new ControleurVueListeProduitsMC(this,primaryStage);

		try {
			
			layoutVuePrincipal.setAlignment(Pos.TOP_CENTER);
			layoutVuePrincipal.setStyle("-fx-background-color: BEIGE;"); 
			layoutVuePrincipal.getChildren().add(barreMenus);
			
			window.setScene(sceneVuePrincipal);
			window.setTitle("Bienvenue sur la Page d\'accueil");
			window.show();

			creerBarMenus(primaryStage);
			this.desactiverItemsMenus();
			creerVueAuthentification(primaryStage);
			creerVueListeCategories();
			creerVueInsererCategorie();
			creerVueInsererProduit();
			creerVueChoisirUneCategorie();
			creerVueListesProduits();
			creerTableViewProduitsByCategorie();
			creerVueListeProduitsMC();
		} 
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * Créer la Table View des Produits nous permettant de rechercher des Produits par Mot Clé
	 */
	private void creerVueListeProduitsMC() {
		System.out.println("Vues::creerVueListesProduitsMC");
		tvProduitsMC.getColumns().add(modelTableListeProduitsMC.getColonneNom());
		tvProduitsMC.getColumns().add(modelTableListeProduitsMC.getColonnePrix());
		tvProduitsMC.getColumns().add(modelTableListeProduitsMC.getColonneQteStock());
		tvProduitsMC.getColumns().add(modelTableListeProduitsMC.getColonneIdCategorie());
		tvProduitsMC.setItems(Modele.getMesProduits());
		tvProduitsMC.setEditable(false);
		
		//Empecher le tri automatique sur les colonnes
		modelTableListeProduitsMC.getColonneNom().setSortable(false);
		modelTableListeProduitsMC.getColonnePrix().setSortable(false);
		modelTableListeProduitsMC.getColonneQteStock().setSortable(false);
		modelTableListeProduitsMC.getColonneIdCategorie().setSortable(false);

		//les colonnes sont redimensionnées pour occuper toute la largeur de la table.
		tvProduits.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		layoutVueConsulterLesProduitsMC.setAlignment(Pos.CENTER);
		layoutVueConsulterLesProduitsMC.getChildren().addAll(tfRechercheMC,tvProduitsMC,bRetourPP);
		layoutVueConsulterLesProduitsMC.setStyle("-fx-background-color: BEIGE;"); 
		
		tfRechercheMC.setMinSize(50, 25);
		tfRechercheMC.setMaxSize(200, 25);
	}

	
	/**
	 * Créer la Table View des Produits après avoir sélectionée une catégorie
	 */
	private void creerTableViewProduitsByCategorie() {
		System.out.println("Vues::creerTableViewProduitsByCategories()");
		tvProduitsByCategories.getColumns().add(modelTablePCat.getColonneNom());
		tvProduitsByCategories.getColumns().add(modelTablePCat.getColonnePrix());
		tvProduitsByCategories.getColumns().add(modelTablePCat.getColonneQteStock());
		tvProduitsByCategories.getColumns().add(modelTablePCat.getColonneIdCategorie());
		
		//La table n'est pas éditable
		tvProduitsByCategories.setEditable(false);
		
		//Empecher le tri automatique sur les colonnes
		//Empecher le tri automatique sur les colonnes
		modelTablePCat.getColonneNom().setSortable(false);
		modelTablePCat.getColonnePrix().setSortable(false);
		modelTablePCat.getColonneQteStock().setSortable(false);
		modelTablePCat.getColonneIdCategorie().setSortable(false);
		
		//les colonnes sont redimensionnées pour occuper toute la largeur de la table.
		tvProduitsByCategories.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		layoutVueConsulterLesProduitByCategorie.setAlignment(Pos.CENTER);
		layoutVueConsulterLesProduitByCategorie.getChildren().add(tvProduitsByCategories);
		layoutVueConsulterLesProduitByCategorie.getChildren().add(bRetourPrincipalPageAccueil);
		layoutVueConsulterLesProduitByCategorie.setStyle("-fx-background-color: BEIGE;"); 

	}
	/**
	 * Créer la Vue de la liste Des Produits
	 */
	private void creerVueListesProduits() {
		
		System.out.println("Vues::creerVueListesProduits");
		tvProduits.getColumns().add(modelTableListeP.getColonneNom());
		tvProduits.getColumns().add(modelTableListeP.getColonnePrix());
		tvProduits.getColumns().add(modelTableListeP.getColonneQteStock());
		tvProduits.getColumns().add(modelTableListeP.getColonneIdCategorie());
		tvProduits.setItems(Modele.getMesProduits());
		tvProduits.setEditable(false);
		
		//Empecher le tri automatique sur les colonnes
		modelTableListeP.getColonneNom().setSortable(false);
		modelTableListeP.getColonnePrix().setSortable(false);
		modelTableListeP.getColonneQteStock().setSortable(false);
		modelTableListeP.getColonneIdCategorie().setSortable(false);

		//les colonnes sont redimensionnées pour occuper toute la largeur de la table.
		//tvProduits.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		layoutVueConsulterLesProduits.setAlignment(Pos.CENTER);
		layoutVueConsulterLesProduits.getChildren().addAll(tvProduits,bRetourPrincipalPage);
		layoutVueConsulterLesProduits.setStyle("-fx-background-color: BEIGE;"); 

		
		bRetourPrincipalPage.setPrefSize(100, 100);
	}

	/**
	 * Créer la Vue de Selection d'une Catégorie
	 */
	private void creerVueChoisirUneCategorie() {
		
		System.out.println("Vues::creerVueConsulterUneCategorie()");
		layoutVueConsulterUneCategorie.setAlignment(Pos.CENTER);
		layoutVueConsulterUneCategorie.setPadding(new Insets(25, 25, 25, 25));

		Text scenetitle = new Text("Selectionée une Catégorie pour voir ses Produits");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		layoutVueConsulterUneCategorie.add(scenetitle, 0, 0, 2, 1);
		
		ComboLesCategories.setItems(Modele.getMesCacategories());
		layoutVueConsulterUneCategorie.add(ComboLesCategories, 0, 1);
		
		HBox boxBoutons = new HBox(15);
		boxBoutons.setAlignment(Pos.BOTTOM_CENTER);
		boxBoutons.getChildren().addAll(bValiderUneCategorie,bRetourAccueilPage);
		layoutVueConsulterUneCategorie.add(boxBoutons,0 , 2);
		
		this.bValiderUneCategorie.setPrefSize(60, 20);
		
		layoutVueConsulterUneCategorie.setHgap(30);
		layoutVueConsulterUneCategorie.setVgap(30);
		layoutVueConsulterUneCategorie.setStyle("-fx-background-color: BEIGE;"); 

	}

	/**
	 * Créer la Vue d'Insertion d'un Nouveau Produit
	 */
	private void creerVueInsererProduit() {
		
		System.out.println("Vues::creerVueInsererProduit()");
		layoutVueInsererProduit.setAlignment(Pos.CENTER);
		layoutVueInsererProduit.setHgap(10);
		layoutVueInsererProduit.setVgap(10);
		layoutVueInsererProduit.setPadding(new Insets(25, 25, 25, 25));

		Text scenetitle = new Text("Inserer votre nouveau produit");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		layoutVueInsererProduit.add(scenetitle, 0, 0, 2, 1);

		layoutVueInsererProduit.add(lblProduit, 0, 1);
		layoutVueInsererProduit.add(tfNomProduit, 1, 1);

		layoutVueInsererProduit.add(lblPrix, 0, 2);
		layoutVueInsererProduit.add(tfPrix, 1, 2);
		
		layoutVueInsererProduit.add(lblQteStock, 0, 3);
		layoutVueInsererProduit.add(tfQteStock, 1, 3);
		
		ComboCategories.setItems(Modele.getMesCacategories());
		layoutVueInsererProduit.add(lblNomCatChoisi, 0, 4);
		layoutVueInsererProduit.add(ComboCategories, 1, 4);
		
		HBox boxBoutons = new HBox(15);
		boxBoutons.setAlignment(Pos.BOTTOM_CENTER);
		boxBoutons.getChildren().addAll(bValiderProduit,bAnnulerProduit,bRetourFirstVue);
		layoutVueInsererProduit.add(boxBoutons,1 , 5);
		
		layoutVueInsererProduit.setStyle("-fx-background-color: BEIGE;"); 

	}

	/**
	 * Créer la Vue d'Insertion d'une Nouvelle Catégorie
	 */
	private void creerVueInsererCategorie() {

		System.out.println("Vues::creerVueInsererCategorie()");
		layoutVueInsererCategorie.setAlignment(Pos.CENTER);
		layoutVueInsererCategorie.setHgap(10);
		layoutVueInsererCategorie.setVgap(10);
		layoutVueInsererCategorie.setPadding(new Insets(25, 25, 25, 25));

		Text scenetitle = new Text("Inserer votre nouvelle Catégorie");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		
		layoutVueInsererCategorie.add(scenetitle, 0, 0, 2, 1);
		layoutVueInsererCategorie.add(lblNomCategorie, 0, 1);
		layoutVueInsererCategorie.add(tfNomCategorie, 1, 1);

		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_CENTER);
		hbBtn.getChildren().addAll(bValiderCategorie,bAnnulerCategorie,bRetourPagePrincipal);
		layoutVueInsererCategorie.add(hbBtn, 1, 4);
		
		layoutVueInsererCategorie.setStyle("-fx-background-color: BEIGE;"); 

	}

	/**
	 * Créer la Vue de la Liste des Categories
	 */
	private void creerVueListeCategories() {
		
		System.out.println("Vues::creerVueListeCategories()");
		/**
		 * On précise que l'orientation de la liste sera Vertical
		 * définition d'uune hauteur (ou largeur si horizontal)fixe pour tous les éléments de la liste
		 * On précise que la list n'est pas éditable
		 */
		
		listViewCategorie.setItems(Modele.getMesCacategories());
		listViewCategorie.setOrientation(Orientation.VERTICAL);
		//listViewCategorie.setPrefSize(250, 200);
		listViewCategorie.setFixedCellSize(22);
		listViewCategorie.setEditable(false);
		listViewCategorie.setStyle("-fx-alignment: CENTER;");
	
		layoutVueListeCategories.getChildren().add(listViewCategorie);
		layoutVueListeCategories.getChildren().add(bRetourPageAccueil);
		layoutVueListeCategories.setAlignment(Pos.CENTER);
		layoutVueListeCategories.setStyle("-fx-background-color: BEIGE;"); 

		bRetourPageAccueil.setAlignment(Pos.BOTTOM_CENTER);
		
	}

	/**
	 * Créer la Vue d'Authentification
	 * @param primaryStage
	 */
	private void creerVueAuthentification(Stage primaryStage) {
		
		System.out.println("Vues::creerVueAuthentification()");
		layoutVueConnecter.setStyle("-fx-background-color: BEIGE;"); 

		/**
		 * Insertion du composant dans le layout Gridpane
		 * Alignement horizontal du composant passé en paramètre(Centré).
		 * Marge autour du composant (de type Insets)
		 */
		lblTitre.setFont(Font.font("System", FontWeight.BOLD, 20));
		lblTitre.setTextFill(Color.rgb(80, 80, 180));
		layoutVueConnecter.add(lblTitre, 0, 0, 2, 1);// Title (2 cols spanning)
		GridPane.setHalignment(lblTitre, HPos.CENTER);
		GridPane.setMargin(lblTitre, new Insets(0, 0, 10,0));
		
		/**
		 * Nombre de colonnes du champ texte du login
		 * Insertion du libellé et du champ de texte pour l'identifant dans le layout Gridpane
		 * Alignement horizontal du composant passé en paramètre(à Droite)
		 */
		tfLogin.setPrefColumnCount(12);
		layoutVueConnecter.add(lblLogin,0,1); // Username label
		layoutVueConnecter.add(tfLogin, 1, 1); // Username text-field
		GridPane.setHalignment(tfLogin, HPos.CENTER);
		
		/**
		 * Nombre de colonnes du champ texte du login
		 * Insertion du libellé et du champ de texte pour le mot de passe dans le layout Gridpane
		 * Alignement horizontal du composant passé en paramètre(à Droite)
		 * Le composant s'agrandira pas jusqu'à sa largeur maximale
		 */
		pfMdp.setPrefColumnCount(12);
		layoutVueConnecter.add(lblMdp, 0, 2); // Password label
		layoutVueConnecter.add(pfMdp, 1, 2); // Password text-field
		GridPane.setHalignment(lblMdp, HPos.RIGHT);
		GridPane.setFillWidth(pfMdp, true);
		
		
		/**
		 * Insertion des boutons Valider et Annuler dans le conteneur de boutons
		 * Alignement des boutons(Centré)
		 * Insertion du conteneur de boutons dans le layout Gridpane
		 * Marge autour du conteneur de boutons (de type Insets)
		 */
		lesBoutons.getChildren().addAll(bValiderConnexion,bAnnulerConnexion,bRetourViewAccueil);
		lesBoutons.setAlignment(Pos.CENTER);
		layoutVueConnecter.add(lesBoutons, 1, 3);
		GridPane.setMargin(lesBoutons, new Insets(10, 0, 0,0));
		
		
		/**
		 * Alignement de la grille dans le conteneur (si elle n'occupe pas tout l'espace)
		 * Espacement autour de la grille (marge)
		 * Espacement horizontal entre les colonnes
		 * Espacement vertical entre les lignes
		 */
		layoutVueConnecter.setAlignment(Pos.CENTER);
		layoutVueConnecter.setPadding(new Insets(20));
		layoutVueConnecter.setHgap(10);
		layoutVueConnecter.setVgap(15);
	}

	/**
	 * Création de la Barre de menus
	 * @param primaryStage
	 */
	private void creerBarMenus(Stage primaryStage) {
		
		System.out.println("Vues::creerBarMenus()");
		
		barreMenus.prefWidthProperty().bind(primaryStage.widthProperty());
		menuFichier.getItems().addAll(seConnecter,seDeconnecter,separateur,quitter);
		menuAjout.getItems().addAll(categorie,produit);
		menuConsultation.getItems().addAll(categories,uneCategorie,separateur1,lesProduitsCodeCat,lesProduitsMC);
		barreMenus.getMenus().addAll(menuFichier,menuAjout,menuConsultation);
	
	}
	
	/**
	 * Afficher un message de confirmation
	 */
	public void afficherMessageInformations() {
		Alert info = new Alert(AlertType.INFORMATION);
		info.setHeaderText("Connexion réussie avec succès");
		info.setContentText("Bienvenue "+tfLogin.getText()+".");
		info.showAndWait();
	}
	
	/**
	 * Afficher un message d'erreur
	 */
	public void afficherMessageErreurs() {
		Alert error = new Alert(AlertType.ERROR);
		error.setHeaderText("Echec de la Connexion");
		error.setContentText("Login ou Mot de Passe Incorrect.");
		error.showAndWait();
	}

	/**
	 * Remettre les valeurs des champs à zéro
	 */
	public void resetChamps() {
		tfLogin.setText("");
		pfMdp.setText("");
	}
	
	/**
	 * Desactiver des Items de Menus et des Menus
	 */
	public void desactiverItemsMenus() {
		this.menuAjout.setDisable(true);
		this.menuConsultation.setDisable(true);
		this.seDeconnecter.setDisable(true);
	}
	
	/**
	 * Activer Items de Menus et des Menus
	 */
	public void activerItemsMenus() {
		this.menuAjout.setDisable(false);
		this.menuConsultation.setDisable(false);
		this.seDeconnecter.setDisable(false);
		this.seConnecter.setDisable(true);
	}
	

	/**
	 * @return the menuFichier
	 */
	public Menu getMenuFichier() {
		return menuFichier;
	}

	/**
	 * @return the seConnecter
	 */
	public MenuItem getSeConnecter() {
		return seConnecter;
	}

	/**
	 * @return the seDeconnecter
	 */
	public MenuItem getSeDeconnecter() {
		return seDeconnecter;
	}

	/**
	 * @return the quitter
	 */
	public MenuItem getQuitter() {
		return quitter;
	}

	/**
	 * @return the menuAjout
	 */
	public Menu getMenuAjout() {
		return menuAjout;
	}

	/**
	 * @return the produit
	 */
	public MenuItem getProduit() {
		return produit;
	}

	/**
	 * @return the categorie
	 */
	public MenuItem getCategorie() {
		return categorie;
	}

	/**
	 * @return the menuConsultation
	 */
	public Menu getMenuConsultation() {
		return menuConsultation;
	}

	/**
	 * @return the categories
	 */
	public MenuItem getCategories() {
		return categories;
	}

	/**
	 * @return the uneCategorie
	 */
	public MenuItem getUneCategorie() {
		return uneCategorie;
	}

	/**
	 * @return the lesProduitsCodeCat
	 */
	public MenuItem getLesProduitsCodeCat() {
		return lesProduitsCodeCat;
	}

	/**
	 * @return the lesProduitsMC
	 */
	public MenuItem getLesProduitsMC() {
		return lesProduitsMC;
	}

	/**
	 * @return the tfLogin
	 */
	public TextField getTfLogin() {
		return tfLogin;
	}

	/**
	 * @return the pfMdp
	 */
	public PasswordField getPfMdp() {
		return pfMdp;
	}

	
	/**
	 * @return the tfRechercheMC
	 */
	public TextField getTfRechercheMC() {
		return tfRechercheMC;
	}


	/**
	 * @return the bValiderConnexion
	 */
	public Button getbValiderConnexion() {
		return bValiderConnexion;
	}

	/**
	 * @return the bAnnulerConnexion
	 */
	public Button getbAnnulerConnexion() {
		return bAnnulerConnexion;
	}

	/**
	 * @return the bRetourViewAccueil
	 */
	public Button getbRetourViewAccueil() {
		return bRetourViewAccueil;
	}

	/**
	 * @return the bRetourPageAccueil
	 */
	public Button getbRetourPageAccueil() {
		return bRetourPageAccueil;
	}

	/**
	 * @return the bRetourPrincipalPageAccueil
	 */
	public Button getbRetourPrincipalPageAccueil() {
		return bRetourPrincipalPageAccueil;
	}


	/**
	 * @return the bRetourPP
	 */
	public Button getbRetourPP() {
		return bRetourPP;
	}


	/**
	 * @return the tfNomCategorie
	 */
	public TextField getTfNomCategorie() {
		return tfNomCategorie;
	}

	/**
	 * @return the bValiderCategorie
	 */
	public Button getbValiderCategorie() {
		return bValiderCategorie;
	}

	/**
	 * @return the bAnnulerCategorie
	 */
	public Button getbAnnulerCategorie() {
		return bAnnulerCategorie;
	}

	/**
	 * @return the bRetourPagePrincipal
	 */
	public Button getbRetourPagePrincipal() {
		return bRetourPagePrincipal;
	}
	
	/**
	 * @return the tfNomProduit
	 */
	public TextField getTfNomProduit() {
		return tfNomProduit;
	}

	/**
	 * @return the tfPrix
	 */
	public TextField getTfPrix() {
		return tfPrix;
	}

	/**
	 * @return the tfQteStock
	 */
	public TextField getTfQteStock() {
		return tfQteStock;
	}
	
	/**
	 * @return the comboCategories
	 */
	public ComboBox<Categorie> getComboCategories() {
		return ComboCategories;
	}

	/**
	 * @return the comboLesCategories
	 */
	public ComboBox<Categorie> getComboLesCategories() {
		return ComboLesCategories;
	}

	/**
	 * @return the bValiderProduit
	 */
	public Button getbValiderProduit() {
		return bValiderProduit;
	}

	/**
	 * @return the bAnnulerProduit
	 */
	public Button getbAnnulerProduit() {
		return bAnnulerProduit;
	}

	/**
	 * @return the bRetourFirstVue
	 */
	public Button getbRetourFirstVue() {
		return bRetourFirstVue;
	}

	/**
	 * @return the bValiderUneCategorie
	 */
	public Button getbValiderUneCategorie() {
		return bValiderUneCategorie;
	}

	/**
	 * @return the bRetourAccueilPage
	 */
	public Button getbRetourAccueilPage() {
		return bRetourAccueilPage;
	}

	/**
	 * @return the bRetourPrincipalPage
	 */
	public Button getbRetourPrincipalPage() {
		return bRetourPrincipalPage;
	}

	/**
	 * @return the sceneVuePrincipal
	 */
	public Scene getSceneVuePrincipal() {
		return sceneVuePrincipal;
	}

	/**
	 * @return the sceneVueAuthentification
	 */
	public Scene getSceneVueAuthentification() {
		return sceneVueAuthentification;
	}

	/**
	 * @return the sceneVueListeCategories
	 */
	public Scene getSceneVueListeCategories() {
		return sceneVueListeCategories;
	}

	/**
	 * @return the sceneVueInsererCategorie
	 */
	public Scene getSceneVueInsererCategorie() {
		return sceneVueInsererCategorie;
	}

	/**
	 * @return the sceneVueInsererProduit
	 */
	public Scene getSceneVueInsererProduit() {
		return sceneVueInsererProduit;
	}

	/**
	 * @return the sceneVueConsulterUneCategorie
	 */
	public Scene getSceneVueConsulterUneCategorie() {
		return sceneVueConsulterUneCategorie;
	}

	/**
	 * @return the sceneVueConsulterLesProduits
	 */
	public Scene getSceneVueConsulterLesProduits() {
		return sceneVueConsulterLesProduits;
	}

	/**
	 * @return the sceneVueConsulterLesProduitsByCategories
	 */
	public Scene getSceneVueConsulterLesProduitsByCategories() {
		return sceneVueConsulterLesProduitsByCategories;
	}

	/**
	 * @return the sceneVueConsulterLesProduitsMC
	 */
	public Scene getSceneVueConsulterLesProduitsMC() {
		return sceneVueConsulterLesProduitsMC;
	}


	/**
	 * @return the tvProduitsByCategories
	 */
	public TableView<Produit> getTvProduitsByCategories() {
		return tvProduitsByCategories;
	}
	
	/**
	 * @return the listViewCategorie
	 */
	public ListView<Categorie> getListViewCategorie() {
		return listViewCategorie;
	}


	/**
	 * @return the tvProduits
	 */
	public TableView<Produit> getTvProduits() {
		return tvProduits;
	}

	/**
	 * @return the tvProduitsMC
	 */
	public TableView<Produit> getTvProduitsMC() {
		return tvProduitsMC;
	}


	/**
	 * Lancement de l'Application
	 * @param args
	 */
	public static void Main(String[] args) {
		
		launch(args);
	}
}