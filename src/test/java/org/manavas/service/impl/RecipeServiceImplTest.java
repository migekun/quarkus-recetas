package org.manavas.service.impl;

import io.quarkus.test.InjectMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.manavas.model.Recipe;
import org.manavas.repository.RecipeRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RecipeServiceImplTest {

    /**
     * Test class for the update method in RecipeServiceImpl.
     * The update method allows updating an existing Recipe entity based on the provided ID and new Recipe data.
     */
    @Mock
    private RecipeRepository recipeRepository; // Dependencia del servicio

    @InjectMocks
    private RecipeServiceImpl recipeService; // El servicio bajo prueba

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa los mocks y los inyecta
    }


    @Test
    void testUpdate_RecipeExists() {
        // Arrange
        RecipeServiceImpl service = new RecipeServiceImpl(recipeRepository);

        Long recipeId = 1L;
        Recipe existingRecipe = new Recipe();
        existingRecipe.title = "Old Recipe";
        existingRecipe.makingTime = "45 minutes";
        existingRecipe.serves = "4 people";
        existingRecipe.ingredients = "Ingredients A";
        existingRecipe.cost = 500;
        existingRecipe.updatedAt = LocalDateTime.of(2023, 1, 1, 0, 0);

        Recipe newRecipe = new Recipe();
        newRecipe.title = "New Recipe";
        newRecipe.makingTime = "30 minutes";
        newRecipe.serves = "2 people";
        newRecipe.ingredients = "Ingredients B";
        newRecipe.cost = 300;

        when(recipeRepository.findByIdOptional(recipeId)).thenReturn(Optional.of(existingRecipe));

        // Act
        Recipe updatedRecipe = service.update(recipeId, newRecipe);

        // Assert
        assertNotNull(updatedRecipe);
        assertEquals("New Recipe", updatedRecipe.title);
        assertEquals("30 minutes", updatedRecipe.makingTime);
        assertEquals("2 people", updatedRecipe.serves);
        assertEquals("Ingredients B", updatedRecipe.ingredients);
        assertEquals(300, updatedRecipe.cost);
        assertNotEquals(LocalDateTime.of(2023, 1, 1, 0, 0), updatedRecipe.updatedAt);

        verify(recipeRepository, times(1)).findByIdOptional(recipeId);
    }

    @Test
    void testUpdate_RecipeDoesNotExist() {
        // Arrange
        RecipeServiceImpl service = new RecipeServiceImpl(recipeRepository);

        Long recipeId = 1L;
        Recipe newRecipe = new Recipe();
        newRecipe.title = "New Recipe";
        newRecipe.makingTime = "30 minutes";
        newRecipe.serves = "2 people";
        newRecipe.ingredients = "Ingredients B";
        newRecipe.cost = 300;

        when(recipeRepository.findByIdOptional(recipeId)).thenReturn(Optional.empty());

        // Act
        Recipe updatedRecipe = service.update(recipeId, newRecipe);

        // Assert
        assertNull(updatedRecipe);
        verify(recipeRepository, times(1)).findByIdOptional(recipeId);
    }
}