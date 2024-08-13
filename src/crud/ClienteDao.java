package crud;

import java.util.List;

/**
 *
 * @author diego
 */
public interface ClienteDao {

    public void insertar(Cliente cliente);

    public void modificar(Cliente cliente);

    public void eliminar(Cliente cliente);

    public List<Cliente> buscarCliente(Filter filter, Page page);


}
