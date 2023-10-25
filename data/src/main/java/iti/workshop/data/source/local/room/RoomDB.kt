package iti.workshop.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import iti.workshop.data.source.dto.Article

@Database(
    entities = [Article::class],
    version = 2,
    exportSchema = false
)
abstract class RoomDB : RoomDatabase() {
    abstract fun articleDao(): ArticlesDao

    companion object {
        @Volatile
        private var instance: RoomDB? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                RoomDB::class.java,
                "room.db"
            )
                .fallbackToDestructiveMigration()
                .build()
    }
}