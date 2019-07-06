package com.jaqxues.thunder.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.jaqxues.thunder.R
import kotlinx.android.synthetic.main.fragment_user_information.*

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [UserInformationFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 */
class UserInformationFragment : Fragment() {
    private var listener: OnFragmentInteractionListener? = null
    private lateinit var viewModel: UserInformationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_information, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(UserInformationViewModel::class.java)
        viewModel.patientSummary.observe(this, Observer {
            fetch_progress.visibility = View.GONE
            info_container.visibility = View.VISIBLE
            var txt = ""
            it.allergies.forEach { allergy ->
                allergy.entries.forEach { entry ->
                    txt += "${entry.displayName}\n"
                }
            }
            allergies.text = txt.trim()

            txt = ""
            it.active_problems.forEach {problem ->
                txt += "Signs:"
                problem.signs.forEach { sign ->
                    txt += "\n\t${sign.displayName}: ${sign.value.value}${sign.value.unit}"
                }
            }
            current_problems.text = txt.trim()

            txt = ""
            it.substanceAdministration.forEach { substance ->
                txt += "${substance.doseQuantity}* ${substance.consumable.name}\n"
            }
            medicine.text = txt.trim()
        })

        medicine_card.setOnClickListener {
            listener?.openMedicineQRs()
        }

        finance_card.setOnClickListener {
            listener?.openTransactions()
        }

        health_check_card.setOnClickListener {
            listener?.openHealthCheck()
        }

        viewModel.refreshItems()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface OnFragmentInteractionListener {
        fun openMedicineQRs()

        fun openTransactions()

        fun openHealthCheck()
    }

    companion object {
        const val TAG = "UserInformationFragment"
    }
}
