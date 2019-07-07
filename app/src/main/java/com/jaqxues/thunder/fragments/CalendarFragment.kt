package com.jaqxues.thunder.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import com.jaqxues.thunder.R
import kotlinx.android.synthetic.main.fragment_calendar.view.*


class CalendarFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.calendar_add_fab.setOnClickListener {
            val snack = Snackbar.make(view, "Fix new Appointment with doctor not yet implemented", Snackbar.LENGTH_LONG)
            snack.show()
        }
    }
}
