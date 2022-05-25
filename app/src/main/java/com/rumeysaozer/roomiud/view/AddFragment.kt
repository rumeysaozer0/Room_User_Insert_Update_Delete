package com.rumeysaozer.roomiud.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.rumeysaozer.roomiud.R
import com.rumeysaozer.roomiud.databinding.FragmentAddBinding
import com.rumeysaozer.roomiud.model.User
import com.rumeysaozer.roomiud.viewModel.UserViewModel


class AddFragment : Fragment() {
private lateinit var userViewModel : UserViewModel
    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        binding.ibutton.setOnClickListener {
            val user = User(0,binding.ename.text.toString(),binding.eage.text.toString().toInt())
            userViewModel.addUser(user)
            Toast.makeText(requireContext(),"User added",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }
    }


}