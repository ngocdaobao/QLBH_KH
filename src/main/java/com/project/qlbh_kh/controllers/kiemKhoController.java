package com.project.qlbh_kh.controllers;

import com.project.qlbh_kh.entity.matHang;
import com.project.qlbh_kh.utils.JDBCUtil;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class kiemKhoController {
    @FXML
    private TableView<matHang> tableView;
    @FXML
    private TableColumn<matHang, String> prodNameColumn;
    @FXML
    private TableColumn<matHang,String> prodQuantityColumn;
    @FXML
    public void initialize() {
        prodNameColumn.setCellValueFactory(new PropertyValueFactory<>("tenMatHang"));
        prodQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("soLuong"));

        loadStockData();
    }
    private void loadStockData() {
        ObservableList<matHang> matHangList = FXCollections.observableArrayList();
        Connection conn = null;

        try {
            conn = JDBCUtil.getConnection();
            String query = "SELECT prod_name, quantity FROM stock_tb";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String tenMatHang = rs.getString(1);
                int soLuong = rs.getInt(2);
                matHangList.add(new matHang(tenMatHang, soLuong));
            }
            tableView.setItems(matHangList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(conn);
        }
    }
}
