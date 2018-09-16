package io.github.regres.data.local

import androidx.room.Dao
import androidx.room.Query
import io.github.regres.data.entities.Resource

@Dao
interface ResourceDao: BaseDao<Resource> {

    @Query("SELECT * FROM resource")
    fun getResources(): List<Resource>

}