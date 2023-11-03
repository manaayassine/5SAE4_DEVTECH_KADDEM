package services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.services.DepartementServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@ContextConfiguration(classes = {DepartementServiceImpl.class})
@RunWith(SpringRunner.class)

public class DepartementServiceTest {

    @InjectMocks
    private DepartementServiceImpl departementService;

    @Mock
    private DepartementRepository departementRepository;



    @Test
    public void testRetrieveAllDepartements() {
        // Given
        List<Departement> departements = new ArrayList<>();
        departements.add(new Departement(1, "IT"));
        departements.add(new Departement(2, "HR"));
        when(departementRepository.findAll()).thenReturn(departements);

        // When
        List<Departement> retrievedDepartements = departementService.retrieveAllDepartements();

        // Then
        assertEquals(2, retrievedDepartements.size());
        // You can add more assertions based on your implementation
    }

    @Test
    public void testAddDepartement() {
        // Given
        Departement newDepartement = new Departement(3, "Finance");
        when(departementRepository.save(any(Departement.class))).thenReturn(newDepartement);

        // When
        Departement addedDepartement = departementService.addDepartement(newDepartement);

        // Then
        assertEquals("Finance", addedDepartement.getNomDepart());
        // You can add more assertions based on your implementation
    }

    @Test
    public void testUpdateDepartement() {
        // Given
        Departement existingDepartement = new Departement(4, "Marketing");
        when(departementRepository.save(any(Departement.class))).thenReturn(existingDepartement);

        // When
        Departement updatedDepartement = departementService.updateDepartement(existingDepartement);

        // Then
        assertEquals("Marketing", updatedDepartement.getNomDepart());
        // You can add more assertions based on your implementation
    }

    @Test
    public void testRetrieveDepartement() {
        // Given
        Departement departement = new Departement(5, "Sales");
        when(departementRepository.findById(5)).thenReturn(Optional.of(departement));

        // When
        Departement retrievedDepartement = departementService.retrieveDepartement(5);

        // Then
        assertEquals("Sales", retrievedDepartement.getNomDepart());
        // You can add more assertions based on your implementation
    }

    @Test
    public void testDeleteDepartement() {
        // Given
        Departement departement = new Departement(6, "Logistics");
        when(departementRepository.findById(6)).thenReturn(Optional.of(departement));

        // When
        departementService.deleteDepartement(6);

        // Then
        verify(departementRepository, times(1)).delete(departement);
    }
}