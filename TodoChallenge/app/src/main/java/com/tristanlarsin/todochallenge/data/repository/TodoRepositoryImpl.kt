package com.tristanlarsin.todochallenge.data.repository

import androidx.lifecycle.LiveData
import com.tristanlarsin.todochallenge.data.db.dao.TodoItemDao
import com.tristanlarsin.todochallenge.data.db.entity.TodoItemEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TodoRepositoryImpl(
    private val todoListDao: TodoItemDao
) : TodoRepository {
    private val scope = CoroutineScope(Dispatchers.Default)

    override fun upsertTodoItem(item: TodoItemEntity) = scope.launch {
        todoListDao.upsert(item)
    }

    override fun deleteTodoItem(item: TodoItemEntity) = scope.launch {
        todoListDao.delete(item)
    }

    override suspend fun getTodoList(): LiveData<out List<TodoItemEntity>> {
        return withContext(Dispatchers.IO) { todoListDao.getTodoItems() }
    }
}