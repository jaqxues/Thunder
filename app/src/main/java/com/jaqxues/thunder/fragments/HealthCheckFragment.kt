package com.jaqxues.thunder.fragments

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.jaqxues.thunder.R
import kotlinx.android.synthetic.main.fragment_health_check.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HealthCheckFragment : ScopedFragment() {
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_health_check, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        temp_symps_btn.setOnClickListener {
            showDialog(requireActivity(), "Select")
        }

        send_report_btn.setOnClickListener {
            Toast.makeText(requireActivity(), "Uploading Report", Toast.LENGTH_LONG).show()
            launch {
                delay(1500)
                listener?.uploadedItem()
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun uploadedItem()
    }

    companion object {
        @JvmStatic
        fun showDialog(activity: Activity, msg: String) {
            AlertDialog.Builder(activity)
                .setTitle("Transient Symptoms")
                .setMultiChoiceItems(
                    arrayOf("Edema of the ankle", "Shortness of breath", "Coughing", "Unusually high urine production", "Heavy thirst", "Dizziness"),
                    booleanArrayOf(false, false, false, false, false, false)
                ) { _, _, _ ->

                }.setCancelable(false)
                .setPositiveButton(msg) { dialog, _ ->
                    dialog.dismiss()
                }
                .setNeutralButton("Cancel") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }
}
