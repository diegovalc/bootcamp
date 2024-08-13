package crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diego
 */
public final class ClienteDaoImp implements ClienteDao {

    Conexion conexion = new Conexion();
    Connection con;

    private class ClienteException extends RuntimeException {

        public ClienteException(String error, Throwable e) {
            super(error, e);
        }
    }

    @Override
    public void insertar(Cliente cliente) {
        con = conexion.getConection();
        PreparedStatement ps = null;
        String sql = "insert into cliente (nombre,email,telefono,tipo_cliente) values (?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getEmail());
            ps.setString(3, cliente.getTelefono());
            ps.setInt(4, cliente.getTipo_cliente());
            ps.execute();
        } catch (Exception e) {
            //System.err.println(e);
            throw new ClienteException("Algo fallo con la BD y no se pudo agregar el registro", e);
        }

    }

    @Override
    public void modificar(Cliente cliente) {
        con = conexion.getConection();
        PreparedStatement ps = null;
        String sql = "update cliente set nombre = ? , email = ? , telefono = ? where id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getEmail());
            ps.setString(3, cliente.getTelefono());
            ps.setInt(4, cliente.getId());
            ps.execute();
        } catch (Exception e) {
            throw new ClienteException("Error al intentar modificar el registo", e);
        }
    }

    @Override
    public void eliminar(Cliente cliente) {
        con = conexion.getConection();
        PreparedStatement ps = null;
        String sql = "delete from cliente where id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, cliente.getId());
            ps.execute();
        } catch (Exception e) {
            throw new ClienteException("No se pudo eliminar el registro de la BD", e);
        }
    }

    @Override
    public List<Cliente> buscarCliente(Filter filter, Page page) {
        List<Cliente> clientes = new ArrayList<>();
        con = conexion.getConection();
        PreparedStatement ps = null;
        String sql = "Select * from cliente where nombre like ? limit ? offset ?";
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + filter.getDescription() + "%");
            ps.setInt(2, page.getPageSize());
            ps.setInt(3, page.getOffSet());
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setEmail(rs.getString("email"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setTipo_cliente(rs.getInt("tipo_cliente"));
                clientes.add(cliente);
            }

        } catch (Exception e) {
            throw new ClienteException("No se pudo obtener resultados", e);
        }
        return clientes;
    }

}
