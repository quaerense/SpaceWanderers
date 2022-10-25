package org.quaerense.spacewanderers.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.quaerense.spacewanderers.databinding.ItemAsteroidBinding
import org.quaerense.spacewanderers.domain.entity.Asteroid

class AsteroidListAdapter : ListAdapter<Asteroid, AsteroidViewHolder>(AsteroidDiffUtilCallback) {

    var page = 1
    var loadNextPage: (pageToLoading: Int) -> Unit = {}
    var loadPreviousPage: (pageToLoading: Int) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsteroidViewHolder {
        val binding = ItemAsteroidBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return AsteroidViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AsteroidViewHolder, position: Int) {
        val asteroid = getItem(position)

        with(holder.binding) {
            tvAsteroidName.text = asteroid.name
            tvAsteroidSize.text = asteroid.absoluteMagnitudeH
        }

        if (position >= currentList.size - 5) {
            loadNextPage.invoke(page++)
        }

        if (position <= 5 && page > 1) {
            loadPreviousPage.invoke(page--)
        }
    }

}

class AsteroidViewHolder(val binding: ItemAsteroidBinding) : RecyclerView.ViewHolder(binding.root)

object AsteroidDiffUtilCallback : DiffUtil.ItemCallback<Asteroid>() {

    override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
        return oldItem == newItem
    }
}