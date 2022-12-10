package com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.views


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.databinding.ActivityAssignmentListBinding
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.view_models.AssignmentListViewModel
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.views.fragments.AddAssignmentFragment
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.views.recyler_view_adapters.AssignmentListAdapter


class AssignmentListActivity : AppCompatActivity() {

    private lateinit var viewModel: AssignmentListViewModel
    private lateinit var binding: ActivityAssignmentListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAssignmentListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "My Assignments"

        viewModel = ViewModelProvider(this)[AssignmentListViewModel::class.java]
        binding.newTaskButton.setOnClickListener {
                AddAssignmentFragment(null).show(supportFragmentManager, "newTaskTag")
        }
        viewModel.getAssignments(this)

        // observe live data and populate recycler view
        viewModel.publicAssignmentList.observe(this){
            binding.assignmentList.apply {
                layoutManager = LinearLayoutManager(applicationContext)
                adapter = AssignmentListAdapter(it, viewModel, this@AssignmentListActivity)
            }
        }
    }


}