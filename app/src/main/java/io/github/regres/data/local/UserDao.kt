package io.github.regres.data.local

import androidx.room.Dao
import io.github.regres.data.entities.User

@Dao
interface UserDao: BaseDao<User> {



}