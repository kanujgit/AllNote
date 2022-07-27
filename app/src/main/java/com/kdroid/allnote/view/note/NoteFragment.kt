package com.kdroid.allnote.view.note

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.kdroid.allnote.R
import com.kdroid.allnote.databinding.FragmentNoteBinding
import com.kdroid.core.data.Note


class NoteFragment : Fragment() {

    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: NoteViewModel
    private var currentNote = Note("", "", 0L, 0L)
    private var noteId = 0L


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)
        arguments?.let {
            noteId = NoteFragmentArgs.fromBundle(it).noteId
        }
        if (noteId != 0L)
            viewModel.getCurrentNote(noteId)
        observedViewModel()

        binding.checkBtn.setOnClickListener {
            if (binding.titleView.text.toString()
                    .isNotEmpty() || binding.contentView.text.toString().isNotEmpty()
            ) {
                var time = System.currentTimeMillis()
                currentNote.title = binding.titleView.text.toString()
                currentNote.content = binding.contentView.text.toString()
                currentNote.updatedTime = time
                if (currentNote.id == 0L)
                    currentNote.creationTime = time

                viewModel.savedNote(currentNote)
            } else {
                Navigation.findNavController(it).popBackStack()
            }
        }
    }

    private fun observedViewModel() {
        viewModel.saved.observe(viewLifecycleOwner) {
            if (it) {
                hideKeyBoard()
                Navigation.findNavController(binding.contentView).popBackStack()
                Toast.makeText(context, "Done", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(context, "Something went wrong", Toast.LENGTH_LONG).show()
            }
        }

        viewModel.currentNote.observe(viewLifecycleOwner, Observer {
            it?.let {
                currentNote = it
                binding.titleView.setText(it.title)
                binding.contentView.setText(it.content)
            }
        })
    }

    private fun hideKeyBoard() {
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.contentView.windowToken, 0)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.note_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete_note -> {
                if (context != null && noteId != 0L) {
                    AlertDialog.Builder(context)
                        .setTitle("Delete Note")
                        .setMessage("Are you sure you want to delete this note!")
                        .setPositiveButton("Yes") { _, _ -> viewModel.deleteNote(currentNote) }
                        .setNegativeButton("No") { dialog, _ -> dialog.dismiss() }
                        .show()
                }
            }
        }
        return true
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}