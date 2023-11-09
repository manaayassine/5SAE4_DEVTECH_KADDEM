package tn.esprit.spring.kaddem;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tn.esprit.spring.kaddem.controllers.EquipeRestController;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.services.IEquipeService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class EquipeRestControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private EquipeRestController equipeRestController;

    @Mock
    private IEquipeService equipeService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(equipeRestController).build();
    }

    @Test
    public void testGetEquipes() throws Exception {
        List<Equipe> equipes = new ArrayList<>();
        // Ajoutez des équipes à la liste equipes ici

        when(equipeService.retrieveAllEquipes()).thenReturn(equipes);

        mockMvc.perform(MockMvcRequestBuilders.get("/equipe/retrieve-all-equipes"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testRetrieveEquipe() throws Exception {
        // Remplacez le code suivant par l'ID de l'équipe que vous souhaitez récupérer
        Integer equipeId = 1;
        Equipe equipe = new Equipe();
        // Configurez l'équipe de test avec les attributs nécessaires

        when(equipeService.retrieveEquipe(equipeId)).thenReturn(equipe);

        mockMvc.perform(MockMvcRequestBuilders.get("/equipe/retrieve-equipe/{equipe-id}", equipeId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }
    @Test
    public void testAddEquipe() throws Exception {
        Equipe equipe = new Equipe();
        // Configurez l'objet equipe selon vos besoins pour le test

        when(equipeService.addEquipe(any(Equipe.class))).thenReturn(equipe);

        mockMvc.perform(MockMvcRequestBuilders.post("/equipe/add-equipe")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(equipe)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

        verify(equipeService, times(1)).addEquipe(any(Equipe.class));
    }
    @Test
    public void testRemoveEquipe() throws Exception {
        Integer equipeId = 1;

        mockMvc.perform(MockMvcRequestBuilders.delete("/equipe/remove-equipe/{equipe-id}", equipeId))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(equipeService, times(1)).deleteEquipe(equipeId);
    }

    @Test
    public void testUpdateEquipe() throws Exception {
        Equipe equipe = new Equipe();
        // Configurez l'objet equipe selon vos besoins pour le test

        when(equipeService.updateEquipe(any(Equipe.class))).thenReturn(equipe);

        mockMvc.perform(MockMvcRequestBuilders.put("/equipe/update-equipe")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(equipe)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

        verify(equipeService, times(1)).updateEquipe(any(Equipe.class));
    }


    // Ajoutez des tests similaires pour les autres méthodes du contrôleur (addEquipe, removeEquipe, updateEquipe, faireEvoluerEquipes, etc.)

}
