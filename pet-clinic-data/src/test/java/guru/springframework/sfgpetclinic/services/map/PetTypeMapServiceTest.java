package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PetTypeMapServiceTest {

  PetTypeService service;

  @BeforeEach
  void setUp() {
    service = new PetTypeMapService();
    service.save(PetType.builder().id(1L).build());
    service.save(PetType.builder().id(2L).build());
  }

  @Test
  void findAll() {
    Set<PetType> petTypeSet = service.findAll();
    assertEquals(2, petTypeSet.size());
  }

  @Test
  void deleteById() {
    service.deleteById(1L);
    assertEquals(1, service.findAll().size());
  }

  @Test
  void delete() {
    service.delete(service.findById(1L));
    assertEquals(1, service.findAll().size());
  }

  @Test
  void saveExistingId() {
    Long id = 3L;
    PetType petType = PetType.builder().id(id).build();
    PetType savedPetType = service.save(petType);
    assertEquals(id, savedPetType.getId());
  }

  @Test
  void saveNoId() {
    PetType savedPetType = service.save(PetType.builder().build());
    assertNotNull(savedPetType);
    assertNotNull(savedPetType.getId());
  }

  @Test
  void findById() {
    Long id = 1L;
    PetType petType = service.findById(id);
    assertNotNull(petType);
    assertEquals(id, petType.getId());
  }
}