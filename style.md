#Normes de codification pour l’équipe 7

---
###Contexte : programmation avec le langage _Java_

---

###Structure des classes
- Une classe par fichier
- Une documentation _Javadoc_ avec les noms des auteurs de la classe, sauf dans le cas des classes utilisées sous licence (e.g. _FileReader.java_) 
- En ordre d’apparition dans la classe, on trouve les éléments suivants (s'ils existent) :
	- Les attributs statiques
	- Les attributs d’instance
	- Les constructeurs
	- Les autres méthodes

###Nomenclature
- Casses des identifiants : 
	- _PascalCase_ pour les noms de classes
	- _camelCase_ pour tout autre identifiant
- Tout identifiant est écrit en **Français** (mais sans accent), sauf pour les préfixes _get_ et _set_ pour les accesseurs et les mutateurs, respectivement

###Mise en page du code 

- Indentation : 4 espaces par niveau
- Taille maximale d’une ligne de code : 120 caractères
- Taille maximale d’une méthode : 10 lignes
- Une ligne d'espacement entre les éléments ou ensembles d'éléments suivants :
	- Le paquetage dans lequel se trouve la classe
    - Les importations
    - Les attributs d'une classe
    - Chaque méthode d'une classe 
- À l'intérieur des parenthèses, les éléments distincts sont séparés par exactement une espace; il n'y a pas d'espace adjacente aux parenthèses 
- La paire de de parenthèses pour les arguments d'une méthode est adjacente au nom de la méthode mais séparée d'une accolade ouvrante par exactemeent une espace 

###Modèle d'une classe avec méthode
```
public classe Telephone {
	
	static String fonctionnalite = "Faire des appels";
	String marque;

	public Telephone (String marque) {
		this.marque = marque;
	}
	
	public appeler(String numeroDeTelephone) {
		System.out.println("Dring dring");
	}
}
```
---
_Dernière révision: le 26 octobre, 2014_
