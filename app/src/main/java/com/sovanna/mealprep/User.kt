package com.sovanna.mealprep

data class User(
    val userId: String = "",
    val username: String = "",
    val email: String = "",
    val dietaryPreference: String = "None"
)