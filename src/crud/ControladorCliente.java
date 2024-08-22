package crud;

import java.util.List;

/**
 *
 * @author diego
 */
public final class ControladorCliente {

    private final ClienteDao clienteDao;

    public ControladorCliente() {
        this.clienteDao = new ClienteDaoImp();
    }

    public void agregarCliente(Cliente cliente) {
        clienteDao.insertar(cliente);
    }

    public List<Cliente> buscarCliente(Filter filter, Page page) {
        List<Cliente> clientes = clienteDao.buscarCliente(filter, page);
        return clientes;

    }
}
