package io.github.regres.data

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.regres.data.entities.Resource
import io.github.regres.data.entities.User
import io.github.regres.data.local.ResourceDao
import io.github.regres.data.local.UserDao

@Database(entities = [Resource::class, User::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun resourceDao(): ResourceDao
    abstract fun userDao(): UserDao
}