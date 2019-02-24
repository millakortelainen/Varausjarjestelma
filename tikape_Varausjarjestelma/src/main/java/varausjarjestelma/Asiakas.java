package varausjarjestelma;

public class Asiakas {

    private int id;
    private String nimi;
    private String puhnro;
    private String email;

    public Asiakas(String nimi, String puhnro, String email) {
        this.nimi = nimi;
        this.puhnro = puhnro;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public String getNimi() {
        return nimi;
    }

    public String getPuhnro() {
        return puhnro;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public void setPuhnro(String puhnro) {
        this.puhnro = puhnro;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
