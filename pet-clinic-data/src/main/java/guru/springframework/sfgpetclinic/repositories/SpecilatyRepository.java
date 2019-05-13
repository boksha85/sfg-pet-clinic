package guru.springframework.sfgpetclinic.repositories;

import guru.springframework.sfgpetclinic.model.Specialty;
import org.springframework.data.repository.CrudRepository;

public interface SpecilatyRepository extends CrudRepository<Specialty, Long> {
}
