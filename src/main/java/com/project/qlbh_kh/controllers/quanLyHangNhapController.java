package com.project.qlbh_kh.controllers;

import javafx.fxml.FXML;

public class quanLyHangNhapController extends basicController {
    @FXML
    public void executeQuery()
    {
        if (fromDate != null) System.out.println("From Date: " + fromDateValue);
        if (toDate != null) System.out.println("To Date: " + toDateValue);
        if (operation != null) System.out.println("Operation " + operation);
    }
}
