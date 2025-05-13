package Model.Constructors;

public class Brawler {
    private int id;
    private String nom;
    private String descripcio;
    private int idRarity;
    private int idClasse;

    public Brawler(int id, String nom, String descripcio, int idRarity, int idClasse) {
        this.id = id;
        this.nom = nom;
        this.descripcio = descripcio;
        this.idRarity = idRarity;
        this.idClasse = idClasse;
    }

    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getDescripcio() { return descripcio; }
    public int getIdRarity() { return idRarity; }
    public int getIdClasse() { return idClasse; }
}
