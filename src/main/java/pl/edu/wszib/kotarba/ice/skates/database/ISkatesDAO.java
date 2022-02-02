package pl.edu.wszib.kotarba.ice.skates.database;

import pl.edu.wszib.kotarba.ice.skates.model.Skates;

import java.util.List;
import java.util.Optional;

public interface ISkatesDAO {
    List<Skates> getSkates();
    Optional<Skates> getSkatesById(int skatesId);
    void updateSkates(Skates skates);
}
