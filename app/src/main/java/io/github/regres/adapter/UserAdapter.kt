package io.github.regres.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import io.github.regres.R
import io.github.regres.data.entities.User
import io.github.regres.databinding.ItemUserBinding

class UserAdapter(private val context: Context): RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private val dataSet = ArrayList<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.item_user, parent, false))
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

    fun addUsers(dataSet: List<User>?) {
        if (dataSet != null) {
            this.dataSet.addAll(dataSet)
            notifyDataSetChanged()
        }
    }

    fun clear() {
        this.dataSet.clear()
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemUserBinding):
            RecyclerView.ViewHolder(binding.root) {
        fun bind(data: User) {
            binding.apply {
                user = data
                executePendingBindings()
            }
        }
    }

}