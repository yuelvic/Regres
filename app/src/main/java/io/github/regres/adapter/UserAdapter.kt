package io.github.regres.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import io.github.regres.R
import io.github.regres.data.entities.User
import io.github.regres.databinding.ItemUserBinding
import io.github.regres.ui.user.UserActivity

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
            bind(data, createClickListener(data))
        }
    }

    fun addUsers(dataSet: List<User>?) {
        if (dataSet != null) {
            this.dataSet.addAll(dataSet)
            notifyDataSetChanged()
        }
    }

    fun setUsers(dataSet: List<User>?) {
        clear()
        if (dataSet != null) {
            this.dataSet.addAll(dataSet)
        }
    }

    fun clear() {
        this.dataSet.clear()
        notifyDataSetChanged()
    }

    private fun createClickListener(data: User): View.OnClickListener {
        return View.OnClickListener {
            val intent = Intent(context, UserActivity::class.java)
            intent.putExtra("USER_INTENT", Gson().toJson(data))
            context.startActivity(intent)
        }
    }

    class ViewHolder(private val binding: ItemUserBinding):
            RecyclerView.ViewHolder(binding.root) {
        fun bind(data: User, listener: View.OnClickListener) {
            binding.apply {
                user = data
                clickListener = listener
                executePendingBindings()
            }
        }
    }

}