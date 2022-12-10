package com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.views.recyler_view_adapters

import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.data_models.Tasks
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.databinding.TaskItemsBinding
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.view_models.TaskListViewModel


class TaskListAdapter(
    private var tasks: ArrayList<Tasks>,
    private val viewModel: TaskListViewModel,
    private val context: Context
) : RecyclerView.Adapter<TaskListAdapter.TaskListHolder>() {
    inner class TaskListHolder(val binding: TaskItemsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskListHolder {
        val view = LayoutInflater.from(parent.context)
        val binding = TaskItemsBinding.inflate(view, parent, false)
        return TaskListHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskListHolder, position: Int) {
        holder.binding.apply {
            taskItemsTitleTextview.text = tasks[position].title
            if (tasks[position].completed) {
                holder.binding.taskItemsTitleTextview.apply {
                    paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                }
            }
            taskItemsCheckbox.isChecked = tasks[position].completed
            taskItemsPriorityTextview.text = tasks[position].priority
            mainActivityDescriptionTextview.text =
                tasks[position].description.ifEmpty { "No description" }


            val isExpandable: Boolean = tasks[position].expandable!!
            mainActivityExpendableLayout.visibility = if (isExpandable) View.VISIBLE else
                View.GONE

            mainActivityTasksLayout.setOnClickListener {
                val taskDescription = tasks[position]
                taskDescription.expandable = !taskDescription.expandable!!
                notifyItemChanged(position)
            }

            taskItemsCheckbox.setOnCheckedChangeListener { _, isChecked ->
                tasks[position].completed = isChecked
                Log.i(TAG, "TasksListAdapter -> " + tasks[position].completed.toString())
                if (tasks[position].completed) {
                    taskItemsTitleTextview.apply {
                        paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                    }
                }else{
                    taskItemsTitleTextview.paintFlags = taskItemsTitleTextview.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                }
                viewModel.updateCompletedOfTask(tasks[position].id!!, isChecked, context)
            }
        }
    }

    override fun getItemCount(): Int {
        return tasks.size
    }
}