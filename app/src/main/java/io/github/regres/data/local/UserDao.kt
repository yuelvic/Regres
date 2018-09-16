package io.github.regres.data.local

import androidx.room.Dao
import androidx.room.Query
import io.github.regres.data.entities.User

@Dao
interface UserDao: BaseDao<User> {

    @Query("SELECT * FROM user")
    fun getResources(): List<User>

}