package com.shruti.demoproject.repository

import com.shruti.demoproject.network.NetWorkApi
import com.shruti.demoproject.model.TodoModel

class DataRepositoryImpl(private val networkApi: NetWorkApi) :
    IDataRepository {
    override suspend fun getTodoList(): UseCaseResult<List<TodoModel>> {
        return try {
            val result = networkApi.getTodoListData()
            if (result.isSuccessful)
                UseCaseResult.Success(result.body() as List<TodoModel>)
            else UseCaseResult.Error(RuntimeException("test error"))
        } catch (ex: Exception) {
            UseCaseResult.Error(ex)
        }
    }
}

