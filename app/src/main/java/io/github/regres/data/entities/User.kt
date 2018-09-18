package io.github.regres.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class User(
        @PrimaryKey
        var id: Int = 0,
        @SerializedName("first_name")
        @ColumnInfo(name = "first_name")
        var firstName: String = "",
        @SerializedName("last_name")
        @ColumnInfo(name = "last_name")
        var lastName: String = "",
        var avatar: String = "",

        var name: String = "",
        var job: String = ""
)