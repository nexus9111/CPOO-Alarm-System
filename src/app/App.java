package app;

import controller.Controller;

public class App {
    public static void main(String[] args) {
        Controller controleur;
        try {
            controleur = new Controller();
            // controleur.habStart();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
