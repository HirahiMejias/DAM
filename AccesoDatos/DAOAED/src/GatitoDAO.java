import java.util.ArrayList;

public interface GatitoDAO {
    ArrayList<Gatito> getGatitos();
    int deleteGatito(Gatito gatito);
    int addGatito(Gatito gatito);
    int updateGatito(Gatito gatito);
}
