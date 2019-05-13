package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Specialty;
import guru.springframework.sfgpetclinic.repositories.SpecilatyRepository;
import guru.springframework.sfgpetclinic.services.SpecialtyService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class SpecialtySDJpaService implements SpecialtyService {

  private final SpecilatyRepository specilatyRepository;

  public SpecialtySDJpaService(SpecilatyRepository specilatyRepository) {
    this.specilatyRepository = specilatyRepository;
  }

  @Override
  public Set<Specialty> findAll() {
    Set<Specialty> specialties = new HashSet<>();
    specilatyRepository.findAll().forEach(specialties::add);
    return specialties;
  }

  @Override
  public Specialty findById(Long aLong) {
    return specilatyRepository.findById(aLong).orElse(null);
  }

  @Override
  public Specialty save(Specialty object) {
    return specilatyRepository.save(object);
  }

  @Override
  public void delete(Specialty object) {
    specilatyRepository.delete(object);
  }

  @Override
  public void deleteById(Long aLong) {
    specilatyRepository.deleteById(aLong);
  }
}
