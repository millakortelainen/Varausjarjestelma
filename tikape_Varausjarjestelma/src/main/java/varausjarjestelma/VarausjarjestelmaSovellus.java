package varausjarjestelma;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class VarausjarjestelmaSovellus implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(VarausjarjestelmaSovellus.class);
    }

    @Autowired
    Tekstikayttoliittyma tekstikayttoliittyma;
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        Scanner lukija = new Scanner(System.in);
        tekstikayttoliittyma.kaynnista(lukija);
        poistaTietokanta();
        alustaTietokanta();
//        int huonenumero = 123;
//        String tyyppi = "asdadsa";
//        double paivahinta = 3.4;
//        jdbcTemplate.update("INSERT INTO Huoneet (huonenumero, tyyppi, paivahinta) VALUES (?,?, ?)",
//                huonenumero, tyyppi, paivahinta);
//        jdbcTemplate.update("INSERT INTO Asiakkaat (nimi, puhnro, email) VALUES (?,?, ?)",
//                "Milla", "090909090", "milla@gmail.com");
//        jdbcTemplate.update("INSERT INTO Asiakkaat (nimi, puhnro, email) VALUES (?,?, ?)",
//                "Milla", "090909090", "milla@gmail.com");
//        System.out.println("jee");
//        jdbcTemplate.query(
//                "SELECT huonenumero FROM Huoneet;",
//                (rs, rowNum) -> rs.getString("huonenumero")
//        ).forEach(System.out::println);
//        jdbcTemplate.query(
//                "SELECT id FROM Asiakkaat;",
//                (rs, rowNum) -> rs.getString("id")
//        ).forEach(System.out::println);
    }

    private static void alustaTietokanta() {

        try (Connection conn = DriverManager.getConnection("jdbc:h2:./hotelliketju", "sa", "")) {

            conn.prepareStatement("CREATE TABLE Huoneet(huonenumero integer,"
                    + " tyyppi varchar(255), paivahinta numeric (5,2),"
                    + " primary key (huonenumero));").executeUpdate();

            conn.prepareStatement("CREATE TABLE Asiakkaat(id serial, "
                    + "nimi varchar(255),puhnro varchar(255),email varchar(255),"
                    + "primary key (id));").executeUpdate();

            conn.prepareStatement("CREATE TABLE Lisavarusteet(id integer,"
                    + " lisavaruste varchar(255), primary key (id));").executeUpdate();

            conn.prepareStatement("CREATE TABLE Varaukset(id integer, "
                    + "alkupaiva date, loppupaiva date, asiakas integer,"
                    + " huone integer, primary key (id),"
                    + " foreign key (asiakas) references Asiakkaat(id),"
                    + " foreign key(huone) references Huoneet(id));").executeUpdate();

            conn.prepareStatement("CREATE TABLE VarausLisavaruste(id integer, "
                    + "tyyppi varchar(255), paivahinta numeric (5,2),"
                    + " primary key (id));").executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(VarausjarjestelmaSovellus.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void poistaTietokanta() {
        try (Connection conn = DriverManager.getConnection("jdbc:h2:./hotelliketju", "sa", "")) {

            conn.prepareStatement("DROP TABLE Huoneet IF EXISTS;").executeUpdate();
            conn.prepareStatement("DROP TABLE Asiakkaat IF EXISTS;").executeUpdate();
            conn.prepareStatement("DROP TABLE Varaukset IF EXISTS;").executeUpdate();
            conn.prepareStatement("DROP TABLE Lisavarusteet IF EXISTS;").executeUpdate();
            conn.prepareStatement("DROP TABLE VarausLisavaruste IF EXISTS;").executeUpdate();

        } catch (SQLException ex) {

            Logger.getLogger(VarausjarjestelmaSovellus.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

}
