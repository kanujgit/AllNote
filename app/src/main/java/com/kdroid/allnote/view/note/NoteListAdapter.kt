package com.kdroid.allnote.view.note

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kdroid.allnote.databinding.ItemNoteBinding
import com.kdroid.allnote.view.ListAction
import com.kdroid.core.data.Note
import java.text.SimpleDateFormat
import java.util.*


class NoteListAdapter(var notes: ArrayList<Note>,val action: ListAction) :
    RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>() {

    inner class NoteViewHolder(private val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(note: Note) {
            binding.title.text = note.title
            binding.content.text = note.content
            val sdf = SimpleDateFormat("MM dd, HH:mm:ss")
            val res = Date(note.updatedTime)
            binding.date.text = "Last updated: ${sdf.format(res)}"
            binding.noteLayout.setOnClickListener{action.onClick(id = note.id)}
        }
    }

    fun updateList(newNote: List<Note>) {
        notes.clear()
        notes.addAll(newNote)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewItemListBinding: ItemNoteBinding =
            ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(viewItemListBinding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(notes[position])
    }

    override fun getItemCount(): Int = notes.size
}