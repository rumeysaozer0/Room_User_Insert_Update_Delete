package com.rumeysaozer.roomiud.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rumeysaozer.roomiud.adapter.UserAdapter
import com.rumeysaozer.roomiud.databinding.FragmentListBinding
import com.rumeysaozer.roomiud.viewModel.UserViewModel

class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter : UserAdapter
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        val recyclerView  = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = UserAdapter()
        recyclerView.adapter = adapter
        observeLiveData()
        binding.floatingActionButton.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToAddFragment()
            findNavController().navigate(action)
        }
    }
    fun observeLiveData(){
        userViewModel.readAllData.observe(viewLifecycleOwner, Observer { user->
            user?.let{
                adapter.setData(it)
            }
        })
    }

}