package com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.views.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.R
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.databinding.FragmentAddTaskBinding
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.view_models.TaskListViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

private const val ARG_PARAM1 = "assignment_id"


class AddTaskFragment : BottomSheetDialogFragment() {

    private var assignmentId : Int = 0

    private var _binding: FragmentAddTaskBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            assignmentId = it.getString(ARG_PARAM1)!!.toInt()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        val view = binding.root
        initSpinner()

        val viewModel = ViewModelProvider(requireActivity())[TaskListViewModel::class.java]

        binding.addTaskSaveButton.setOnClickListener{
            if(validateFields()) {
                val taskName = binding.addTaskTaskName.text.toString()
                val priority = binding.addTaskSetPrioritySpinner.selectedItem.toString()
                val description = binding.addTaskDescriptionEditText.text.toString()
                viewModel.saveTask(taskName, assignmentId, priority, description, requireActivity())
                Toast.makeText(requireActivity(), "$taskName has been added", Toast.LENGTH_SHORT).show()
                viewModel.getTask(assignmentId, requireActivity())
                dismiss()
            }
        }

        binding.addTaskCancelButton.setOnClickListener{
            dismiss()
        }

        return binding.root
    }

    private fun validateFields(): Boolean{
        if(binding.addTaskTaskName.text.isEmpty()){
            Toast.makeText(requireActivity(), "Please enter a task Name", Toast.LENGTH_SHORT).show()
            return false
        }
        if(binding.addTaskSetPrioritySpinner.selectedItem.toString() == "Select Priority"){
            Toast.makeText(requireActivity(), "Please select a priority", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun initSpinner(){
        val adapter = ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.priority_list,
            R.layout.add_task_priority_spinner_item
        )
        adapter.setDropDownViewResource(R.layout.add_task_priority_dropdown_item)
        binding.addTaskSetPrioritySpinner.adapter = adapter
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddTaskFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}