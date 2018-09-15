package io.github.regres.di.modules

import androidx.room.Room
import dagger.Module
import dagger.Provides
import io.github.regres.App
import io.github.regres.data.AppDatabase
import io.github.regres.data.local.ResourceDao
import io.github.regres.data.local.UserDao
import javax.inject.Singleton

@Module(includes = [AppModule::class])
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(app: App): AppDatabase {
        return Room.databaseBuilder(app, AppDatabase::class.java,
                "reqres-db").fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideResourceDao(appDatabase: AppDatabase): ResourceDao {
        return appDatabase.resourceDao()
    }

    @Provides
    @Singleton
    fun provideUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }

}