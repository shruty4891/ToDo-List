package com.shruti.demoproject.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chethan.demoproject.R
import com.shruti.demoproject.model.TodoModel

class TodoListAdapter(private val todoList: List<TodoModel>) :
    RecyclerView.Adapter<TodoListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(view: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(view.context).inflate(R.layout.adapter_product_list, view, false)
        return ViewHolder(v);
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.mTodoTitle?.text = todoList[position].title
        if (todoList[position].completed)
            viewHolder.mTodoStatus?.text = "true" else viewHolder.mTodoStatus?.text = "false"
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mTodoTitle = itemView.findViewById<TextView>(R.id.tv_title)
        val mTodoStatus = itemView.findViewById<TextView>(R.id.tv_status)
    }
}