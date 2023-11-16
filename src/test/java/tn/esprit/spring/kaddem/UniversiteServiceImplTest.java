package tn.esprit.spring.kaddem;

import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.ArgumentMatchers.eq;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.UniversiteRepository;
import tn.esprit.spring.kaddem.services.UniversiteServiceImpl;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UniversiteServiceImplTest {
    @Mock
    private UniversiteRepository universiteRepository;

    @Mock
    private DepartementRepository departementRepository;

    @InjectMocks
    private UniversiteServiceImpl universiteService;



    @Test
    public void testRetrieveAllUniversites() {
        // Créer des données de test
        Universite universite1 = new Universite();
        Universite universite2 = new Universite();
        List<Universite> universites = Arrays.asList(universite1, universite2);

        // Définir le comportement du mock
        when(universiteRepository.findAll()).thenReturn(universites);

        // Appeler la méthode à tester
        List<Universite> result = universiteService.retrieveAllUniversites();

        // Vérifier le résultat
        assertEquals(2, result.size());
    }

    @Test
    public void testAddUniversite() {
        // Créer des données de test
        Universite universite = new Universite();

        // Définir le comportement du mock
        when(universiteRepository.save(universite)).thenReturn(universite);

        // Appeler la méthode à tester
        Universite result = universiteService.addUniversite(universite);

        // Vérifier le résultat
        assertNotNull(result);
    }

    @Test
    public void testUpdateUniversite() {
        // Créer des données de test
        Universite universite = new Universite();

        // Définir le comportement du mock
        when(universiteRepository.save(universite)).thenReturn(universite);

        // Appeler la méthode à tester
        Universite result = universiteService.updateUniversite(universite);

        // Vérifier le résultat
        assertNotNull(result);
    }

    @Test
    public void testRetrieveUniversite() {
        // Créer des données de test
        Integer idUniversite = 1;
        Universite universite = new Universite();
        Optional<Universite> optionalUniversite = Optional.of(universite);

        // Définir le comportement du mock
        when(universiteRepository.findById(idUniversite)).thenReturn(optionalUniversite);

        // Appeler la méthode à tester
        Universite result = universiteService.retrieveUniversite(idUniversite);

        // Vérifier le résultat
        assertNotNull(result);
    }

    @Test
    public void testDeleteUniversite() {
        // Créer des données de test
        Integer idUniversite = 1;
        Universite universite = new Universite();
        when(universiteRepository.findById(idUniversite)).thenReturn(Optional.of(universite));

        // Appeler la méthode à tester
        universiteService.deleteUniversite(idUniversite);

        // Vérifier que la méthode delete du repository a été appelée
        verify(universiteRepository, times(1)).delete(universite);
    }




}
