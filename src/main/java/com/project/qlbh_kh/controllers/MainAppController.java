package com.project.qlbh_kh.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainAppController implements Initializable {
    @FXML
    private TreeView<String> treeView;

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private StackPane contentArea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TreeItem<String> root = new TreeItem<>();
        TreeItem<String> hoaDon = new TreeItem<>("Hóa đơn");
        TreeItem<String> hangNhap = new TreeItem<>("Hàng Nhập");
        TreeItem<String> hangXuat = new TreeItem<>("Hàng Xuất");
        TreeItem<String> hangTonKho = new TreeItem<>("Hàng Tồn Kho");
        root.getChildren().addAll(hoaDon, hangNhap, hangXuat, hangTonKho);

        // hóa đơn
        TreeItem<String> taoHoaDon = new TreeItem<>("Tạo hóa đơn");
        TreeItem<String> truyXuatHoaDon = new TreeItem<>("Truy xuất hóa đơn");
        hoaDon.getChildren().addAll(taoHoaDon, truyXuatHoaDon);

        // tồn kho
        TreeItem<String> kiemKho = new TreeItem<>("Kiểm kho");
        TreeItem<String> truyXuatKho = new TreeItem<>("Truy xuất kho");
        hangTonKho.getChildren().addAll(kiemKho, truyXuatKho);

        root.setExpanded(true);
        treeView.setRoot(root);
        treeView.setShowRoot(false);

        treeView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldItem, newItem) -> {
            if (newItem != null) {
                String selectedItem = newItem.getValue();
                if (newItem.isExpanded()) newItem.setExpanded(false);
                else newItem.setExpanded(true);
                // Thay đổi giao diện ở phần giữa (center)
                try {
                    switch (selectedItem) {
                        case "Tạo hóa đơn":
                            loadContent("/com/project/qlbh_kh/views/taoHoaDonView.fxml");
                            break;
                        case "Truy xuất hóa đơn":
                            loadContent("/com/project/qlbh_kh/views/truyXuatHoaDonView.fxml");
                            break;
                        case "Hàng Nhập":
                            loadContent("/com/project/qlbh_kh/views/quanLyHangNhapView.fxml");
                            break;
                        case "Hàng Xuất":
                            loadContent("/com/project/qlbh_kh/views/quanLyHangXuatView.fxml");
                            break;
                        case "Kiểm kho":
                            loadContent("/com/project/qlbh_kh/views/kiemKhoView.fxml");
                            break;
                        case "Truy xuất kho":
                            loadContent("/com/project/qlbh_kh/views/truyXuatKhoView.fxml");
                            break;
                        default:
                            contentArea.getChildren().clear(); // Xóa nội dung nếu không có lựa chọn phù hợp
                            break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Phương thức để tải và thay thế nội dung ở phần giữa (center) của BorderPane
    private void loadContent(String fxmlFile) throws IOException {
        Node node = FXMLLoader.load(getClass().getResource(fxmlFile));
        contentArea.getChildren().setAll(node);
    }
}
