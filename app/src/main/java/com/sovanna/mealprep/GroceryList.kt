package com.sovanna.mealprep


data class GroceryList(
    val listId: String = "",
    val planId: String = "",
    val userId: String = "",
    val items: Map<String, GroceryItem> = emptyMap(), // Key: ingredient name
    val createdDate: String = ""
)

data class GroceryItem(
    val ingredientName: String = "",
    val quantity: String = "", // e.g., "2 cups", "500g"
    val isPurchased: Boolean = false
)