package io.github.regres.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import io.github.regres.R
import io.github.regres.data.entities.Resource
import io.github.regres.databinding.ItemResourceBinding
import timber.log.Timber

class MainAdapter(private val context: Context): RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private var dataSet = ArrayList<Resource>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.item_resource, parent, false))
    }

    override fun getItemCount(): Int {
        return this.dataSet.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = this.dataSet[position]
        holder.apply {
            bind(data)
        }
    }

    fun addResources(dataSet: List<Resource>?) {
        if (dataSet != null) {
            this.dataSet.addAll(dataSet)
            notifyDataSetChanged()
        }
    }

    fun clear() {
        this.dataSet.clear()
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemResourceBinding):
            RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Resource) {
            this.binding.apply {
                resource = data
                executePendingBindings()
            }
        }
    }

}