package com.shruti.demoproject.repository

import com.shruti.demoproject.model.TodoModel

interface IDataRepository {
    suspend fun getTodoList(): UseCaseResult<List<TodoModel>>
}