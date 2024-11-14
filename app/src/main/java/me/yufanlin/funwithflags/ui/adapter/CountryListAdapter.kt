package me.yufanlin.funwithflags.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import me.yufanlin.funwithflags.data.model.Country
import me.yufanlin.funwithflags.databinding.ItemCountryBinding
import me.yufanlin.funwithflags.util.getProgressDrawable
import me.yufanlin.funwithflags.util.loadImage

class CountryListAdapter : RecyclerView.Adapter<CountryListAdapter.CountryViewHolder>() {

    private val diff = AsyncListDiffer(this, object : DiffUtil.ItemCallback<Country>() {
        override fun areItemsTheSame(oldItem: Country, newItem: Country) =
            oldItem.countryName == newItem.countryName

        override fun areContentsTheSame(oldItem: Country, newItem: Country) =
            oldItem == newItem
    })


    fun updateCountries(newCountries: List<Country>) {
        diff.submitList(emptyList())
        diff.submitList(newCountries)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CountryViewHolder(
        ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(diff.currentList[position])
    }

    override fun getItemCount() = diff.currentList.size

    inner class CountryViewHolder(private val binding: ItemCountryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(country: Country) {
            binding.countryName.text = isEmpty(country.countryName)
            binding.countryCapital.text = isEmpty(country.capital)
            binding.imageView.loadImage(country.flag, getProgressDrawable(binding.root.context))
        }

        private fun isEmpty(str: String?) = str?.ifEmpty { "N/A" }
    }
}