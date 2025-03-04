package org.manavas.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.manavas.model.Recipe;

@ApplicationScoped
public class RecipeRepository implements PanacheRepository<Recipe> {}

