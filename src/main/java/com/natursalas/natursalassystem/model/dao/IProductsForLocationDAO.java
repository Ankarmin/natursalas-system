package com.natursalas.natursalassystem.model.dao;

import com.natursalas.natursalassystem.model.dto.ProductsForLocationDTO;

import java.util.List;

public interface IProductsForLocationDAO {
    boolean insertProductInLocation(ProductsForLocationDTO product);

    boolean updateProductStockAndPrice(ProductsForLocationDTO product);

    boolean removeProductFromLocation(String idProduct, String idLocation);

    ProductsForLocationDTO findProductInLocation(String idProduct, String idLocation);

    List<ProductsForLocationDTO> listAllProductsByLocation();

    boolean existsProductInLocation(String idProduct, String idLocation);
}
