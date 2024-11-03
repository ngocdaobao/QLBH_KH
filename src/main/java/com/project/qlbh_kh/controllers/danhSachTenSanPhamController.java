package com.project.qlbh_kh.controllers;

import com.project.qlbh_kh.utils.JDBCUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class danhSachTenSanPhamController implements Initializable {

    @FXML
    private TextField productNameField;

    @FXML
    private ListView<String> productNameList;
    ObservableList<String> productNames = FXCollections.observableArrayList();

    @FXML
    void filterProductName(ActionEvent event) {
        System.out.println(productNameField.getText());
    }
    //ham set controller cha
    private truyXuatKhoController mainController;
    public void setMainController(truyXuatKhoController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //load danh sach ten mat hang
        loadProductName();
        //filter theo ten mat hang
        productNameField.textProperty().addListener(((observableValue, oldValue, newValue) -> {
            productNameList.setItems(productNames.filtered(productName -> productName.toLowerCase().contains(newValue.toLowerCase())));
        }));
        //chon mat hang
        productNameList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, selectedProduct) -> {
            if (selectedProduct != null && mainController != null) {
                mainController.setSelectedProductName(selectedProduct); // truyen mat hang da chon ve cho controller cha
                ((Stage) productNameList.getScene().getWindow()).close(); // dong cua so
            }
        });
    }
    //ham load danh sach ten mat hang
    public void loadProductName()
    {
        String sql = "use BTL_QL_BanHang\n" +
                "select prod_name\n" +
                "from products_tb";
        try{
            Connection connection = JDBCUtil.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) productNames.add(resultSet.getString(1));
            productNameList.setItems(productNames);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
