package ru.itis.cooking.core.domain.usecase.base

interface BaseUseCase<in Parameter, out Result> {
    suspend operator fun invoke(parameter: Parameter): Result
}
