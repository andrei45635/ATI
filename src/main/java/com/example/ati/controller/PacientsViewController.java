package com.example.ati.controller;

import com.example.ati.domain.BedType;
import com.example.ati.domain.Pacient;
import com.example.ati.dto.Pacient2PacientDiagnosticDTO;
import com.example.ati.mapper.Pacient2PacientDiagnosticMapper;
import com.example.ati.service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class PacientsViewController {
    @FXML
    private ComboBox<BedType> bedTypeComboBox;
    @FXML
    private TableView<Pacient2PacientDiagnosticDTO> pacientTableView;
    @FXML
    private TableColumn<Pacient2PacientDiagnosticDTO, Integer> pacientCNPColumn;
    @FXML
    private TableColumn<Pacient2PacientDiagnosticDTO, Integer> pacientSeverityColumn;
    @FXML
    private TableColumn<Pacient2PacientDiagnosticDTO, String> pacientDiagnosticColumn;

    private final ObservableList<Pacient2PacientDiagnosticDTO> diagnosticModel = FXCollections.observableArrayList();
    private Service service;
    private final Pacient2PacientDiagnosticMapper pacient2PacientDiagnosticMapper = new Pacient2PacientDiagnosticMapper();

    public void setService(Service service){
        this.service = service;
        bedTypeComboBox.setItems(FXCollections.observableArrayList(BedType.TIM, BedType.TIIP, BedType.TIC));
        initModel();
    }

    @FXML
    public void initialize(){
        pacientCNPColumn.setCellValueFactory(new PropertyValueFactory<>("pacientCNP"));
        pacientSeverityColumn.setCellValueFactory(new PropertyValueFactory<>("pacientSeverity"));
        pacientDiagnosticColumn.setCellValueFactory(new PropertyValueFactory<>("pacientDiagnostic"));

        pacientTableView.setItems(diagnosticModel);
    }

    private void initModel() {
        List<Pacient> pacientList = service.pacientsWithoutBeds();
        List<Pacient2PacientDiagnosticDTO> diagnosticDTOList = pacient2PacientDiagnosticMapper.convert(pacientList);
        diagnosticModel.setAll(diagnosticDTOList);
    }

    @FXML
    private void onClickAddPacient(ActionEvent actionEvent) {
        Pacient2PacientDiagnosticDTO selectedPacient = pacientTableView.getSelectionModel().getSelectedItem();
        BedType selectedType = bedTypeComboBox.getValue();
        service.addPacientToBed(selectedType, selectedPacient);
        initModel();
    }
}
