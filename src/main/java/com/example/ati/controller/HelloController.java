package com.example.ati.controller;

import com.example.ati.HelloApplication;
import com.example.ati.domain.Bed;
import com.example.ati.domain.BedType;
import com.example.ati.repo.db.BedRepoDB;
import com.example.ati.repo.db.PacientRepoDB;
import com.example.ati.service.Service;
import com.example.ati.utils.event.EntityChangeEvent;
import com.example.ati.utils.observer.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelloController implements Observer<EntityChangeEvent> {
    @FXML
    private TableView<Bed> bedTableView;
    @FXML
    private TableColumn<Bed, BedType> bedTypeColumn;
    @FXML
    private TableColumn<Bed, String> ventilationColumn;
    @FXML
    private TableColumn<Bed, Integer> pacientCNPColumn;
    @FXML
    private Label freeBedsLabel;

    private final ObservableList<Bed> bedsModel = FXCollections.observableArrayList();
    private Service service;

    public void setNoOfBeds(){
        int noOfFreeBeds = 0;
        for(Bed bed: service.getAllBeds()){
            if(bed.getPacientCNP() != 0){
                noOfFreeBeds++;
            }
        }
        String text = "Free beds: " + (21 - noOfFreeBeds);
        freeBedsLabel.setText(text);

    }

    public void setService(Service service) throws IOException {
        this.service = service;
        //setNoOfBeds();
        initModel();

        service.addObserver(this);

        Stage pacientsStage = new Stage();
        FXMLLoader fxmlLoaderPacients = new FXMLLoader(HelloApplication.class.getResource("pacients-view.fxml"));
        Scene scenePacients = new Scene(fxmlLoaderPacients.load(), 600, 300);
        pacientsStage.setTitle("Hello!");
        pacientsStage.setScene(scenePacients);

        PacientsViewController pacientsViewController = fxmlLoaderPacients.getController();
        pacientsViewController.setService(new Service(new PacientRepoDB(), new BedRepoDB()));
        pacientsStage.show();
    }

    private void initModel() {
        List<Bed> beds = service.getAllBeds();
        List<Bed> occupiedBeds = new ArrayList<>();
        for(Bed bed: beds){
            if(bed.getPacientCNP() != 0){
                occupiedBeds.add(bed);
            }
        }
        bedsModel.setAll(occupiedBeds);
        setNoOfBeds();
    }

    @FXML
    public void initialize(){
        bedTypeColumn.setCellValueFactory(new PropertyValueFactory<>("bedType"));
        ventilationColumn.setCellValueFactory(new PropertyValueFactory<>("ventilation"));
        pacientCNPColumn.setCellValueFactory(new PropertyValueFactory<>("pacientCNP"));

        bedTableView.setItems(bedsModel);
    }

    @Override
    public void update(EntityChangeEvent entityChangeEvent) {
        initModel();
    }

    @FXML
    private void onClickWindow(MouseEvent mouseEvent) {
        //initModel();
    }
}