package com.yourname.simpletodoapp // Make sure this package name matches yours

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private val tasks = mutableListOf<String>()
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextTask = findViewById<EditText>(R.id.editTextTask)
        val buttonAdd = findViewById<Button>(R.id.buttonAdd)
        val listViewTasks = findViewById<ListView>(R.id.listViewTasks)

        // Set up the adapter to display tasks in the ListView
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, tasks)
        listViewTasks.adapter = adapter

        // Add task when the button is clicked
        buttonAdd.setOnClickListener {
            val task = editTextTask.text.toString()
            if (task.isNotEmpty()) {
                tasks.add(task)
                adapter.notifyDataSetChanged()
                editTextTask.text.clear()
            } else {
                Toast.makeText(this, "Please enter a task", Toast.LENGTH_SHORT).show()
            }
        }

        // Optional: Remove task when an item is clicked
        listViewTasks.setOnItemClickListener { parent, view, position, id ->
            val selectedTask = tasks[position]
            tasks.removeAt(position)
            adapter.notifyDataSetChanged()
            Toast.makeText(this, "'$selectedTask' removed", Toast.LENGTH_SHORT).show()
        }
    }
}