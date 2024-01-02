package ru.itis.cooking.core.data.util

object Constants  {
    const val API_KEY = "ddb2169c7f044f49a4b3d3714d627ecb"
    const val BASE_URL = "https://api.spoonacular.com/"
    const val BASE_IMAGE_URL = "https://spoonacular.com/cdn/ingredients_100x100"

    val dietTypes = listOf(
        "Gluten Free",
        "Ketogenic",
        "Vegetarian",
        "Lacto-Vegetarian",
        "Vegan",
        "Pescetarian",
        "Paleo",
        "Primal"
    )
    val mealTypes = listOf(
        "Main course",
        "Side dish",
        "Dessert",
        "Appetizer",
        "Salad",
        "Breakfast",
        "Soup",
        "Beverage",
        "Sauce",
        "Marinade",
        "Fingerfood",
        "Snack",
        "Drink"
    )
}
