package me.yufanlin.funwithflags.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.yufanlin.funwithflags.databinding.ItemCountryBinding
import me.yufanlin.funwithflags.model.Country
import me.yufanlin.funwithflags.util.getProgressDrawable
import me.yufanlin.funwithflags.util.loadImage

class CountryListAdapter(private var countries: ArrayList<Country>) :
    RecyclerView.Adapter<CountryListAdapter.CountryViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun updateCountries(newCountries: List<Country>) {
        countries.clear()
        countries.addAll(newCountries)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CountryViewHolder(
        ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countries[position])
    }

    override fun getItemCount() = countries.size

    class CountryViewHolder(private val binding: ItemCountryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(country: Country) {
            binding.countryName.text = isEmpty(country.countryName)
            binding.countryCapital.text = isEmpty(country.capital)
            binding.imageView.loadImage(country.flag, getProgressDrawable(binding.root.context))
        }

        private fun isEmpty(str: String?) = str?.ifEmpty { "N/A" }
    }
}