package com.rumeysaozer.roomiud.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.rumeysaozer.roomiud.R
import com.rumeysaozer.roomiud.databinding.FragmentListBinding
import com.rumeysaozer.roomiud.databinding.FragmentUpdateBinding
import com.rumeysaozer.roomiud.model.User
import com.rumeysaozer.roomiud.viewModel.UserViewModel


class UpdateFragment : Fragment() {
    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!
    private lateinit var userViewModel: UserViewModel
    private val args by navArgs<UpdateFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        binding.uname.setText(args.currentUser.name)
        binding.uage.setText(args.currentUser.age.toString())
        binding.ubutton.setOnClickListener {
        val updateUser = User(args.currentUser.id, binding.uname.text.toString(),binding.uage.text.toString().toInt())
            userViewModel.updateUser(updateUser)
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        setHasOptionsMenu(true)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.delete){
          val builder = AlertDialog.Builder(requireContext())
          builder.setTitle("${args.currentUser.name} will delete")
            builder.setMessage("are you sure you want to delete?")
            builder.setPositiveButton("yes"){_, _->
               userViewModel.deleteUser(args.currentUser)
                findNavController().navigate(R.id.action_updateFragment_to_listFragment)
            }
            builder.setNegativeButton("no"){_,_->}
            builder.create().show()
        }
        return super.onOptionsItemSelected(item)
    }


}