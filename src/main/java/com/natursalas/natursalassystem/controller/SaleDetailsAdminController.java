package com.natursalas.natursalassystem.controller;

import com.natursalas.natursalassystem.model.dao.ProductDAO;
import com.natursalas.natursalassystem.model.dto.ProductDTO;
import com.natursalas.natursalassystem.model.dto.SaleDetailDTO;
import com.natursalas.natursalassystem.model.dto.SaleDetailSpecialDTO;
import com.natursalas.natursalassystem.service.DatabaseConnection;
import com.natursalas.natursalassystem.service.SaleDetailService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;

public class SaleDetailsAdminController implements Initializable {

    @FXML
    private TableColumn<SaleDetailSpecialDTO, String> saleDetails_columna_sede;

    @FXML
    private TableColumn<SaleDetailSpecialDTO, String> saleDetails_columna_producto;

    @FXML
    private TableColumn<SaleDetailSpecialDTO, Integer> saleDetails_columna_precioUnitario;

    @FXML
    private TableColumn<SaleDetailSpecialDTO, Integer> saleDetails_columna_cantidadVendida;

    @FXML
    private TableColumn<SaleDetailSpecialDTO, Integer> saleDetails_columna_precioTotal;

    @FXML
    private TableView<SaleDetailSpecialDTO> saleDetails_tableViewDetalles;

    private SaleDetailService saleDetailService;
    private ProductDAO productDAO;

    private final ObservableList<SaleDetailSpecialDTO> observableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarBaseDatos();
        configurarColumnas();
    }

    private void configurarBaseDatos() {
        Connection connection = DatabaseConnection.getConnection();
        saleDetailService = new SaleDetailService(connection);
        productDAO = new ProductDAO(connection);
    }

    private void configurarColumnas() {
        saleDetails_columna_sede.setCellValueFactory(new PropertyValueFactory<>("idLocation"));
        saleDetails_columna_producto.setCellValueFactory(new PropertyValueFactory<>("productName"));
        saleDetails_columna_precioUnitario.setCellValueFactory(new PropertyValueFactory<>("price"));
        saleDetails_columna_cantidadVendida.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        saleDetails_columna_precioTotal.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
    }

    public void cargarDetallesVenta(String idSale) {
        List<SaleDetailDTO> saleDetails = saleDetailService.getSalesDetailsBySaleId(idSale);

        for (SaleDetailDTO saleDetail : saleDetails) {
            ProductDTO product = productDAO.getProduct(saleDetail.getIdProduct(), saleDetail.getIdLocation());
            if (product != null) {
                observableList.add(new SaleDetailSpecialDTO(
                        saleDetail.getIdLocation(),
                        product.getProductName(),
                        saleDetail.getPrice(),
                        saleDetail.getQuantity(),
                        saleDetail.getSubtotal()
                ));
            }
        }

        saleDetails_tableViewDetalles.setItems(observableList);
        saleDetails_tableViewDetalles.refresh();
    }
}