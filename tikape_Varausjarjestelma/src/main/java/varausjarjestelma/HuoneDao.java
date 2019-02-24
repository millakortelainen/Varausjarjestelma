package varausjarjestelma;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class HuoneDao implements Dao<Asiakas, Integer> {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void create(Asiakas asiakas) throws SQLException {
        jdbcTemplate.update("INSERT INTO Asiakas"
                + " (nimi, puhelinnumero, katuosoite)"
                + " VALUES (?, ?, ?)",
                asiakas.getNimi(),
                asiakas.getPuhnro(),
                asiakas.getEmail());
                
    }

    @Override
    public Asiakas read(Integer key) throws SQLException {
        Asiakas asiakas = jdbcTemplate.queryForObject(
                "SELECT * FROM Asiakas WHERE id = ?",
                new BeanPropertyRowMapper<>(Asiakas.class),
                key);

        return asiakas;
    }

    @Override
    public Asiakas update(Asiakas asiakas) throws SQLException {
        jdbcTemplate.update("update asiakas set nimi=? where id=?", asiakas.getNimi(), asiakas.getId());
        jdbcTemplate.update("update asiakas set puhelinnumero=? where id=?", asiakas.getPuhnro(), asiakas.getId());
        jdbcTemplate.update("update asiakas set email=? where id=?", asiakas.getEmail(), asiakas.getId());
        return asiakas;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        jdbcTemplate.update("DELETE FROM Asiakas WHERE id=?", key);

    }

    @Override
    public List<Asiakas> list() throws SQLException {
        List<Integer> asiakasID = jdbcTemplate.query(
                "SELECT * FROM Asiakas",
                (rs, rowNum) -> rs.getInt("id"));
        List<Asiakas> asiakkaat = new ArrayList<>();
        for (Integer i : asiakasID) {
            Asiakas asiakas = jdbcTemplate.queryForObject(
                    "SELECT * FROM Asiakas WHERE id = ?",
                    new BeanPropertyRowMapper<>(Asiakas.class),
                    i);
            asiakkaat.add(asiakas);
        }

        return asiakkaat;
    }
}
