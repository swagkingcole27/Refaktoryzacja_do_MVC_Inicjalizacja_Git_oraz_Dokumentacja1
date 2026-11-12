package com.egzamin;

import com.egzamin.controller.AppController;
import com.egzamin.model.DatabaseManager;
import com.egzamin.view.MainFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DatabaseManager model = new DatabaseManager();
            MainFrame view = new MainFrame();
            new AppController(model, view);
            view.setVisible(true);
        });
    }
}