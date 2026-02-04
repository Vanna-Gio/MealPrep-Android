package com.sovanna.mealprep


data class Recipe(
    val recipeId: String = "",
    val recipeName: String = "",
    val cuisineType: String = "",
    val cookingTime: Int = 0, // in minutes
    val difficultyLevel: String = "", // Easy, Medium, Hard
    val calories: Int = 0,
    val description: String = "",
    val imageUrl: String = "",
    val ingredients: List<String> = emptyList(),
    val steps: List<String> = emptyList()
)