package com.d3if0002.sportyclubs.ui.tentang

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.d3if0002.sportyclubs.databinding.FragmentTentangBinding

class TentangFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentTentangBinding.inflate(inflater, container, false)
        binding.textView.visibility = View.VISIBLE
        return binding.root
    }
}