package io.github.regres.data.entities

data class Result<T>(
        var page: Int = 0,
        var data: List<T>?
)