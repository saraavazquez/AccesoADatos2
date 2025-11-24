package northwind;

import northwind.db.DBInit;
import northwind.service.JSONImporter;
import northwind.service.ProductService;

public class Main {
    public static void main(String[] args) {

        DBInit.createTables();

        var productos = JSONImporter.fetchProducts();
        ProductService.insertProducts(productos);

        ProductService.insertEmpleadosAndPedidos();

        ProductService.insertFavProductsPriceGreaterThan1000();

        ProductService.printAllProducts();
        ProductService.printFavProducts();
        ProductService.printProductsLess600();
    }
}

