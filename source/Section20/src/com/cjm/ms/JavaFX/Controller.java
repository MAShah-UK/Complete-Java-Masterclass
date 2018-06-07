package com.cjm.ms.JavaFX;

import com.cjm.ms.JavaFX.model.Album;
import com.cjm.ms.JavaFX.model.Artist;
import com.cjm.ms.JavaFX.model.DataSource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableView;

public class Controller {

    @FXML
    private TableView artistTable;
    // Indeterminate progress bar since total records are unknown.
    @FXML
    private ProgressBar progressBar;

    @FXML
    public void listArtists() {
        Task<ObservableList<Artist>> task = new GetAllArtistsTask();
        artistTable.itemsProperty().bind(task.valueProperty());
        progressBar.progressProperty().bind(task.progressProperty());
        progressBar.setVisible(true);
        // Using lambda functions to reduce code.
        task.setOnSucceeded(e -> progressBar.setVisible(false));
        task.setOnFailed(e -> progressBar.setVisible(false));
        new Thread(task).start();
    }
    @FXML
    public void listAlbumsForArtist() {
        final Artist artist = (Artist) artistTable.getSelectionModel().getSelectedItem();
        if(artist == null) {
            System.out.println("No artist selected.");
            return;
        }
        Task<ObservableList<Album>> task = new Task<>() {
            @Override
            protected ObservableList<Album> call() throws Exception {
                return FXCollections.observableArrayList(
                        DataSource.getInstance().queryAlbumsForArtistId(artist.getId()));
            }
        };
        artistTable.itemsProperty().bind(task.valueProperty());
        new Thread(task).start();
    }
    @FXML
    public void updateArtists() {
        // Normally would get the user's selection, but to save time and avoid writing
        // a custom dialog to get user input, we're assuming they want rename
        // the third element to AC/DC.
        final Artist artist = (Artist) artistTable.getItems().get(2);
        Task<Boolean> task = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                return DataSource.getInstance().updateArtistName(artist.getId(), "AC/DC");
            }
        };
        task.setOnSucceeded(e -> {
            if(task.valueProperty().get()) {
                artist.setName("AC/DC");
                artistTable.refresh();
            }
        });
        new Thread(task).start();
    }
}

class GetAllArtistsTask extends Task {
    @Override
    protected ObservableList<Artist> call() throws Exception {
        return FXCollections.observableArrayList(
                DataSource.getInstance().queryArtists(DataSource.ORDER_BY_ASC)
        );
    }
}