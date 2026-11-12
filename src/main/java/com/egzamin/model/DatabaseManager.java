package com.egzamin.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private final String URL = "jdbc:sqlite:pracownicy.db";

    public DatabaseManager() {
        utworzTabeleWBazie();
    }

    private void utworzTabeleWBazie() {
        String sql = "CREATE TABLE IF NOT EXISTS pracownicy (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "imie TEXT NOT NULL, " +
                "nazwisko TEXT NOT NULL, " +
                "stanowisko TEXT NOT NULL)";

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Błąd tworzenia bazy: " + e.getMessage(), e);
        }
    }

    public List<Object[]> pobierzPracownikow() {
        List<Object[]> lista = new ArrayList<>();
        String sql = "SELECT id, imie, nazwisko, stanowisko FROM pracownicy";

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Object[]{
                        rs.getInt("id"),
                        rs.getString("imie"),
                        rs.getString("nazwisko"),
                        rs.getString("stanowisko")
                });
            }
        } catch (SQLException e) {
            throw new RuntimeException("Błąd wczytywania danych: " + e.getMessage(), e);
        }
        return lista;
    }

    public void dodajPracownika(String imie, String nazwisko, String stanowisko) {
        String sql = "INSERT INTO pracownicy (imie, nazwisko, stanowisko) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, imie);
            pstmt.setString(2, nazwisko);
            pstmt.setString(3, stanowisko);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Błąd dodawania do bazy: " + e.getMessage(), e);
        }
    }
}