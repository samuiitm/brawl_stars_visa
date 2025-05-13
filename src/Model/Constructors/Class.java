package Model.Constructors;

public class Class {
    private int id;
    private String nom;

    public Class(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public int getId() { return id; }
    public String getNom() { return nom; }
}