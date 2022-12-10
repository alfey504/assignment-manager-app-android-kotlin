package com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.views.fragments

import android.app.DatePickerDialog
import android.content.ContentValues.TAG
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.data_models.Assignment
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.databinding.FragmentAddAssignmentBinding
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.view_models.AssignmentListViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.schedule

class AddAssignmentFragment(var taskItem: Assignment?) : BottomSheetDialogFragment()
{
    private lateinit var binding: FragmentAddAssignmentBinding
    private lateinit var viewModel: AssignmentListViewModel
    private var dueDate: String = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()

        viewModel = ViewModelProvider(activity)[AssignmentListViewModel::class.java]
        binding.addAssignmentSaveButton.setOnClickListener {
            saveAction()
        }

        binding.addAssignmentDatePicker.setOnClickListener {
           // openTimePicker()
            val c = Calendar.getInstance()
            val yr = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)
            val display = DatePickerDialog(activity, DatePickerDialog.OnDateSetListener {
                    _, year, monthOfYear, dayOfMonth ->
                var monthInput = (monthOfYear + 1).toString()
                if (monthInput.toInt() == 1) {
                    monthInput = "Jan"
                } else if (monthInput.toInt() == 2) {
                    monthInput = "Feb"
                } else if (monthInput.toInt() == 3) {
                    monthInput = "March"
                } else if (monthInput.toInt() == 4) {
                    monthInput = "April"
                } else if (monthInput.toInt() == 5) {
                    monthInput = "May"
                } else if (monthInput.toInt() == 6) {
                    monthInput = "June"
                } else if (monthInput.toInt() == 7) {
                    monthInput = "July"
                } else if (monthInput.toInt() == 8) {
                    monthInput = "Aug"
                } else if (monthInput.toInt() == 9) {
                    monthInput = "Sept"
                } else if (monthInput.toInt() == 10) {
                    monthInput = "Oct"
                } else if (monthInput.toInt() == 11) {
                    monthInput = "Nov"
                } else if (monthInput.toInt() == 12) {
                    monthInput = "Dec"
                }
                binding.addAssignmentDatePicker.text = ("$dayOfMonth $monthInput, $year")
            }, yr, month, day)
            display.datePicker.minDate = System.currentTimeMillis()
            display.show()
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAddAssignmentBinding.inflate(inflater,container,false)
        return binding.root
    }


    private fun saveAction() {

        if(validateFields()){
            val title = binding.addAssignmentAssignmentTitle.text.toString()
            val description = binding.addAssignmentDescription.text.toString()
            val dueDate = binding.addAssignmentDatePicker.text.toString()

            val dateFormat = SimpleDateFormat("dd MMM, yyyy")
            val date = dateFormat.parse(dueDate)

            val assignment = Assignment(
                title = title,
                description = description,
                dueDate = date,
            )

            viewModel.addAssignment(assignment, requireActivity())
            dismiss()
        }

    }

    private fun validateFields(): Boolean {
        binding.apply {
            if(addAssignmentAssignmentTitle.text.toString() == ""){
                Toast.makeText(context, "Please enter a title", Toast.LENGTH_SHORT).show()
                return false
            }

            if(addAssignmentDatePicker.text.toString().lowercase() == "select date"){
                Toast.makeText(context, "Please select a date", Toast.LENGTH_SHORT).show()
                return false
            }

            if(addAssignmentDescription.text.toString() == ""){
                Toast.makeText(context, "Please enter a description", Toast.LENGTH_SHORT).show()
                return false
            }
        }
        return  true
    }

}