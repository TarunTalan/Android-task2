package com.example.myapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class SecondFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val createNewAcc: TextView = view.findViewById(R.id.createNewAcc)
        createNewAcc.setOnClickListener {
            val thirdFragment = ThirdFragment()
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment, thirdFragment)
                commit()
            }
        }
    }
}
