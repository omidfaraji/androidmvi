package com.faraji.challenge.vira.ui.selectionpage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.faraji.challenge.vira.R
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnMainPage.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_MainFragment)
        }
        btnUploadPage.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_UploadFragment)
        }
    }
}