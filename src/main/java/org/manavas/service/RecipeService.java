package org.manavas.service;

import org.manavas.model.Recipe;

import java.util.List;
import java.util.Optional;

public interface RecipeService {
    List<Recipe> listAll();

    Optional<Recipe> getById(Long id);

    Recipe create(Recipe recipe);

    Recipe update(Long id, Recipe newRecipe);

    Boolean delete(Long id);
}
