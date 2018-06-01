package com.cjm.ms.JavaFX;

import com.cjm.ms.JavaFX.model.Artist;
import com.cjm.ms.JavaFX.model.DataSource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class Controller {

    @FXML
    private TableView<Artist> artistTable;

    public void listArtists() {
        Task<ObservableList<Artist>> task = new GetAllArtistsTask();
        artistTable.itemsProperty().bind(task.valueProperty());
    }
}

class GetAllArtistsTask extends Task {
    @Override
    protected ObservableList<Artist> call() throws Exception {
        return FXCollections.observableArrayList(
                DataSource.getInstance().queryArtist(DataSource.ORDER_BY_ASC)
        );
    }
}