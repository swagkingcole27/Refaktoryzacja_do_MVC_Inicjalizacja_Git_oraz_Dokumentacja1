package com.egzamin.controller;

import com.egzamin.model.DatabaseManager;
import com.egzamin.view.MainFrame;

import javax.swing.*;
import java.util.List;

public class AppController {
    private DatabaseManager model;
    private MainFrame view;

    public AppController(DatabaseManager model, MainFrame view) {
        this.model = model;
        this.view = view;

        wczytajDane();
        this.view.getPrzyciskDodaj().addActionListener(e -> dodajPracownika());
    }

    private void wczytajDane() {
        try {
            view.getModelTabeli().setRowCount(0);
            List<Object[]> pracownicy = model.pobierzPracownikow();
            for (Object[] wiersz : pracownicy) {
                view.getModelTabeli().addRow(wiersz);
            }
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(view, e.getMessage());
        }
    }

    private void dodajPracownika() {
        String imie = view.getPoleImie().getText().trim();
        String nazwisko = view.getPoleNazwisko().getText().trim();
        String stanowisko = view.getPoleStanowisko().getText().trim();

        if (imie.isEmpty() || nazwisko.isEmpty() || stanowisko.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Wszystkie pola muszą być wypełnione!", "Błąd walidacji", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            model.dodajPracownika(imie, nazwisko, stanowisko);
            JOptionPane.showMessageDialog(view, "Pomyślnie dodano pracownika!");

            view.getPoleImie().setText("");
            view.getPoleNazwisko().setText("");
            view.getPoleStanowisko().setText("");

            wczytajDane();
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(view, e.getMessage());
        }
    }
}