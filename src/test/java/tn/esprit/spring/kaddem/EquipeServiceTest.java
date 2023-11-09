package tn.esprit.spring.kaddem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;
import tn.esprit.spring.kaddem.services.EquipeServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EquipeServiceTest {
    @Mock
    private EquipeRepository equipeRepository;

    @InjectMocks
    private EquipeServiceImpl equipeService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void testAddEquipe() {
        // Arrange
        // Créez une équipe de test avec un ID spécifique
        Equipe equipeAjoutee = new Equipe();
        equipeAjoutee.setIdEquipe(1);

        // Configurez le comportement du repository mock
        Mockito.when(equipeRepository.save(Mockito.any(Equipe.class))).thenReturn(equipeAjoutee);

        // Act
        // Appelez la méthode à tester en ajoutant une nouvelle équipe
        Equipe equipeRetournee = equipeService.addEquipe(new Equipe());

        // Assert
        // Vérifiez si l'équipe retournée est la même que celle ajoutée
        assertEquals(equipeAjoutee.getIdEquipe(), equipeRetournee.getIdEquipe());

        // Vérifiez si la méthode save a été appelée avec la bonne équipe
        Mockito.verify(equipeRepository, Mockito.times(1)).save(Mockito.any(Equipe.class));
    }

    @Test
    void testRetrieveAllEquipes() {
        // Créez des équipes de test
        Equipe equipe1 = new Equipe();
        Equipe equipe2 = new Equipe();
        List<Equipe> equipes = Arrays.asList(equipe1, equipe2);

        // Configurez le comportement du repository mock
        Mockito.when(equipeRepository.findAll()).thenReturn(equipes);

        // Appelez la méthode à tester
        List<Equipe> equipesRetournees = equipeService.retrieveAllEquipes();

        // Vérifiez si la liste retournée contient les mêmes équipes que celles de test
        assertEquals(equipes, equipesRetournees);

        // Vérifiez si la méthode findAll du repository a été appelée une fois
        Mockito.verify(equipeRepository, Mockito.times(1)).findAll();
    }
    @Test
    void testDeleteEquipe() {
        // Créez une équipe de test avec un ID
        Equipe equipe = new Equipe();
        equipe.setIdEquipe(1);

        // Configurez le comportement du repository mock
        Mockito.when(equipeRepository.findById(1)).thenReturn(Optional.of(equipe));

        // Appelez la méthode à tester
        equipeService.deleteEquipe(1);

        // Vérifiez si la méthode delete du repository a été appelée avec la bonne équipe
        Mockito.verify(equipeRepository, Mockito.times(1)).delete(equipe);
    }
    @Test
    void testUpdateEquipe() {
        // Créez une équipe de test
        Equipe equipe = new Equipe();
        equipe.setIdEquipe(1);

        // Configurez le comportement du repository mock
        Mockito.when(equipeRepository.save(equipe)).thenReturn(equipe);

        // Appelez la méthode à tester
        Equipe equipeMiseAJour = equipeService.updateEquipe(equipe);

        // Vérifiez si l'équipe retournée est la même que celle que nous avons mise à jour
        assertEquals(equipe, equipeMiseAJour);

        // Vérifiez si la méthode save du repository a été appelée une fois avec la bonne équipe
        Mockito.verify(equipeRepository, Mockito.times(1)).save(equipe);
    }



  /*  @Test
    void testEvoluerEquipes() {
        // Créez une équipe de test
        Equipe equipe = new Equipe();
        equipe.setIdEquipe(1);
        equipe.setNiveau(Niveau.JUNIOR);

        // Créez des contrats de test
        Contrat contrat1 = new Contrat();
        contrat1.setArchive(false);
        contrat1.setDateFinContrat(new Date(System.currentTimeMillis() - (2 * 365 * 24 * 60 * 60 * 1000))); // Contrat actif depuis 2 ans

        Contrat contrat2 = new Contrat();
        contrat2.setArchive(false);
        contrat2.setDateFinContrat(new Date(System.currentTimeMillis() - (3 * 365 * 24 * 60 * 60 * 1000))); // Contrat actif depuis 3 ans

        // Créez un étudiant de test avec les contrats
        Etudiant etudiant = new Etudiant();
        etudiant.setContrats(new HashSet<>(Arrays.asList(contrat1, contrat2)));

        // Ajoutez l'étudiant à l'équipe (utilisez un HashSet)
        Set<Etudiant> etudiants = new HashSet<>();
        etudiants.add(etudiant);
        equipe.setEtudiants(etudiants);

        // Configurez le comportement du repository mock
        Mockito.when(equipeRepository.findAll()).thenReturn(Arrays.asList(equipe));

        // Appelez la méthode à tester
        equipeService.evoluerEquipes();

        // Vérifiez si l'équipe a été mise à jour correctement
        assertEquals(Niveau.SENIOR, equipe.getNiveau());
        Mockito.verify(equipeRepository, Mockito.times(1)).save(equipe);
    }*/

}
