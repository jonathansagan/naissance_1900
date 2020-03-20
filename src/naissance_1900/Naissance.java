package naissance_1900;

import org.apache.commons.io.FileUtils;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/** Définit la classe Naissance
 * @author Jonathan
 *
 */
public class Naissance {
	long annee;
	LocalDate DateEvenement;
	long nombre;

	/**
	 * Constructeur de naissance
	 * 
	 * @param annee année de naissance
	 * @param dateEvenement date de naissance
	 * @param nombre nombre de naissances
	 */
	public Naissance(long annee, LocalDate dateEvenement, long nombre) {
		super();
		this.annee = annee;
		DateEvenement = dateEvenement;
		this.nombre = nombre;
	}

	public static void main(String[] args) {

		final String PATTERN_DATE = "yyyyMMdd";
		File file = new File("C:/temp/naissances_depuis_1900.csv");
		List<String> lignes = null;
		try {
			lignes = FileUtils.readLines(file, "UTF-8");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		List<Naissance> naissance = new ArrayList<Naissance>();

		lignes.remove(0);
		for (int ligne = 1; ligne < lignes.size(); ++ligne) {

			String[] morceaux = lignes.get(ligne).split(";");
			String annee = morceaux[1];
			String DateEvenement = morceaux[2];
			String nombre = morceaux[3];
			LocalDate date = null;
			try {
				date = LocalDate.parse(morceaux[2], DateTimeFormatter.ofPattern(PATTERN_DATE));
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			long nombreTot = Long.parseLong(nombre);
			long anneeOk = Long.parseLong(annee);

			Naissance naissances = new Naissance(anneeOk, date, nombreTot);
			naissance.add(naissances);
		}
		System.out.println(naissance);
		
// Afficher toutes les données de 1900
	naissance.stream()
	.filter(e -> e.getAnnee() == 1900)
	.forEach(System.out::println);
		
// Afficher la somme des naissance en 1900
	long somme = naissance.stream()
			.filter(n -> n.getAnnee() == 1900)
			.map(n -> n.getNombre())
			.reduce((n1, n2) -> n1 + n2).get();
	System.out.println(somme);
		
		
// Afficher la moyenne du nombre de naissances de l'année 1971
	
	double moyenne = naissance.stream()
			.filter(n -> n.getAnnee() == 1971)
			.mapToLong(n -> n.getNombre())
			.average().getAsDouble();
	System.out.println(moyenne);
	
	}

	@Override
	public String toString() {
		return "Naissance [annee=" + annee + ", DateEvenement=" + DateEvenement + ", nombre=" + nombre + "]";
	}

	/**Getter permet de lire l'année
	 * @return l'année
	 */
	public long getAnnee() {
		return annee;
	}

	/**Setter permet de modifier l'année
	 * @param annee
	 */
	public void setAnnee(long annee) {
		this.annee = annee;
	}

	/** Getter permet de lire la date
	 * @return
	 */
	public LocalDate getDateEvenement() {
		return DateEvenement;
	}

	/** Setter permet de modifier la date
	 * @param dateEvenement
	 */
	public void setDateEvenement(LocalDate dateEvenement) {
		DateEvenement = dateEvenement;
	}

	/**Getter permet de lire le nombre de naissances
	 * @return nombre de naissances
	 */
	public long getNombre() {
		return nombre;
	}

	/**Setter permet de modifier le nombres de naissances
	 * @param nombre nombre de naissances
	 */
	public void setNombre(long nombre) {
		this.nombre = nombre;
	}

}
