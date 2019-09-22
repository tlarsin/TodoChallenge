package com.tristanlarsin.todochallenge.ui

import androidx.lifecycle.ViewModel
import com.tristanlarsin.todochallenge.data.db.entity.TodoItemEntity
import com.tristanlarsin.todochallenge.data.repository.TodoRepository
import com.tristanlarsin.todochallenge.internal.lazyDeferred

class TodoViewModel(
    private val todoRepository: TodoRepository
) : ViewModel() {

    val todoList by lazyDeferred {
        todoRepository.getTodoList()
    }

    fun upsertTodoItem(item: TodoItemEntity) {
        todoRepository.upsertTodoItem(item)
    }

    fun deleteTodoItem(item: TodoItemEntity) {
        todoRepository.deleteTodoItem(item)
    }
}