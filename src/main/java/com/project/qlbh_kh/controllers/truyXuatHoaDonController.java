package com.project.qlbh_kh.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class truyXuatHoaDonController extends basicController {
    @FXML
    public void executeQuery() {
        if (fromDate != null) System.out.println("From Date: " + fromDateValue);
        if (toDate != null) System.out.println("To Date: " + toDateValue);
        if (operation != null) System.out.println("Operation " + operation);
        System.out.println(selectedCustomerId);
    }
    @FXML
    public void openCustomerList()
    {
        System.out.println("open customer list");
        if (operation == null)
        {
            System.out.println("null handle");
        }
        else if (operation.equals("in"))
        {
            System.out.println("in handle");
            try {
                //load view cho danh sach khach hang
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/project/qlbh_kh/views/danhSachKhachHangNhapView.fxml"));
                Scene customerInListScene = new Scene(fxmlLoader.load());
                //set controller cha cho controller cua danh sach ten mat hang
                danhSachKhachHangNhapController controller = fxmlLoader.getController();
                controller.setMainController(this);
                //tao stage moi
                Stage customerListStage = new Stage();
                customerListStage.initModality(Modality.APPLICATION_MODAL);
                customerListStage.initOwner(customerNameField.getScene().getWindow());
                customerListStage.setTitle("Danh sách khách hàng");
                customerListStage.setScene(customerInListScene);
                //show
                customerListStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (operation.equals("out"))
        {
            System.out.println("out handle");
            try {
                //load view cho danh sach khach hang
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/project/qlbh_kh/views/danhSachKhachHangXuatView.fxml"));
                Scene customerInListScene = new Scene(fxmlLoader.load());
                //set controller cha cho controller cua danh sach ten mat hang
                danhSachKhachHangXuatController controller = fxmlLoader.getController();
                controller.setMainController(this);
                //tao stage moi
                Stage customerListStage = new Stage();
                customerListStage.initModality(Modality.APPLICATION_MODAL);
                customerListStage.initOwner(customerNameField.getScene().getWindow());
                customerListStage.setTitle("Danh sách khách hàng");
                customerListStage.setScene(customerInListScene);
                //show
                customerListStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (operation.equals("both"))
        {
            System.out.println("both handle");
            try {
                //load view cho danh sach khach hang
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/project/qlbh_kh/views/danhSachKhachHangView.fxml"));
                Scene customerInListScene = new Scene(fxmlLoader.load());
                //set controller cha cho controller cua danh sach ten mat hang
                danhSachKhachHangController controller = fxmlLoader.getController();
                controller.setMainController(this);
                //tao stage moi
                Stage customerListStage = new Stage();
                customerListStage.initModality(Modality.APPLICATION_MODAL);
                customerListStage.initOwner(customerNameField.getScene().getWindow());
                customerListStage.setTitle("Danh sách khách hàng");
                customerListStage.setScene(customerInListScene);
                //show
                customerListStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
