package org.quaerense.spacewanderers.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import org.quaerense.spacewanderers.R
import org.quaerense.spacewanderers.databinding.FragmentStartBinding
import org.quaerense.spacewanderers.domain.state.*
import org.quaerense.spacewanderers.presentation.viewmodel.StartViewModel

class StartFragment : Fragment() {

    private var _binding: FragmentStartBinding? = null
    private val binding: FragmentStartBinding
        get() = _binding ?: throw RuntimeException("FragmentStartBinding is null")

    private val viewModel by lazy {
        ViewModelProvider(this)[StartViewModel::class.java]
    }

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

        with(binding) {
            val downloadPercent = viewModel.getDownloadPercent()
            tvDownloadProgress.text = downloadPercent.toPercent()
            pbDownloadProgress.progress = downloadPercent

            viewModel.downloadProgress().observe(viewLifecycleOwner) { downloadState ->
                parseState(downloadState)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }


    private fun parseState(downloadState: DownloadState) {
        with(binding) {
            when (downloadState) {
                is Pending -> {
                    with(bDownload) {
                        text = requireContext().getString(R.string.download_start)
                        setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.black))
                        setOnClickListener { viewModel.startDownload() }
                    }
                }
                is Loading -> {
                    with(bDownload) {
                        isClickable = true
                        setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.black))
                        text = requireContext().getString(R.string.download_stop)
                        setOnClickListener { viewModel.stopDownload() }
                    }
                    pbDownloadProgress.progress = downloadState.downloadingPercent
                    tvDownloadProgress.text = downloadState.downloadingPercent.toPercent()
                }
                is Failed -> {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                    with(bDownload) {
                        isClickable = true
                        setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.black))
                        text = requireContext().getString(R.string.download_start)
                        setOnClickListener { viewModel.startDownload() }
                    }
                }
                is Restarting -> {
                    with(bDownload) {
                        isClickable = false
                        setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.grey))
                        text = requireContext().getString(R.string.download_restarting)
                    }
                }
                is Succeeded -> {
                    with(bDownload) {
                        isClickable = false
                        setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.grey))
                        text = requireContext().getString(R.string.download_succeeded)
                    }
                    pbDownloadProgress.progress = 100
                    tvDownloadProgress.text = 100.toPercent()
                    Toast.makeText(context, "Succeeded", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun Int.toPercent() = "$this%"
}