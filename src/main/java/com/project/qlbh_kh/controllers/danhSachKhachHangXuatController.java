package com.project.qlbh_kh.controllers;

import com.project.qlbh_kh.entity.customer;
import com.project.qlbh_kh.utils.JDBCUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class danhSachKhachHangXuatController implements Initializable {
    @FXML
    private TableView<customer> customerList;
    ObservableList<customer> customers = FXCollections.observableArrayList();
    @FXML
    private TableColumn<customer, String> addressColumn;

    @FXML
    private TableColumn<customer, String> customerNameColumn;

    @FXML
    private TextField customerNameField;

    @FXML
    private TableColumn<customer, String> phoneNumberColumn;

    private quanLyHangXuatController quanLyHangXuatController;
    public void setMainController(quanLyHangXuatController quanLyHangXuatController) {
        this.quanLyHangXuatController = quanLyHangXuatController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //cai dat thuoc tinh du lieu cho cac cot
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        //load du lieu
        loadCustomerList();
        //cai dat bo loc tim kiem theo ten KH
        customerNameField.textProperty().addListener(((observableValue, oldValue, newValue) -> {
            customerList.setItems(customers.filtered(customer -> {
                String name = customer.getName();
                System.out.println(name);
                return name.toLowerCase().contains(newValue.toLowerCase());
            }));
        }));
        //chon KH
        customerList.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (newValue != null && quanLyHangXuatController != null)
            {
                quanLyHangXuatController.setSelectedCustomer(newValue.getCustomer_id(),newValue.getName());
                ((Stage) customerList.getScene().getWindow()).close();
            }
        }));
    }

    public void loadCustomerList()
    {
        String sql = "exec customers_out_list";
        try
        {
            Connection connection = JDBCUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String address = resultSet.getString(3);
                String phone_number = resultSet.getString(4);
                customers.add(new customer(id,name,address,phone_number));
            }
            customerList.setItems(customers);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
