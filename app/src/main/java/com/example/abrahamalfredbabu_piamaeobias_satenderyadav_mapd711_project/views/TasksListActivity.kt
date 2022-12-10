package com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.data_models.Tasks
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.databinding.ActivityTaskListBinding
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.view_models.TaskListViewModel
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.views.fragments.AddTaskFragment
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.views.recyler_view_adapters.TaskListAdapter

class TasksListActivity: AppCompatActivity() {
    private lateinit var binding: ActivityTaskListBinding
    private lateinit var viewModel: TaskListViewModel

    var tasksList = arrayListOf<Tasks>()

    var assignmentId: Int ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Tasks"

        val extras = intent.extras
        if (extras != null) {
            try{
                assignmentId = extras.getInt("assignment_id")

            }catch (e: Exception){
                assignmentId = 1
                e.printStackTrace()
            }
        }else{
            assignmentId = 1
        }


        viewModel = ViewModelProvider(this)[TaskListViewModel::class.java]
        viewModel.getTask(assignmentId!!, this)

        viewModel.publicTasksList.observe(this) {
            tasksList = copyTaskMaintainingExpandableState(tasksList, it)
            val adapter = TaskListAdapter(tasksList, viewModel, this)
            binding.mainActivityTasksRecyclerview.adapter = adapter
            binding.mainActivityTasksRecyclerview.layoutManager = LinearLayoutManager(this)
        }

        binding.tasksListAddTaskButton.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("assignment_id", assignmentId.toString())

            val addTaskFragment = AddTaskFragment()
            addTaskFragment.arguments = bundle
            addTaskFragment.show(supportFragmentManager, "AddTaskTag")
        }


        val adapter = TaskListAdapter(tasksList, viewModel, this)
        binding.mainActivityTasksRecyclerview.adapter = adapter
        binding.mainActivityTasksRecyclerview.layoutManager = LinearLayoutManager(this)

    }

    private fun copyTaskMaintainingExpandableState(
        oldTasksList: ArrayList<Tasks>,
        newTasksList: ArrayList<Tasks>
    ): ArrayList<Tasks> {
        if (oldTasksList.isEmpty()) {
            return newTasksList
        } else {
            var taskList = newTasksList
            for (index in 0 until oldTasksList.size) {
                taskList[index].expandable = oldTasksList[index].expandable
            }
            return taskList
        }
    }

}