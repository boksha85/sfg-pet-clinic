package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest {

  OwnerMapService ownerMapService;
  final Long ownerId = 1L;
  final String lastName = "Sutulovic";

  @BeforeEach
  void setUp() {
    ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());
    ownerMapService.save(Owner.builder().id(ownerId).lastName(lastName).build());
  }

  @Test
  void findAll() {
    Set<Owner> ownerSet =  ownerMapService.findAll();
    assertEquals(1, ownerSet.size());

  }

  @Test
  void delete() {
    ownerMapService.delete(ownerMapService.findById(ownerId));
    assertEquals(0, ownerMapService.findAll().size());
  }

  @Test
  void saveExistingId() {
    Long id = 2L;
    Owner owner2 = Owner.builder().id(2L).build();
    Owner savedOwner = ownerMapService.save(owner2);
    assertEquals(id, savedOwner.getId());
  }

  @Test
  void saveNoId() {
    Owner savedOwner = ownerMapService.save(Owner.builder().build());
    assertNotNull(savedOwner);
    assertNotNull(savedOwner.getId());

  }

  @Test
  void deleteById() {
    ownerMapService.deleteById(ownerId);
    assertEquals(0, ownerMapService.findAll().size());
  }

  @Test
  void findById() {
    Owner owner = ownerMapService.findById(ownerId);
    assertEquals(ownerId, owner.getId());
  }

  @Test
  void findByLastName() {
    Owner sutulovic = ownerMapService.findByLastName(lastName);
    assertNotNull(sutulovic);
    assertEquals(ownerId, sutulovic.getId());
    assertEquals(lastName, sutulovic.getLastName());
  }

  @Test
  void findByLastNameNotFound() {
    String lastName2 = "Petrovic";
    Owner notFound = ownerMapService.findByLastName(lastName2);
    assertNull(notFound);
  }
}