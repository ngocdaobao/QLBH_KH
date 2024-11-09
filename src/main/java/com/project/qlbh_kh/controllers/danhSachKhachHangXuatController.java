package com.project.qlbh_kh.controllers;

import com.project.qlbh_kh.entity.customer;
import com.project.qlbh_kh.utils.JDBCUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class danhSachKhachHangXuatController extends danhSachKhachHangController implements Initializable {
    @Override
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
