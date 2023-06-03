package com.example.films;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    @FXML
    private Button btnCsv;

    public Controller() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rangTab.setCellValueFactory(new PropertyValueFactory<Films, Integer>("rang"));
        idTab.setCellValueFactory(new PropertyValueFactory<Films, String>("id"));
        nomTab.setCellValueFactory(new PropertyValueFactory<Films, String>("nom"));
        acteurTab.setCellValueFactory(new PropertyValueFactory<Films, String>("acteur"));
        anneeTab.setCellValueFactory(new PropertyValueFactory<Films, Integer>("annee"));
        // Ce listener est utilisé quand l'utilisateur appuie sur la touche "Suppr" du clavier et supprime un film dans la liste
        tabFilms.setOnKeyPressed(this::handleKeyPressed);

        this.ajouterFilmDansTableau(1, "123", "Harry Potter", "Daniel Radcliffe", 2001);
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
        Integer rang = parseInt(rangText.getText());
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


    private List<List<String>> lireFichierCsv(String filePath) {
        List<List<String>> films = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                films.add(Arrays.asList(values));
            }
            return films;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void chargerFichierCsv() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir un fichier CSV");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers CSV", "*.csv"));

        // Afficher la fenêtre de dialogue de sélection de fichier
        Stage stage = (Stage) tabFilms.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            String filePath = selectedFile.getAbsolutePath();
            List<List<String>> csv = lireFichierCsv(filePath);
            for (int i = 1; i < csv.size(); i++) {
                Films filmsCSV = new Films();
                Integer rangCSV = filmsCSV.setRang(Integer.parseInt(csv.get(i).get(0)));
                String idCSV = filmsCSV.setId(csv.get(i).get(1));
                String nomCSV = filmsCSV.setNom(csv.get(i).get(2));
                String acteurCSV = filmsCSV.setActeur(csv.get(i).get(3));
                Integer anneeCSV = filmsCSV.setAnnee(Integer.parseInt(csv.get(i).get(4)));
                ObservableList<Films> filmss = tabFilms.getItems();
                ajouterFilmDansTableau(rangCSV, idCSV, nomCSV, acteurCSV, anneeCSV);
            }
        }
    }
}
