package org.quaerense.spacewanderers.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import org.quaerense.spacewanderers.databinding.FragmentAsteroidListBinding
import org.quaerense.spacewanderers.presentation.adapter.AsteroidListAdapter
import org.quaerense.spacewanderers.presentation.viewmodel.AsteroidListViewModel

class AsteroidListFragment : Fragment() {

    private var _binding: FragmentAsteroidListBinding? = null
    private val binding: FragmentAsteroidListBinding
        get() = _binding ?: throw RuntimeException("FragmentAsteroidListBinding is null")

    private val viewModel: AsteroidListViewModel by lazy {
        ViewModelProvider(this)[AsteroidListViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAsteroidListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = AsteroidListAdapter()
        binding.rvAsteroidList.adapter = adapter

        viewModel.getAsteroidList(1)
        adapter.loadNextPage = { pageToLoading ->
            viewModel.getAsteroidList(pageToLoading)
            adapter.page++
        }
        adapter.loadPreviousPage = { pageToLoading ->
            viewModel.getAsteroidList(pageToLoading)
            adapter.page--
        }

        viewModel.asteroidList.observe(viewLifecycleOwner) {
            adapter.submitList(adapter.currentList + it)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }


    companion object {

        fun newInstance() = AsteroidListFragment()
    }
}