package org.manavas.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.manavas.model.Recipe;
import org.manavas.repository.RecipeRepository;
import org.manavas.service.RecipeService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    @Inject
    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> listAll() {
        return recipeRepository.listAll();
    }

    public Optional<Recipe> getById(Long id) {
        return recipeRepository.findByIdOptional(id);
    }

    @Transactional
    public Recipe create(Recipe recipe) {
        recipeRepository.persist(recipe);
        return recipe;
    }

    @Transactional
    public Recipe update(Long id, Recipe newRecipe) {
        return recipeRepository.findByIdOptional(id).map(recipe -> {
            recipe.title = newRecipe.title;
            recipe.makingTime = newRecipe.makingTime;
            recipe.serves = newRecipe.serves;
            recipe.ingredients = newRecipe.ingredients;
            recipe.cost = newRecipe.cost;
            recipe.updatedAt = LocalDateTime.now();
            return recipe;
        }).orElse(null);
    }

    @Transactional
    public Boolean delete(Long id) {
        return recipeRepository.findByIdOptional(id).map(recipe -> {
            recipeRepository.delete(recipe);
            return true;
        }).orElse(false);
    }
}
