package com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.views.recyler_view_adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.data_models.Assignment
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.database.type_converters.AssignmentTypeConverters
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.databinding.AssingnmentCardBinding
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.view_models.AssignmentListViewModel
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.views.TasksListActivity
import java.text.SimpleDateFormat

class AssignmentListAdapter(
    private var assignmentItems: List<Assignment>,
    private val viewModel: AssignmentListViewModel,
    private val context: Context

) : RecyclerView.Adapter<AssignmentListAdapter.AssignmentListHolder>() {
    inner class AssignmentListHolder(val binding: AssingnmentCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssignmentListHolder {
        val view = LayoutInflater.from(parent.context)
        val binding = AssingnmentCardBinding.inflate(view, parent, false)
        return AssignmentListHolder(binding)
    }

    override fun onBindViewHolder(holder: AssignmentListHolder, position: Int) {
        holder.binding.apply {
            assignmentCardTitle.text = assignmentItems[position].title


            val dateFormat = SimpleDateFormat("dd MMM, yyyy")
            val date = dateFormat.format(assignmentItems[position].dueDate)

            assignmentCardDueDate.text = date

            assignmentCardDescription.text =
                assignmentItems[position].description.ifEmpty { "No description" }


            val isExpandable: Boolean = assignmentItems[position].expandable!!
            assignmentCardExpendableLayout.visibility = if (isExpandable) View.VISIBLE else
                View.GONE

            assignmentCardExpandButton.setOnClickListener {
                val taskDescription = assignmentItems[position]
                taskDescription.expandable = !taskDescription.expandable!!
                notifyItemChanged(position)
            }

            assignmentCardLayout.setOnClickListener {
                val taskListActivityIntent = Intent(context, TasksListActivity::class.java)
                taskListActivityIntent.putExtra("assignment_id", assignmentItems[position].id)
                it.context.startActivity(taskListActivityIntent)
            }

        }
    }

    override fun getItemCount(): Int {
        return assignmentItems.size
    }
}