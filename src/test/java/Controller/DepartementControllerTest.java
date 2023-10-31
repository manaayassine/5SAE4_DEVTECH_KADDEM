package Controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tn.esprit.spring.kaddem.controllers.DepartementRestController;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.services.IDepartementService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DepartementControllerTest {

    @InjectMocks
    private DepartementRestController departementController;

    @Mock
    private IDepartementService departementService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetDepartements() {
        // Given
        List<Departement> departements = new ArrayList<>();
        departements.add(new Departement(1, "IT"));
        departements.add(new Departement(2, "HR"));
        when(departementService.retrieveAllDepartements()).thenReturn(departements);

        // When
        List<Departement> result = departementController.getDepartements();

        // Then
        assertEquals(2, result.size());
        // Add more assertions based on your implementation
    }

    @Test
    public void testRetrieveDepartement() {
        // Given
        Departement expectedDepartement = new Departement(1, "IT");
        when(departementService.retrieveDepartement(1)).thenReturn(expectedDepartement);

        // When
        Departement result = departementController.retrieveDepartement(1);

        // Then
        assertEquals(expectedDepartement.getNomDepart(), result.getNomDepart());
        // Add more assertions based on your implementation
    }

    @Test
    public void testAddDepartement() {
        // Given
        Departement newDepartement = new Departement(3, "Finance");
        when(departementService.addDepartement(any(Departement.class))).thenReturn(newDepartement);

        // When
        Departement addedDepartement = departementController.addDepartement(newDepartement);

        // Then
        assertEquals("Finance", addedDepartement.getNomDepart());
        // Add more assertions based on your implementation
    }

    @Test
    public void testRemoveDepartement() {
        // Given - No return type for delete
        doNothing().when(departementService).deleteDepartement(1);

        // When
        departementController.removeDepartement(1);

        // Then
        verify(departementService, times(1)).deleteDepartement(1);
    }

    @Test
    public void testUpdateDepartement() {
        // Given
        Departement updatedDepartement = new Departement(1, "Updated IT");
        when(departementService.updateDepartement(any(Departement.class))).thenReturn(updatedDepartement);

        // When
        Departement result = departementController.updateDepartement(updatedDepartement);

        // Then
        assertEquals("Updated IT", result.getNomDepart());
        // Add more assertions based on your implementation
    }
}