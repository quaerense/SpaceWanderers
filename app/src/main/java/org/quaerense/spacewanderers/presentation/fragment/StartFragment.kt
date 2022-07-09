package org.quaerense.spacewanderers.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.quaerense.spacewanderers.data.repository.AsteroidInfoRepositoryImpl
import org.quaerense.spacewanderers.databinding.FragmentStartBinding

class StartFragment : Fragment() {

    private var _binding: FragmentStartBinding? = null
    private val binding: FragmentStartBinding
        get() = _binding ?: throw RuntimeException("FragmentStartBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bDownload.setOnClickListener {
            val repository = AsteroidInfoRepositoryImpl(requireActivity().application)
            repository.loadData()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}