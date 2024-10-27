package com.project.qlbh_kh.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;

public class quanLyHangNhapController {

    @FXML
    private TableColumn<?, ?> customer_in_id;

    @FXML
    private Button execute;

    @FXML
    private DatePicker fromDate;

    @FXML
    private TableColumn<?, ?> order_date;

    @FXML
    private TableColumn<?, ?> order_in_id;

    @FXML
    private TableColumn<?, ?> prod_id;

    @FXML
    private TableColumn<?, ?> quantity;

    @FXML
    private DatePicker toDate;

    @FXML
    private TableColumn<?, ?> total_amount;

    @FXML
    private TableColumn<?, ?> unit_price;

}
