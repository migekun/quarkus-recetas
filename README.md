# recipes

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./gradlew quarkusDev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Packaging and running the application

The application can be packaged using:

```shell script
./gradlew build
```

It produces the `quarkus-run.jar` file in the `build/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `build/quarkus-app/lib/` directory.

The application is now runnable using `java -jar build/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./gradlew build -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar build/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./gradlew build -Dquarkus.native.enabled=true
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./gradlew build -Dquarkus.native.enabled=true -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./build/recipes-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/gradle-tooling>.

## Related Guides

- REST resources for Hibernate ORM with Panache ([guide](https://quarkus.io/guides/rest-data-panache)): Generate Jakarta REST resources for your Hibernate Panache entities and repositories
- REST ([guide](https://quarkus.io/guides/rest)): A Jakarta REST implementation utilizing build time processing and Vert.x. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on it.

## Provided Code

### REST Data with Panache

Generating Jakarta REST resources with Panache

[Related guide section...](https://quarkus.io/guides/rest-data-panache)


### REST

Easily start your REST Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)


Problem statement
RESTful web services are generally used for creating web applications due to their lightness and cacheability (ease of scaling), and for the fact that they're easy to maintain.

Implement and deploy a recipe API server which suits the REST Architectural Constraints.

Implementation
Overview
Implement the Endpoints shown below:
POST /recipes -> Create a recipe.
GET /recipes -> Return the list of all of the recipes.
GET /recipes/{id} -> Return the selected recipe.
PATCH /recipes/{id} -> Update the selected recipe.
DELETE /recipes/{id} -> Delete the selected recipe.
Return all responses in JSON format.
All HTTP status codes for the above endpoints should be 200.
The HTTP status codes for all endpoints other than the above should be 404.
For other details please make sure that the program suits the REST Architectural Constraints
The database schema is provided.
Create a database from sql/create.sql.
Use a hosting service (for example Heroku, AWS) and deploy the application on a server.
After deploying the application, save the link for your application in env.yml.
Run Test on the Track web editor and check if the application passes the test cases.
Basic Specification
POST /recipes Endpoint
Create a new recipe.
Expected request format: POST /recipes
Body field:
title, making_time, serves, ingredients, cost
All the parameters above are required.
The details of each property are written in sql/create.sql.
Expected response format:
Success response:
Copy
{
"message": "Recipe successfully created!",
"recipe": [
{
"id": "3",
"title": "Tomato Soup",
"making_time": "15 min",
"serves": "5 people",
"ingredients": "onion, tomato, seasoning, water",
"cost": "450",
"created_at": "2016-01-12 14:10:12",
"updated_at": "2016-01-12 14:10:12"
}
]
}
Failure response:
Copy
{
"message": "Recipe creation failed!",
"required": "title, making_time, serves, ingredients, cost"
}
GET /recipes Endpoint
Returns the list of all the recipes.

Expected request format: GET /recipes

Expected response format:

Copy
{
"recipes": [
{
"id": 1,
"title": "Chicken Curry",
"making_time": "45 min",
"serves": "4 people",
"ingredients": "onion, chicken, seasoning",
"cost": "1000"
},
{
"id": 2,
"title": "Rice Omelette",
"making_time": "30 min",
"serves": "2 people",
"ingredients": "onion, egg, seasoning, soy sauce",
"cost": "700"
},
{
"id": 3,
"title": "Tomato Soup",
"making_time": "15 min",
"serves": "5 people",
"ingredients": "onion, tomato, seasoning, water",
"cost": "450"
}
]
}
Each recipes are required to have the id if the response were to be used in the following tests shown bellow (GET, PATCH, and DELETE). The other properties can be optional.
GET /recipes/{id} Endpoint
Return the recipe with the selected id.

The response of GET /recipes will be used as the id for this test. When the id is not returned,
1
1 is used for the id.
Expected request format: GET /recipes/1

Expected response format:

Copy
{
"message": "Recipe details by id",
"recipe": [
{
"id": 1,
"title": "Chicken Curry",
"making_time": "45 min",
"serves": "4 people",
"ingredients": "onion, chicken, seasoning",
"cost": "1000"
}
]
}
id, title, making_time, serves, ingredients, and cost are required in recipe. The other properties are optional.
PATCH /recipes/{id} Endpoint
Update the selected recipe id and return the updated recipe.

Expected request format: PATCH /recipes/{id}

The response of GET /recipes will be used as the id for this test. When the id is not returned,
1
1 is used for the id.
Body field:
title, making_time, serves, ingredients, cost
Expected response format:

Copy
{
"message": "Recipe successfully updated!",
"recipe": [
{
"title": "Tomato Soup",
"making_time": "15 min",
"serves": "5 people",
"ingredients": "onion, tomato, seasoning, water",
"cost": "450"
}
]
}
DELETE /recipes/{id} Endpoint
Delete the recipe with selected id.
The response of GET /recipes will be used as the id for this test. When the id is not returned,
1
1 is used for the id.
Expected request format: DELETE /recipes/1
Expected response format:
Success:
Copy
{  "message": "Recipe successfully removed!" }
Failure (when the recipe with selected id is not found):
Copy
{ "message": "No recipe found" }
