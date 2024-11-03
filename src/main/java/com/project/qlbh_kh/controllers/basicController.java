package com.project.qlbh_kh.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class basicController implements Initializable {
    @FXML
    protected Button execute;

    @FXML
    protected DatePicker fromDate;
    protected LocalDate fromDateValue;
    @FXML
    protected ComboBox<String> operationBox;
    protected String operation;

    @FXML
    protected TextField customerField;

    @FXML
    protected ComboBox<String> orderTypeBox;
    protected String orderType;

    @FXML
    protected TextField receiverField;

    @FXML
    protected TextField productField;

    @FXML
    protected DatePicker toDate;
    protected LocalDate toDateValue;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // ComboBox
        //operationBox
        if (operationBox != null) {
            // Thực hiện các thao tác với operationBox
            ObservableList<String> operations = FXCollections.observableArrayList("Nhập", "Xuất");
            operationBox.setItems(operations);
            EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if ("Nhập".equals(operationBox.getValue())) {
                        operation = "in";
                    } else if ("Xuất".equals(operationBox.getValue())) {
                        operation = "out";
                    }
                }
            };
            operationBox.setOnAction(event);
        }
        //DatePicker
        //Format Date
        StringConverter<LocalDate> converter = new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter =
                    DateTimeFormatter.ofPattern("dd-MM-yyyy");

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }
            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        };
        fromDate.setConverter(converter);
        toDate.setConverter(converter);
    }
    @FXML
    public void enterFromDate() {
        fromDateValue = fromDate.getValue();
    }
    @FXML
    public void enterToDate() {
        toDateValue = toDate.getValue();
    }
}
