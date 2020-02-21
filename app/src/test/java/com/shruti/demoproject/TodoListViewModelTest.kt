package com.shruti.demoproject

import com.shruti.demoproject.network.NetWorkApi
import com.shruti.demoproject.repository.DataRepositoryImpl
import com.shruti.demoproject.repository.IDataRepository
import com.shruti.demoproject.viewmodel.TodoListViewModel
import org.junit.Before

class TodoListViewModelTest {

    private lateinit var dataRepository: IDataRepository
    private lateinit var networkApi: NetWorkApi
    lateinit var todoListViewModel: TodoListViewModel

    @Before
    fun setUp() {
//        MockitoAnnotations.initMocks(this)
        this.dataRepository = DataRepositoryImpl(networkApi)
        this.todoListViewModel = TodoListViewModel(dataRepository)
    }
}