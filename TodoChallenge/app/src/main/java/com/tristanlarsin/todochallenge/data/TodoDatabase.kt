package com.tristanlarsin.todochallenge.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tristanlarsin.todochallenge.data.db.dao.TodoItemDao
import com.tristanlarsin.todochallenge.data.db.entity.TodoItemEntity

@Database(
    entities = [TodoItemEntity::class],
    exportSchema = false,
    version = 1
)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoItemDao(): TodoItemDao

    companion object {
        @Volatile private var instance: TodoDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                TodoDatabase::class.java, "todo.db")
                .build()
    }
}