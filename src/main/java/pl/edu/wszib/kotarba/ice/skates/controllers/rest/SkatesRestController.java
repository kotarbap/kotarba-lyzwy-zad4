package pl.edu.wszib.kotarba.ice.skates.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.kotarba.ice.skates.database.ISkatesDAO;
import pl.edu.wszib.kotarba.ice.skates.model.Skates;
import pl.edu.wszib.kotarba.ice.skates.service.ISkatesService;

import java.util.List;
import java.util.Optional;

@RestController
public class SkatesRestController {

    @Autowired
    ISkatesService skatesService;
    @Autowired
    ISkatesDAO skatesDAOImpl;


    @RequestMapping(value = "/endpoint1", method = RequestMethod.GET)
    public List<Skates> getAllSkates() {
        return this.skatesService.getAllSkates();
    }

    @RequestMapping(value = "/endpoint2/{skatesId}", method = RequestMethod.POST)
    public Optional<Skates> getSkatesById(@PathVariable int skatesId) {
        return this.skatesDAOImpl.getSkatesById(skatesId);
    }

    @RequestMapping(value = "/endpoint3", method = RequestMethod.POST)
    public Skates changeSkatesById(@RequestBody Skates skates) {
        this.skatesDAOImpl.updateSkates(skates);
        return skates;
    }
}
