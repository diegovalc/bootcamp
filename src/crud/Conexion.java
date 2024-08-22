package crud;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author diego
 */
public final class Conexion {

    private static Connection con = null;

    static {
        String DB = "clientes";
        String USER = "root";
        String PASSWORD = "";
        String URL = "jdbc:mysql://localhost:3306/";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(URL + DB, USER, PASSWORD);
        } catch (Exception e) {
            System.err.println(e.getCause() + e.getMessage());
            // JOptionPane.showMessageDialog(null, "Error al conectarse a la BD");
            //} catch (ClassNotFoundException ex) {
            //    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Connection getConection() {
        return con;
    }
}
