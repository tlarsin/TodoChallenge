package com.tristanlarsin.todochallenge.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_items")
data class TodoItemEntity (
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    var title: String,
    var isChecked: Boolean
)