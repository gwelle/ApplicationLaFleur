/**
 * 
 */
package application.entites;

/**
 * @author Olga
 *
 */
public class Categorie {
	
	private int code_type ;
	private String nom ;
	
	/**
	 * @param nom
	 */
	public Categorie(String nom) {
		this.nom = nom;
		System.out.println("Categorie::Categorie()");

	}

	/**
	 * @return the code_type
	 */
	public int getCode_type() {
		return code_type;
	}


	/**
	 * @param code_type the code_type to set
	 */
	public void setCode_type(int code_type) {
		this.code_type = code_type;
	}


	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}


	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return nom;
	}
}