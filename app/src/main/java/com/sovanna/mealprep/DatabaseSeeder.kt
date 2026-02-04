package com.sovanna.mealprep

class DatabaseSeeder {
    private val recipeRepository = RecipeRepository()

    fun seedRecipes(onComplete: () -> Unit) {
        val sampleRecipes = listOf(
            Recipe(
                recipeName = "Spaghetti Carbonara",
                cuisineType = "Italian",
                cookingTime = 25,
                difficultyLevel = "Medium",
                calories = 450,
                description = "Classic Italian pasta with creamy egg sauce and bacon",
                imageUrl = "https://images.unsplash.com/photo-1612874742237-6526221588e3?w=400",
                ingredients = listOf(
                    "400g spaghetti",
                    "200g bacon",
                    "3 eggs",
                    "100g parmesan cheese",
                    "Salt and pepper"
                ),
                steps = listOf(
                    "Boil pasta according to package instructions",
                    "Fry bacon until crispy",
                    "Mix eggs and parmesan in a bowl",
                    "Drain pasta and mix with egg mixture",
                    "Add bacon and serve immediately"
                )
            ),
            Recipe(
                recipeName = "Chicken Stir Fry",
                cuisineType = "Asian",
                cookingTime = 20,
                difficultyLevel = "Easy",
                calories = 380,
                description = "Quick and healthy chicken with vegetables",
                imageUrl = "https://images.unsplash.com/photo-1603133872878-684f208fb84b?w=400",
                ingredients = listOf(
                    "300g chicken breast",
                    "2 bell peppers",
                    "1 onion",
                    "3 tbsp soy sauce",
                    "2 tbsp oil",
                    "1 tsp ginger"
                ),
                steps = listOf(
                    "Cut chicken into bite-sized pieces",
                    "Slice vegetables",
                    "Heat oil in wok",
                    "Cook chicken until golden",
                    "Add vegetables and soy sauce",
                    "Stir fry for 5 minutes"
                )
            ),
            Recipe(
                recipeName = "Greek Salad",
                cuisineType = "Mediterranean",
                cookingTime = 10,
                difficultyLevel = "Easy",
                calories = 220,
                description = "Fresh and healthy Mediterranean salad",
                imageUrl = "https://images.unsplash.com/photo-1540189549336-e6e99c3679fe?w=400",
                ingredients = listOf(
                    "2 tomatoes",
                    "1 cucumber",
                    "1 red onion",
                    "200g feta cheese",
                    "Olives",
                    "Olive oil",
                    "Oregano"
                ),
                steps = listOf(
                    "Chop all vegetables",
                    "Cube feta cheese",
                    "Mix everything in a bowl",
                    "Drizzle with olive oil",
                    "Sprinkle oregano"
                )
            ),
            Recipe(
                recipeName = "Beef Tacos",
                cuisineType = "Mexican",
                cookingTime = 15,
                difficultyLevel = "Easy",
                calories = 350,
                description = "Quick and tasty Mexican tacos",
                imageUrl = "https://images.unsplash.com/photo-1565299585323-38d6b0865b47?w=400",
                ingredients = listOf(
                    "300g ground beef",
                    "Taco shells",
                    "Lettuce",
                    "Tomatoes",
                    "Cheese",
                    "Taco seasoning",
                    "Sour cream"
                ),
                steps = listOf(
                    "Brown ground beef in pan",
                    "Add taco seasoning",
                    "Warm taco shells",
                    "Chop lettuce and tomatoes",
                    "Assemble tacos with toppings"
                )
            ),
            Recipe(
                recipeName = "Vegetable Curry",
                cuisineType = "Indian",
                cookingTime = 30,
                difficultyLevel = "Medium",
                calories = 280,
                description = "Flavorful vegetarian curry with rice",
                imageUrl = "https://images.unsplash.com/photo-1585937421612-70a008356fbe?w=400",
                ingredients = listOf(
                    "2 potatoes",
                    "1 cauliflower",
                    "2 carrots",
                    "400ml coconut milk",
                    "2 tbsp curry powder",
                    "1 onion",
                    "Rice"
                ),
                steps = listOf(
                    "Chop all vegetables",
                    "Sauté onion until soft",
                    "Add curry powder",
                    "Add vegetables and coconut milk",
                    "Simmer for 20 minutes",
                    "Serve with rice"
                )
            )
        )

        var count = 0
        sampleRecipes.forEach { recipe ->
            recipeRepository.addRecipe(
                recipe = recipe,
                onSuccess = {
                    count++
                    if (count == sampleRecipes.size) {
                        onComplete()
                    }
                },
                onFailure = { error ->
                    println("Failed to seed recipe: $error")
                }
            )
        }

    }
}