package ru.itis.cooking.core.domain.model

data class FoodFilters(
    val mIndex: Int = 0,
    val mType: String = "Main Course",
    val dIndex: Int = 0,
    val dType: String = "Gluten Free"
) {
    companion object {

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
}
