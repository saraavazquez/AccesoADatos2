package northwind.db;

import java.sql.Connection;
import java.sql.Statement;

public class DBInit {

    public static void createTables() {
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            Statement st = conn.createStatement();

            st.execute("""
                CREATE TABLE IF NOT EXISTS productos (
                    id INT PRIMARY KEY,
                    nombre VARCHAR(255),
                    descripcion VARCHAR(1000),
                    cantidad INT,
                    precio DOUBLE
                );
            """);

            st.execute("""
                CREATE TABLE IF NOT EXISTS empleados (
                    id INT PRIMARY KEY,
                    nombre VARCHAR(255),
                    puesto VARCHAR(255)
                );
            """);

            st.execute("""
                CREATE TABLE IF NOT EXISTS pedidos (
                    id INT PRIMARY KEY,
                    id_producto INT,
                    cantidad INT,
                    fecha DATE,
                    id_empleado INT,
                    FOREIGN KEY (id_producto) REFERENCES productos(id),
                    FOREIGN KEY (id_empleado) REFERENCES empleados(id)
                );
            """);

            st.execute("""
                CREATE TABLE IF NOT EXISTS productos_fav (
                     id INT AUTO_INCREMENT PRIMARY KEY,
                     id_producto INT UNIQUE,
                     FOREIGN KEY (id_producto) REFERENCES productos(id)
                );
             """);


            st.close();
            System.out.println("✔ Tablas creadas con éxito.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
