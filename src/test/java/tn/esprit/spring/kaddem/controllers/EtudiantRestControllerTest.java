package tn.esprit.spring.kaddem.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Option;
import tn.esprit.spring.kaddem.services.EtudiantServiceImpl;
import tn.esprit.spring.kaddem.services.IEtudiantService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {EtudiantRestController.class})
@RunWith(SpringRunner.class)
public class EtudiantRestControllerTest {
    @InjectMocks
    private EtudiantRestController etudiantRestController;

    @Mock
    private IEtudiantService etudiantService;


    @Test
    public void testGetEtudiants() {
        // Given
        List<Etudiant> etudiants = new ArrayList<>();
        etudiants.add(new Etudiant(1,"Youssef","Hamouda", Option.NIDS));
        etudiants.add(new Etudiant(2, "Ahmed","Belkahia",Option.SIM));
        when(etudiantService.retrieveAllEtudiants()).thenReturn(etudiants);

        // When
        List<Etudiant> result = etudiantRestController.getEtudiants();

        // Then
        assertEquals(2, result.size());
        // Add more assertions based on your implementation
    }



    @Test
    public void testAddEtudiant() {
        // Given
        Etudiant newEtudiant = new Etudiant(3,"Yassine","Manaa", Option.GAMIX);
        when(etudiantService.addEtudiant(any(Etudiant.class))).thenReturn(newEtudiant);

        // When
        Etudiant addedEtudiant = etudiantRestController.addEtudiant(newEtudiant);

        // Then
        assertEquals(Option.GAMIX, addedEtudiant.getOp());
        // Add more assertions based on your implementation
    }

    @Test
    public void testRemoveEtudiant() {
        // Given - No return type for delete
        doNothing().when(etudiantService).removeEtudiant(1);

        // When
        etudiantRestController.removeEtudiant(1);

        // Then
        verify(etudiantService, times(1)).removeEtudiant(1);
    }

    @Test
    public void testUpdateEtudiant() {
        // Given
        Etudiant updatedEtudiant = new Etudiant(2, "Ahmed","Tijani",Option.SIM);
        when(etudiantService.updateEtudiant(any(Etudiant.class))).thenReturn(updatedEtudiant);

        // When
        Etudiant result = etudiantRestController.updateEtudiant(updatedEtudiant);

        // Then
        assertEquals(2, result.getIdEtudiant());
        // Add more assertions based on your implementation
    }
}
