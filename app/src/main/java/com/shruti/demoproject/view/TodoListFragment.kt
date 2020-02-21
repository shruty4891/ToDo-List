package com.shruti.demoproject.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chethan.demoproject.R
import com.chethan.demoproject.databinding.FragmentTodoListBinding
import com.shruti.demoproject.viewmodel.TodoListViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class TodoListFragment : Fragment() {
    lateinit var binding: FragmentTodoListBinding
    private val todoListModel: TodoListViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_todo_list, container, false)
        binding.viewModel=todoListModel
        binding.lifecycleOwner=this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        todoListModel.getTodoList()

        binding.recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        binding.tvReload.setOnClickListener {
            binding.shimmerViewContainer.startShimmerAnimation()
            todoListModel.getTodoList()
        }
    }

    override fun onStart() {
        super.onStart()
        todoListModel.listOfTodo.observe(this, Observer {
            it?.let {
                val todoListAdapter = TodoListAdapter(it)
                binding.recyclerView.adapter = todoListAdapter
            }
        })

        todoListModel.isShimmerGone.observe(this, Observer {
            stopShimmer()
        })
    }

    override fun onResume() {
        super.onResume()
        binding.shimmerViewContainer.startShimmerAnimation()
     }

    override fun onPause() {
        super.onPause()
        binding.shimmerViewContainer.stopShimmerAnimation()
    }

    private fun stopShimmer() {
        binding.shimmerViewContainer.stopShimmerAnimation();
        binding.shimmerViewContainer.visibility = View.GONE;
    }
}
