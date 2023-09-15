package com.example.henhouse.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.henhouse.MainActivity
import com.example.henhouse.R
import com.example.henhouse.bdd.AppDatabase
import com.example.henhouse.databinding.FragmentDashboardBinding
import com.example.henhouse.entity.Chicken
import com.example.henhouse.ui.new.NewFragment
import com.example.henhouse.ui.new.NewViewModel

class DashboardFragment : Fragment() {

    companion object {
        fun dashboardFragment() = DashboardFragment()
    }

    private lateinit var viewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)

        var db = AppDatabase.getDatabase(requireActivity().applicationContext)
        val chickens: List<String> = db.chickenDao().getAllName()

        // use arrayadapter and define an array
        val arrayAdapter: ArrayAdapter<*>

        // access the listView from xml file
        var mListView = view?.findViewById<ListView>(R.id.listView)
        arrayAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            chickens
        )
        if (mListView != null) {
            mListView.adapter = arrayAdapter
        }
    }
}