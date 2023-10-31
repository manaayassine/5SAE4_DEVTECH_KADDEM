package tn.esprit.spring.kaddem.Service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;
import tn.esprit.spring.kaddem.services.EtudiantServiceImpl;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TestEtudiantService {
    @InjectMocks
    private EtudiantServiceImpl etudiantService;
    @Mock
    EtudiantRepository etudiantRepository ;
    @Mock
    ContratRepository contratRepository;
    @Mock
    EquipeRepository equipeRepository;
    @Mock
    DepartementRepository departementRepository;

    @Test
    void testRetrieveAllEtudiants(){
        // Créez des équipes de test
        Etudiant etudiant1 = new Etudiant();
        Etudiant etudiant2 = new Etudiant();
        List<Etudiant> etudiants = Arrays.asList(etudiant1, etudiant2);

        // Configurez le comportement du repository mock
        Mockito.when(etudiantRepository.findAll()).thenReturn(etudiants);

        // Appelez la méthode à tester
        List<Etudiant> etudiantssRetournees = etudiantService.retrieveAllEtudiants();

        // Vérifiez si la liste retournée contient les mêmes équipes que celles de test
        assertEquals(etudiants, etudiantssRetournees);

        // Vérifiez si la méthode findAll du repository a été appelée une fois
        Mockito.verify(etudiantRepository, Mockito.times(1)).findAll();
    }

    @Test
    void testAddEtudiant (){
        // Créez une équipe de test
        Etudiant etudiant  = new Etudiant();
        etudiant.setIdEtudiant(1); // L'id de l'équipe après l'ajout

        // Configurez le comportement du repository mock
        Mockito.when(etudiantRepository.save(Mockito.any(Etudiant.class))).thenReturn(etudiant);

        // Appelez la méthode à tester
        Etudiant etudiantAjoutee = etudiantService.addEtudiant(new Etudiant());

        // Vérifiez si l'équipe retournée est la même que celle ajoutée
        assertEquals(etudiant.getIdEtudiant(), etudiantAjoutee.getIdEtudiant());

        // Vérifiez si la méthode save a été appelée avec la bonne équipe
        Mockito.verify(etudiantRepository, Mockito.times(1)).save(Mockito.any(Etudiant.class));
    }

    @Test
    void  testUpdateEtudiant (){
        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(1);

        // Configurez le comportement du repository mock
        Mockito.when(etudiantRepository.save(etudiant)).thenReturn(etudiant);

        // Appelez la méthode à tester
        Etudiant etudiantMiseAJour = etudiantService.updateEtudiant(etudiant);

        // Vérifiez si l'équipe retournée est la même que celle que nous avons mise à jour
        assertEquals(etudiant, etudiantMiseAJour);

        // Vérifiez si la méthode save du repository a été appelée une fois avec la bonne équipe
        Mockito.verify(etudiantRepository, Mockito.times(1)).save(etudiant);    }

    @Test
     void testRemoveEtudiant(){
        // Créez une équipe de test avec un ID
        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(1);

        // Configurez le comportement du repository mock
        Mockito.when(etudiantRepository.findById(1)).thenReturn(Optional.of(etudiant));

        // Appelez la méthode à tester
        etudiantService.removeEtudiant(1);

        // Vérifiez si la méthode delete du repository a été appelée avec la bonne équipe
        Mockito.verify(etudiantRepository, Mockito.times(1)).delete(etudiant);
    }



}
