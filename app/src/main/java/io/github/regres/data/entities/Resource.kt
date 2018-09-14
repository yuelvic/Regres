package io.github.regres.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Resource(
        @PrimaryKey
        var id: Int = 0,
        var name: String = "",
        var color: String = ""
)