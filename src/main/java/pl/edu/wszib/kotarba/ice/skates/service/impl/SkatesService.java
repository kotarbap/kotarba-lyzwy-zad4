package pl.edu.wszib.kotarba.ice.skates.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.kotarba.ice.skates.database.ISkatesDAO;
import pl.edu.wszib.kotarba.ice.skates.model.Skates;
import pl.edu.wszib.kotarba.ice.skates.service.ISkatesService;

import java.util.List;

@Service
public class SkatesService implements ISkatesService {

    @Autowired
    ISkatesDAO skatesDAO;

    public List<Skates> getAllSkates() {
        return this.skatesDAO.getSkates();
    }

}
