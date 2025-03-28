package com.natursalas.natursalassystem.controller;

import com.natursalas.natursalassystem.model.dto.ProductDTO;
import com.natursalas.natursalassystem.model.dto.ProductsForLocationDTO;
import com.natursalas.natursalassystem.model.dto.SaleDetailDTO;
import com.natursalas.natursalassystem.model.dto.ViewSaleDetailDTO;
import com.natursalas.natursalassystem.service.DatabaseConnection;
import com.natursalas.natursalassystem.service.ProductService;
import com.natursalas.natursalassystem.service.ProductsForLocationService;
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

public class SedeSaleDetailsController implements Initializable {
    private final ObservableList<ViewSaleDetailDTO> observableList = FXCollections.observableArrayList();

    @FXML
    private TableColumn<ViewSaleDetailDTO, Integer> saleDetails_columna_cantidadVendida;
    @FXML
    private TableColumn<ViewSaleDetailDTO, Integer> saleDetails_columna_precioTotal;
    @FXML
    private TableColumn<ViewSaleDetailDTO, Integer> saleDetails_columna_precioUnitario;
    @FXML
    private TableColumn<ViewSaleDetailDTO, String> saleDetails_columna_producto;
    @FXML
    private TableView<ViewSaleDetailDTO> saleDetails_tableViewDetalles;

    private SaleDetailService saleDetailService;
    private ProductService productService;
    private ProductsForLocationService productsForLocationService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarBaseDatos();
        configurarColumnas();
    }

    private void configurarBaseDatos() {
        Connection connection = DatabaseConnection.getConnection();
        saleDetailService = new SaleDetailService(connection);
        productService = new ProductService(connection);
        productsForLocationService = new ProductsForLocationService(connection);
    }

    private void configurarColumnas() {
        saleDetails_columna_producto.setCellValueFactory(new PropertyValueFactory<>("productName"));
        saleDetails_columna_precioUnitario.setCellValueFactory(new PropertyValueFactory<>("price"));
        saleDetails_columna_cantidadVendida.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        saleDetails_columna_precioTotal.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
    }

    public void cargarDetallesVenta(String idSale) {
        List<SaleDetailDTO> saleDetails = saleDetailService.getSalesDetailsBySaleId(idSale);

        for (SaleDetailDTO saleDetail : saleDetails) {
            ProductsForLocationDTO productForLocation = productsForLocationService.getProductInLocation(saleDetail.getIdProduct(), saleDetail.getIdLocation());
            ProductDTO product = productService.getProduct(saleDetail.getIdProduct());
            if (product != null) {
                observableList.add(new ViewSaleDetailDTO(saleDetail.getIdLocation(), product.getProductName(), saleDetail.getPrice(), saleDetail.getQuantity(), saleDetail.getSubtotal()));
            }
        }

        saleDetails_tableViewDetalles.setItems(observableList);
        saleDetails_tableViewDetalles.refresh();
    }
}