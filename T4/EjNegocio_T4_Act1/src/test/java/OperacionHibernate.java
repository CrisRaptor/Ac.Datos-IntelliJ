import com.acdat.dao.DireccionDAO;
import com.acdat.domain.Direccion;

public class OperacionHibernate
{
    public static void main(String[] args) {
        DireccionDAO direccionDAO = new DireccionDAO();
        Direccion direccion = new Direccion();
        direccionDAO.listar();
    }
}
