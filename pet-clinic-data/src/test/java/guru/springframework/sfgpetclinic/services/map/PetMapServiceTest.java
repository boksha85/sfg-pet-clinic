package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PetMapServiceTest {

  PetMapService petMapService;

  @BeforeEach
  void setUp() {
    petMapService = new PetMapService();
    petMapService.save(Pet.builder().id(1L).build());
  }

  @Test
  void findAll() {
    assertEquals(1, petMapService.findAll().size());
  }

  @Test
  void delete() {
    petMapService.delete(petMapService.findById(1L));
    assertEquals(0, petMapService.findAll().size());
  }

  @Test
  void saveExistingId() {
    Long ID = 2L;
    Pet savedPet = petMapService.save(Pet.builder().id(ID).build());
    assertNotNull(savedPet);
    assertEquals(ID, savedPet.getId());
    assertEquals(2, petMapService.findAll().size());
  }

  @Test
  void saveNoId() {
    Pet savedPet = petMapService.save(Pet.builder().build());
    assertNotNull(savedPet);
    assertNotNull(savedPet.getId());
    assertEquals(2, petMapService.findAll().size());
  }

  @Test
  void deleteById() {
    petMapService.deleteById(1L);
    assertEquals(0, petMapService.findAll().size());
  }

  @Test
  void findById() {
    Long id = 1L;
    Pet pet = petMapService.findById(id);
    assertNotNull(pet);
    assertEquals(id, pet.getId());

  }
}