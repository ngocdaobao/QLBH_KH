package com.project.qlbh_kh.controllers;

import com.project.qlbh_kh.entity.product;
import com.project.qlbh_kh.utils.JDBCUtil;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ChinhSuaMatHangController {
    @FXML private TextField oldPriceIn;
    @FXML private TextField newPriceIn;
    @FXML private TextField oldPriceOut;
    @FXML private TextField newPriceOut;
    @FXML private Label productNameLabel;
    @FXML private Button confirmButton;
    private QuanLyMatHangController mainController;
    private product selectedProduct;
    public void setMainController(QuanLyMatHangController quanLyMatHangController)
    {
        this.mainController = quanLyMatHangController;
    }
    public void setSelectedProduct(product selectedProduct)
    {
        this.selectedProduct = selectedProduct;
        productNameLabel.setText(selectedProduct.getProd_name());
        oldPriceIn.setText(Double.toString(selectedProduct.getPrice_in()));
        oldPriceOut.setText(Double.toString(selectedProduct.getPrice_out()));
        newPriceIn.requestFocus();
    }
    @FXML
    public void modifyDatabase()
    {
        String newPriceInValue = newPriceIn.getText().trim();
        String newPriceOutValue = newPriceOut.getText().trim();
        // Nếu giá mới không được nhập, sử dụng giá cũ
        try
        {
            double priceIn = newPriceInValue.isEmpty() ? selectedProduct.getPrice_in() : Double.parseDouble(newPriceInValue);
            double priceOut = newPriceOutValue.isEmpty() ? selectedProduct.getPrice_out() : Double.parseDouble(newPriceOutValue);
            Connection connection = JDBCUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE products_tb SET price_in = ?, price_out = ? WHERE prod_id = ?");
            preparedStatement.setDouble(1, priceIn);
            preparedStatement.setDouble(2, priceOut);
            preparedStatement.setInt(3, selectedProduct.getProd_id());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                // Thông báo thành công
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thành công");
                alert.setContentText("Đã cập nhật thông tin mặt hàng thành công!");
                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        // Đóng cửa sổ chỉnh sửa
                        confirmButton.getScene().getWindow().hide();
                        // Gọi phương thức cập nhật danh sách trong mainController
                        if (mainController != null) {
                            mainController.openProductList();
                        }
                    }
                });
            }
        }
        catch (NumberFormatException e)
        {
            showAlert("Lỗi", "Vui lòng nhập đúng định dạng số!", Alert.AlertType.ERROR);
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
