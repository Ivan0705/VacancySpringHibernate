package academits.demo.dao;

import academits.demo.model.Area;
import org.springframework.stereotype.Repository;

@Repository
public class AreaDao extends GenericDaoImpl<Area, Integer> {
    public AreaDao() {
        super(Area.class);
    }
}
