package com.egzamin.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MainFrame extends JFrame {
    private JTextField poleImie;
    private JTextField poleNazwisko;
    private JTextField poleStanowisko;
    private JButton przyciskDodaj;
    private JTable tabela;
    private DefaultTableModel modelTabeli;

    public MainFrame() {
        setTitle("INF.04 - System Obsługi Pracowników");
        setSize(700, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelFormularza = new JPanel(new FlowLayout());
        poleImie = new JTextField(8);
        poleNazwisko = new JTextField(8);
        poleStanowisko = new JTextField(8);
        przyciskDodaj = new JButton("Dodaj Pracownika");

        panelFormularza.add(new JLabel("Imię:"));
        panelFormularza.add(poleImie);
        panelFormularza.add(new JLabel("Nazwisko:"));
        panelFormularza.add(poleNazwisko);
        panelFormularza.add(new JLabel("Stanowisko:"));
        panelFormularza.add(poleStanowisko);
        panelFormularza.add(przyciskDodaj);

        add(panelFormularza, BorderLayout.NORTH);

        String[] kolumny = {"ID", "Imię", "Nazwisko", "Stanowisko"};
        modelTabeli = new DefaultTableModel(kolumny, 0);
        tabela = new JTable(modelTabeli);
        JScrollPane scrollPane = new JScrollPane(tabela);

        add(scrollPane, BorderLayout.CENTER);
    }

    public JTextField getPoleImie() {
        return poleImie;
    }

    public JTextField getPoleNazwisko() {
        return poleNazwisko;
    }

    public JTextField getPoleStanowisko() {
        return poleStanowisko;
    }

    public JButton getPrzyciskDodaj() {
        return przyciskDodaj;
    }

    public DefaultTableModel getModelTabeli() {
        return modelTabeli;
    }
}