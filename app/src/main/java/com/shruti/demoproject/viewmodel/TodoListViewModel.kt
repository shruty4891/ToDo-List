package com.shruti.demoproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shruti.demoproject.model.TodoModel
import com.shruti.demoproject.repository.DataRepositoryImpl
import com.shruti.demoproject.repository.IDataRepository
import com.shruti.demoproject.repository.UseCaseResult
import kotlinx.coroutines.*
import retrofit2.Retrofit
import kotlin.coroutines.CoroutineContext

class TodoListViewModel(private val dataRepository: IDataRepository) : ViewModel(), CoroutineScope {
    private val job = Job()
    val listOfTodo = MutableLiveData<List<TodoModel>>()
    val isShimmerGone = MutableLiveData<Boolean>()
    val isInternetGone = MutableLiveData<Boolean>()

    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    fun getTodoList() {
        CoroutineScope(Dispatchers.Main).launch {
            val result = withContext(Dispatchers.IO) { dataRepository.getTodoList() }
            isShimmerGone.value = true
            when (result) {
                is UseCaseResult.Success -> {
                    listOfTodo.value = result.data
                    isInternetGone.value = false
                }
                is UseCaseResult.Error ->
                    isInternetGone.value = true
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}