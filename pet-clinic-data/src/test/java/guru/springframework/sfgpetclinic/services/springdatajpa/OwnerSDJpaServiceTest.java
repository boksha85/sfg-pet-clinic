package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

//junit 5 for Mockito
@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

  @Mock
  OwnerRepository ownerRepository;

  @Mock
  PetRepository petRepository;

  @Mock
  PetTypeRepository petTypeRepository;

  //setup service
  @InjectMocks
  OwnerSDJpaService service;

  final Long ID = 1L;
  final String LAST_NAME = "Sutulovic";
  Owner returnOwner;

  @BeforeEach
  void setUp() {
    returnOwner = Owner.builder().id(ID).lastName(LAST_NAME).build();
  }

  @Test
  void findByLastName() {

    when(ownerRepository.findByLastName(any())).thenReturn(returnOwner);
    Owner sutulovic = service.findByLastName(LAST_NAME);
    assertEquals(LAST_NAME, sutulovic.getLastName());
    assertEquals(ID, sutulovic.getId());
    verify(ownerRepository).findByLastName(any());
  }

  @Test
  void findAll() {
    Set<Owner> returnOwnerSet = new HashSet<>();
    returnOwnerSet.add(Owner.builder().id(1L).build());
    returnOwnerSet.add(Owner.builder().id(2L).build());
    when(ownerRepository.findAll()).thenReturn(returnOwnerSet);
    Set<Owner> owners = service.findAll();
    assertNotNull(owners);
    assertEquals(2, owners.size());
  }

  @Test
  void findById() {
    when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnOwner));
    Owner owner = service.findById(ID);
    assertNotNull(owner);
    assertEquals(ID, owner.getId());
  }

  @Test
  void findByIdNotFound() {
    when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());
    Owner owner = service.findById(ID);
    assertNull(owner);
  }

  @Test
  void save() {
    Owner ownerToSave = Owner.builder().id(ID).build();
    when(ownerRepository.save(any())).thenReturn(returnOwner);
    Owner savedOwner = service.save(ownerToSave);
    assertNotNull(savedOwner);
    assertEquals(ID, savedOwner.getId());
    verify(ownerRepository).save(any());
  }

  @Test
  void delete() {
    service.delete(returnOwner);
    verify(ownerRepository, times(1)).delete(any());
  }

  @Test
  void deleteById() {
    service.deleteById(ID);
    verify(ownerRepository, times(1)).deleteById(anyLong());
  }
}