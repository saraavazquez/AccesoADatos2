package northwind.service;

import northwind.db.DBConnection;
import northwind.model.Product;

import java.sql.*;

public class ProductService {

    public static void insertProducts(java.util.List<Product> products) {
        String sql = """
            MERGE INTO productos (id, nombre, descripcion, cantidad, precio)
            KEY(id) VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            for (Product p : products) {
                ps.setInt(1, p.getId());
                ps.setString(2, p.getTitle());
                ps.setString(3, p.getDescription());
                ps.setInt(4, p.getStock());
                ps.setDouble(5, p.getPrice());
                ps.addBatch();
            }

            ps.executeBatch();
            System.out.println("✔ Productos insertados: " + products.size());

        } catch (Exception e) { e.printStackTrace(); }
    }

    public static void insertEmpleadosAndPedidos() {
        try (Connection conn = DBConnection.getInstance().getConnection();
             Statement st = conn.createStatement()) {

            st.executeUpdate("MERGE INTO empleados KEY(id) VALUES (1,'Ana','Comercial')");
            st.executeUpdate("MERGE INTO empleados KEY(id) VALUES (2,'Luis','Almacén')");

            st.executeUpdate("MERGE INTO pedidos KEY(id) VALUES (100,1,2,'2025-11-01',1)");
            st.executeUpdate("MERGE INTO pedidos KEY(id) VALUES (101,2,1,'2025-11-02',2)");

            System.out.println("✔ Empleados y pedidos insertados.");

        } catch (Exception e) { e.printStackTrace(); }
    }

    // 🔥 MÉTODO CORREGIDO: evita duplicados
    public static void insertFavProductsPriceGreaterThan1000() {
        try (Connection conn = DBConnection.getInstance().getConnection();
             Statement st = conn.createStatement()) {

            int rows = st.executeUpdate("""
                MERGE INTO productos_fav (id_producto)
                KEY(id_producto)
                SELECT id FROM productos WHERE precio > 1000
            """);

            System.out.println("✔ Insertados en favoritos: " + rows);

        } catch (Exception e) { e.printStackTrace(); }
    }

    // 🧹 MÉTODO PARA LIMPIAR DUPLICADOS (solo usar una vez)
    public static void clearFavProducts() {
        try (Connection conn = DBConnection.getInstance().getConnection();
             Statement st = conn.createStatement()) {

            st.executeUpdate("TRUNCATE TABLE productos_fav");
            System.out.println("✔ Tabla productos_fav limpiada.");

        } catch (Exception e) { e.printStackTrace(); }
    }

    public static void printAllProducts() {
        try (Connection conn = DBConnection.getInstance().getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM productos")) {

            System.out.println("\n=== TODOS LOS PRODUCTOS ===");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                                rs.getString("nombre") + " | " +
                                rs.getString("descripcion") + " | " +
                                rs.getInt("cantidad") + " | " +
                                rs.getDouble("precio"));
            }

        } catch (Exception e) { e.printStackTrace(); }
    }

    public static void printFavProducts() {
        try (Connection conn = DBConnection.getInstance().getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("""
                    SELECT p.* FROM productos p
                    JOIN productos_fav f ON p.id = f.id_producto
                """)) {

            System.out.println("\n=== PRODUCTOS FAVORITOS ===");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " | " +
                        rs.getString("nombre") + " | " +
                        rs.getDouble("precio"));
            }

        } catch (Exception e) { e.printStackTrace(); }
    }

    public static void printProductsLess600() {
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT * FROM productos WHERE precio < 600")) {

            ResultSet rs = ps.executeQuery();
            System.out.println("\n=== PRODUCTOS < 600 € ===");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " | " +
                        rs.getString("nombre") + " | " +
                        rs.getDouble("precio"));
            }

        } catch (Exception e) { e.printStackTrace(); }
    }
}
