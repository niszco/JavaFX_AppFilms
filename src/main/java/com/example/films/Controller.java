package com.example.films;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class Controller implements Initializable {
    @FXML
    private TableView<Films> tabFilms;
    @FXML
    private TableColumn<Films, Integer> rangTab;
    @FXML
    private TableColumn<Films, String> idTab;
    @FXML
    private TableColumn<Films, String> nomTab;
    @FXML
    private TableColumn<Films, String> acteurTab;
    @FXML
    private TableColumn<Films, Integer> anneeTab;
    @FXML
    private TextField rangText;
    @FXML
    private TextField idText;
    @FXML
    private TextField nomText;
    @FXML
    private TextField acteurText;
    @FXML
    private TextField anneeText;
    @FXML
    private Button btnViderTab;
    @FXML
    private Button btnAjouter;

    public Controller() {}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rangTab.setCellValueFactory(new PropertyValueFactory<Films, Integer>("rang"));
        idTab.setCellValueFactory(new PropertyValueFactory<Films, String>("id"));
        nomTab.setCellValueFactory(new PropertyValueFactory<Films, String>("nom"));
        acteurTab.setCellValueFactory(new PropertyValueFactory<Films, String>("acteur"));
        anneeTab.setCellValueFactory(new PropertyValueFactory<Films, Integer>("annee"));
        // Ce listener est utilisé quand l'utilisateur appuie sur la touche "Suppr" du clavier et supprime un film dans la liste
        tabFilms.setOnKeyPressed(this::handleKeyPressed);

        this.ajouterFilmDansTableau(1, "123", "Harry Potter", "Daniel Radcliffe",2001);
    }

    private void handleKeyPressed(KeyEvent e) {
        KeyCode keyCode = null;
        if (e.getCode() == keyCode.DELETE) {
            supprimerFilmDuTableau();
        }
    }

    public void ajouterFilmDansTableau(Integer rang, String id, String nom, String acteur, Integer annee) {
        Films films = new Films();

        films.setRang(rang);
        films.setId(id);
        films.setNom(nom);
        films.setActeur(acteur);
        films.setAnnee(annee);

        ObservableList<Films> film = tabFilms.getItems();
        film.add(films);
        tabFilms.setItems(film);
    }

    @FXML
    private void ajouterFilm() {
        Integer rang =  parseInt(rangText.getText());
        String id = idText.getText();
        String nom = nomText.getText();
        String acteur = acteurText.getText();
        Integer annee = parseInt(anneeText.getText());

        ajouterFilmDansTableau(rang, id, nom, acteur, annee);

        rangText.setText("");
        idText.setText("");
        nomText.setText("");
        acteurText.setText("");
        anneeText.setText("");
    }
    @FXML
    public void viderTableau() {
        ObservableList<Films> films = tabFilms.getItems();
        films.clear();
    }

    @FXML
    private void supprimerFilmDuTableau() {
        Films filmSelectionne = tabFilms.getSelectionModel().getSelectedItem();
        if (filmSelectionne != null) {
            ObservableList<Films> films = tabFilms.getItems();
            films.remove(filmSelectionne);
        }
    }

}