package com.kdroid.allnote.view.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.kdroid.allnote.databinding.FragmentListBinding
import com.kdroid.allnote.view.ListAction
import com.kdroid.allnote.view.note.NoteFragment
import com.kdroid.allnote.view.note.NoteFragmentArgs
import com.kdroid.allnote.view.note.NoteListAdapter


class ListFragment : Fragment(),ListAction {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val listAdapter = NoteListAdapter(arrayListOf(),this)
    private lateinit var viewMode: ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.noteListView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
        }
        viewMode = ViewModelProviders.of(this).get(ListViewModel::class.java)
        binding.createBtn.setOnClickListener { goToNoteDetails() }

        observeViewModel()
    }

    override fun onResume() {
        super.onResume()
        viewMode.getAllNoteData()
    }

    private fun goToNoteDetails(id: Long = 0L) {
        val action: NavDirections = ListFragmentDirections.actionGoToNote(id)
        Navigation.findNavController(binding.noteListView).navigate(action)
    }

    private fun observeViewModel() {
        viewMode.note.observe(viewLifecycleOwner, Observer { noteList ->
            binding.loadingView.visibility = View.GONE
            binding.noteListView.visibility = View.VISIBLE
            listAdapter.updateList(noteList.sortedByDescending { it.updatedTime })
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(id: Long) {
        goToNoteDetails(id)
    }

}