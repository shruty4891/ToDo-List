package com.shruti.demoproject.network


import com.shruti.demoproject.model.TodoModel
import retrofit2.Response
import retrofit2.http.GET

interface NetWorkApi{
    @GET("/todos")
    suspend fun getTodoListData(): Response<List<TodoModel>>
}