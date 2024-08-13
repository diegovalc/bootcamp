package crud;

import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author diego
 */
public final class ControladorCliente {

    private final Cliente cliente;
    private final ClienteDao clienteDao ;
    private final Filter filter;
    
    public ControladorCliente(){
        this.cliente= new Cliente();
        this.clienteDao= new ClienteDaoImp();
        this.filter = new Filter();
    }

    public void agregarCliente(Vista vista) {
        cliente.setNombre(vista.txtNombres.getText());
        cliente.setEmail(vista.txtEmail.getText());
        cliente.setTelefono(vista.txtTelefono.getText());
        cliente.setTipo_cliente(1);
        clienteDao.insertar(cliente);
        limpiarCampos(vista);
    }
    
    public void buscarCliente(Vista vista){
        Page page = new Page(1,5);
        // se setea el campo del filtro a buscar
        filter.setDescription(vista.txtBuscar.getText());
        List<Cliente> clientes = clienteDao.buscarCliente(filter,page);
        DefaultTableModel model = new DefaultTableModel();
        // se configuran los nombres de las columnas de la tabla
        model.addColumn("ID");
        model.addColumn("Nombres");
        model.addColumn("Email");
        model.addColumn("Télefono");
        vista.txtTabla.setModel(model);
        String[] datos = new String[4];
        
        for(Cliente cliente: clientes){
            datos[0] = String.valueOf(cliente.getId());
            datos[1] = cliente.getNombre();
            datos[2] = cliente.getEmail();
            datos[3] = cliente.getTelefono();
            model.addRow(datos);
        }
    }

    public void limpiarCampos(Vista vista) {
        vista.txtNombres.setText(null);
        vista.txtEmail.setText(null);
        vista.txtTelefono.setText(null);
        vista.txtBuscar.setText(null);
    }

}
