package io.github.regres.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.regres.R
import io.github.regres.data.entities.Resource
import io.github.regres.databinding.ItemResourceBinding

class MainAdapter(private val context: Context): RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private var dataSet = ArrayList<Resource>()
    private var userAdapters = ArrayList<UserAdapter>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(context, DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.item_resource, parent, false))
    }

    override fun getItemCount(): Int {
        return this.dataSet.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = this.dataSet[position]
        val adapter = this.userAdapters[position]

        holder.binding.userRecycler.adapter = adapter
        holder.apply {
            bind(data)
        }
    }

    fun addResources(dataSet: List<Resource>?) {
        if (dataSet != null) {
            this.dataSet.addAll(dataSet)
            for (i in this.dataSet) {
                this.userAdapters.add(UserAdapter(context))
            }
            notifyDataSetChanged()
        }
    }

    fun clear() {
        this.dataSet.clear()
        this.userAdapters.clear()
        notifyDataSetChanged()
    }

    class ViewHolder(val context: Context, val binding: ItemResourceBinding):
            RecyclerView.ViewHolder(binding.root) {
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        init {
            this.binding.userRecycler.layoutManager = layoutManager
        }
        fun bind(data: Resource) {
            this.binding.apply {
                resource = data
                executePendingBindings()
            }
        }
    }

}