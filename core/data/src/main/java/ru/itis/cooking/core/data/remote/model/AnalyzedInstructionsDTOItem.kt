package ru.itis.cooking.core.data.remote.model

data class AnalyzedInstructionsDTOItem(
    val name: String,
    val steps: List<Step>
)
