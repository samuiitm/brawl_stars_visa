package Model.Interfaces;

import java.util.List;

public interface CRUD<T> {
    void inserir(T objecte);
    List<T> llistar();
    T obtenirPerId(int id);
    void actualitzar(T objecte);
    void eliminar(int id);
}