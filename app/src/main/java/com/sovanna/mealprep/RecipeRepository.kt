package com.sovanna.mealprep


import com.google.firebase.database.*

class RecipeRepository {
    private val database = FirebaseDatabase.getInstance().reference
    private val recipesRef = database.child("recipes")

    fun getAllRecipes(
        onSuccess: (List<Recipe>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        recipesRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val recipes = mutableListOf<Recipe>()
                for (recipeSnapshot in snapshot.children) {
                    recipeSnapshot.getValue(Recipe::class.java)?.let {
                        recipes.add(it)
                    }
                }
                onSuccess(recipes)
            }

            override fun onCancelled(error: DatabaseError) {
                onFailure(error.message)
            }
        })
    }

    fun getRecipeById(
        recipeId: String,
        onSuccess: (Recipe?) -> Unit,
        onFailure: (String) -> Unit
    ) {
        recipesRef.child(recipeId).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val recipe = snapshot.getValue(Recipe::class.java)
                onSuccess(recipe)
            }

            override fun onCancelled(error: DatabaseError) {
                onFailure(error.message)
            }
        })
    }

    fun addRecipe(
        recipe: Recipe,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        val recipeId = recipesRef.push().key ?: return
        val newRecipe = recipe.copy(recipeId = recipeId)

        recipesRef.child(recipeId).setValue(newRecipe)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailure(it.message ?: "Failed to add recipe") }
    }

    fun filterRecipes(
        cuisineType: String? = null,
        maxCookingTime: Int? = null,
        onSuccess: (List<Recipe>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        getAllRecipes(
            onSuccess = { recipes ->
                var filtered = recipes

                if (!cuisineType.isNullOrEmpty() && cuisineType != "All") {
                    filtered = filtered.filter { it.cuisineType == cuisineType }
                }

                if (maxCookingTime != null) {
                    filtered = filtered.filter { it.cookingTime <= maxCookingTime }
                }

                onSuccess(filtered)
            },
            onFailure = onFailure
        )
    }
}