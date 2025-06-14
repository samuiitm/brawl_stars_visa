# 📌 Brawl Stars API amb GSON

## 📋 Descripció
Aquest projecte és una aplicació feta en Java que es connecta a una base de dades MySQL. El seu objectiu és gestionar tota la informació dels brawlers del joc *Brawl Stars*: brawlers, classes, rareses, gadgets i *starpowers*. Permet fer operacions bàsiques de CRUD (crear, llegir, actualitzar, eliminar) sobre cada entitat, importar dades des d’una API o fitxer JSON, i consultar si un brawler existeix a la base de dades.

---

## 🛠️ Tecnologies utilitzades

- Java
- [Gson](https://github.com/google/gson) per al parseig de JSON
- API REST de [Brawlify](https://brawlify.com/)
- API REST de [Brawl Stars](https://developer.brawlstars.com/#/)
- JDBC
- MySQL
- Maven
- Docker

---

## 🧠 Decisions tècniques

### 1. **Arquitectura MVC**
Hem separat el projecte en Model, Vista i Controlador per tenir el codi més ordenat i fàcil de mantenir. Així cada part fa la seva feina i no es barregen responsabilitats.

### 2. **DAO per a cada entitat**
Cada taula de la base de dades (brawlers, classes, rareses, gadgets, *starpowers*) té la seva pròpia classe DAO, que s’encarrega de comunicar-se amb la base de dades. Això fa que el codi sigui més net i modular.

### 3. **CRUD genèric**
Tenim una interfície CRUD amb els mètodes bàsics per gestionar les entitats. Cada DAO implementa aquesta interfície i així tot segueix el mateix patró.

### 4. **Importació des d’API i JSON**
El programa pot importar totes les dades dels brawlers des de l’API oficial de Brawlify o des d’un fitxer JSON. Això permet tenir la base de dades sempre actualitzada de manera fàcil.

### 5. **Control d’integritat**
Abans d’importar dades, es desactiven les claus foranes i es buiden les taules per evitar errors i duplicats. Després es tornen a activar.

### 6. **Menú interactiu**
L’usuari pot navegar per un menú per fer totes les operacions: llistar, importar, comprovar si existeix un brawler, etc.

---

## 🌟 Funcionalitats destacades

- ✅ **Importació automàtica**: pots importar tots els brawlers, gadgets i *starpowers* directament des de l’API o un JSON.
- 🔍 **Consulta ràpida**: pots comprovar si un brawler existeix a la base de dades pel seu ID.
- 🧩 **Gestió completa**: CRUD per a brawlers, classes, rareses, gadgets i *starpowers*.
- 🛠️ **Codi modular**: fàcil d’ampliar si vols afegir més funcionalitats o entitats.

---

## 📦 Instal·lació i ús

### 1. **Clona el repositori:**
```
git clone https://github.com/samuiitm/brawl_stars_visa.git  
cd brawl_stars_visa 
```
### 2. Assegura’t de tenir Java instal·lat (JDK 8 o superior).
### 3. El GSON s’afegirà gràcies a Maven.
### 4. Configura el teu token de l’API de Brawlify al codi font
> El token es troba a Controlador.ExistenBrawlers
### 5. Crea la base de dades amb:
```
docker-compose up
```
### 6. Executa el programa

## 🤝 Autors
_**Samuel Cañadas & Victor Extremera**_