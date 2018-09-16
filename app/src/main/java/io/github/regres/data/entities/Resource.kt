package io.github.regres.data.entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class Resource(
        @PrimaryKey
        var id: Int = 0,
        var name: String = "",
        var color: String = "",
        @Ignore
        var users: List<User> = emptyList()
)