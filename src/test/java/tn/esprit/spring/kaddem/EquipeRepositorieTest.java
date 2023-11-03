package tn.esprit.spring.kaddem;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;
import tn.esprit.spring.kaddem.services.EquipeServiceImpl;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EquipeRepositorieTest {
    @Mock
    private EquipeRepository equipeRepository;

    @InjectMocks
    private EquipeServiceImpl equipeService;

   /* @Test
    void testFindEquipeById() {
        // Créez un objet Equipe de test avec un ID donné
        Equipe equipe = new Equipe();
        equipe.setIdEquipe(1);

        // Configurez le comportement du repository mock
        Mockito.when(equipeRepository.findById(1)).thenReturn(Optional.of(equipe));

        // Appelez la méthode à tester
        Optional<Equipe> result = equipeService.findById(1);

        // Vérifiez si l'objet Equipe retourné par le service a le bon ID
        assertEquals(1, result.get().getIdEquipe());
    }*/
}
