package com.project.qlbh_kh.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class TrangChuController {
    private MainAppController mainAppController;

    @FXML
    private Button productManageButton;

    public void setMainAppController(MainAppController mainAppController) {
        this.mainAppController = mainAppController;
    }

    @FXML
    private void initialize() {
        productManageButton.setOnAction(event -> {
            if (mainAppController != null) {
                try {
                    mainAppController.loadContent("/com/project/qlbh_kh/views/QuanLyMatHangView.fxml");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
