package com.project.qlbh_kh.controllers;

import com.project.qlbh_kh.utils.JDBCUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ThemMatHangMoiController {
    @FXML private Button confirmButton;
    @FXML private TextField productName;
    @FXML private TextField priceIn;
    @FXML private TextField priceOut;
    @FXML private TextField unitSold;
    private QuanLyMatHangController mainController;
    public void setMainController(QuanLyMatHangController quanLyMatHangController)
    {
        this.mainController = quanLyMatHangController;
    }
    @FXML
    public void addNewProductToDatabase()
    {
        String productNameInput = productName.getText();
        String priceInInput = priceIn.getText();
        String priceOutInput = priceOut.getText();
        String unitSoldInput = unitSold.getText();
        if (productNameInput.isEmpty() || priceInInput.isEmpty() || priceOutInput.isEmpty() || unitSoldInput.isEmpty()) {
            showAlert("Lỗi", "Vui lòng điền đầy đủ thông tin trước khi xác nhận!", Alert.AlertType.ERROR);
            return;
        }
        try
        {
            double priceInValue = Double.parseDouble(priceInInput);
            double priceOutValue = Double.parseDouble(priceOutInput);

            Connection connection = JDBCUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO products_tb (prod_name, price_in, price_out, unit_sold) VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, productNameInput);
            preparedStatement.setDouble(2, priceInValue);
            preparedStatement.setDouble(3, priceOutValue);
            preparedStatement.setString(4, unitSoldInput);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thành công");
                alert.setContentText("Đã thêm mặt hàng mới thành công!");
                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        // Đóng cửa sổ thêm mặt hàng
                        confirmButton.getScene().getWindow().hide();
                        // Gọi openProductList
                        if (mainController != null) {
                            mainController.openProductList();
                        }
                    }
                });
            } else {
                showAlert("Lỗi", "Không thể thêm mặt hàng. Vui lòng thử lại.", Alert.AlertType.ERROR);
            }
        }
        catch (NumberFormatException e)
        {
            showAlert("Lỗi", "Vui lòng nhập đúng định dạng cho giá mua vào và giá bán ra!", Alert.AlertType.ERROR);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
