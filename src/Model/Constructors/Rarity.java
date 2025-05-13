package Model.Constructors;

public class Rarity {
    private int id;
    private String nom;

    public Rarity(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public int getId() { return id; }
    public String getNom() { return nom; }
}
