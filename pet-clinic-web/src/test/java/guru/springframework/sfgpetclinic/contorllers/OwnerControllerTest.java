package guru.springframework.sfgpetclinic.contorllers;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

  @Mock
  OwnerService ownerService;

  @InjectMocks
  OwnerController controller;

  Set<Owner> owners;

  MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    owners = new HashSet<>();
    owners.add(Owner.builder().id(1L).build());
    owners.add(Owner.builder().id(2L).build());

    //For each test -> initialize mock environment
    mockMvc = MockMvcBuilders
            .standaloneSetup(controller)
            .build();
  }

  //fix static imports
  @Test
  void listOfOwners() throws Exception {

    Mockito.when(ownerService.findAll()).thenReturn(owners);

    mockMvc.perform(MockMvcRequestBuilders.get("/owners"))
            .andExpect(MockMvcResultMatchers.status().is(200));

    mockMvc.perform(MockMvcRequestBuilders.get("/owners"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("owners/index"))
            .andExpect(MockMvcResultMatchers.model().attribute("owners", Matchers.hasSize(2)));

  }

  @Test
  void listOfOwnersByIndex() throws Exception {

    Mockito.when(ownerService.findAll()).thenReturn(owners);

    mockMvc.perform(MockMvcRequestBuilders.get("/owners/index"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("owners/index"))
            .andExpect(MockMvcResultMatchers.model().attribute("owners", Matchers.hasSize(2)));

  }

  @Test
  void findOwners() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/owners/find"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("notImplemented"));

    Mockito.verifyZeroInteractions(ownerService);
  }
}