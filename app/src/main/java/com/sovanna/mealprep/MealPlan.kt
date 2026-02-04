package com.sovanna.mealprep


data class MealPlan(
    val planId: String = "",
    val userId: String = "",
    val weekStartDate: String = "", // Format: "2024-01-15"
    val meals: Map<String, DayMeals> = emptyMap() // Key: "Monday", "Tuesday", etc.
)

data class DayMeals(
    val breakfast: String = "", // recipeId
    val lunch: String = "",
    val dinner: String = ""
)