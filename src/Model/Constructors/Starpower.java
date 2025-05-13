package Model.Constructors;

public class Starpower {
    private int id;
    private String nom;
    private String descripcio;
    private int idBrawler;

    public Starpower(int id, String nom, String descripcio, int idBrawler) {
        this.id = id;
        this.nom = nom;
        this.descripcio = descripcio;
        this.idBrawler = idBrawler;
    }

    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getDescripcio() { return descripcio; }
    public int getIdBrawler() { return idBrawler; }
}
