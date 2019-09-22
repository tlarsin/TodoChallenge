package com.tristanlarsin.todochallenge

import android.app.Application
import com.tristanlarsin.todochallenge.data.TodoDatabase
import com.tristanlarsin.todochallenge.data.repository.TodoRepository
import com.tristanlarsin.todochallenge.data.repository.TodoRepositoryImpl
import com.tristanlarsin.todochallenge.ui.TodoViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class TodoApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@TodoApplication))

        // Setup instances
        bind() from singleton { TodoDatabase(instance()) }
        bind() from singleton { instance<TodoDatabase>().todoItemDao() }

        bind<TodoRepository>() with singleton { TodoRepositoryImpl(instance()) }
        bind() from provider { TodoViewModelFactory(instance()) }
    }
}