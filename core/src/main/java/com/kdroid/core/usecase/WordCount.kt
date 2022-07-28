package com.kdroid.core.usecase

import android.util.Log
import com.kdroid.core.data.Note

class WordCount {
    operator fun invoke(note: Note) :Int{
      val res = getCount(note.title) + getCount(note.content)
      Log.e("tag","${getCount(note.title)} ${getCount(note.content)} ${note.title}")
        return res
    }

    private fun getCount(str: String) =
        str.split(" ", "\n")
            .filter {
                it.contains(Regex(".*[a-zA-Z].*"))
            }
            .count()
}