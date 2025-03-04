package org.manavas.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.manavas.model.Recipe;
import org.manavas.service.RecipeService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

@Path("/recipes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class RecipeController {

    private final RecipeService service;

    @Inject
    public RecipeController(RecipeService service) {
        this.service = service;
    }
    @GET
    public List<Recipe> getAll() {
        return service.listAll();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        return service.getById(id)
                .map(recipe -> Response.ok(recipe).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    public Response create(Recipe recipe) {
        if (Stream.of(recipe.title, recipe.makingTime, recipe.serves, recipe.ingredients, recipe.cost)
                .anyMatch(Objects::isNull)) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of(
                            "message", "Recipe creation failed!",
                            "required", "title, making_time, serves, ingredients, cost"
                    ))
                    .build();
        }


        recipe.createdAt = LocalDateTime.now();
        recipe.updatedAt = LocalDateTime.now();
        service.create(recipe);

        return Response.status(Response.Status.CREATED)
                .entity(Map.of(
                        "message", "Recipe successfully created!",
                        "recipe", recipe )
                ).build();
    }

    @PATCH
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, Recipe newRecipe) {
        return service.getById(id).map(recipe -> {
            recipe.title = newRecipe.title;
            recipe.makingTime = newRecipe.makingTime;
            recipe.serves = newRecipe.serves;
            recipe.ingredients = newRecipe.ingredients;
            recipe.cost = newRecipe.cost;
            recipe.updatedAt = LocalDateTime.now();
            return Response.ok(recipe).build();
        }).orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        return service.delete(id) ?
                Response.noContent().build():
        Response.status(Response.Status.NOT_FOUND).build();
    }
}

