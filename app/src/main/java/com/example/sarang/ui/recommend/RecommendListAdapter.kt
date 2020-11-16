package com.example.sarang.ui.recommend

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sarang.R
import com.example.sarang.data.model.User
import com.example.sarang.databinding.ItemRecommendBinding

class RecommendListAdapter
    : RecyclerView.Adapter<RecommendListAdapter.RecommendViewHolder>() ,
    Filterable{
    var userList = ArrayList<User>()
    var filteredList = userList

    init {
        setHasStableIds(true)
    }

    fun setList(itemList: ArrayList<User>){
        this.userList = itemList
        this.filteredList = userList
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendViewHolder {
        return RecommendViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.item_recommend, parent, false)
        )
    }

    override fun getItemId(position: Int): Long {
        return filteredList[position].name.hashCode().toLong()
    }

    override fun getFilter(): Filter {
        return UserFilter()
    }

    override fun getItemCount(): Int {
        return filteredList.size
    }

    override fun onBindViewHolder(holder: RecommendViewHolder, position: Int) {
        holder.bind(filteredList[position])
    }

    inner class RecommendViewHolder(private val binding:ItemRecommendBinding):
        RecyclerView.ViewHolder(binding.root){

        fun bind(item:User){
            binding.user = item
        }

    }

    inner class UserFilter: Filter(){
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val result = FilterResults()

            if (constraint == null || constraint.isEmpty()){
                result.count = userList.size
                result.values = userList
            }
            else{
                val constraintList = ArrayList<User>()

                for(item:User in userList){
                    if(item.name.contains(constraint.toString())){
                        constraintList.add(item)
                    }
                }

                result.count = constraintList.size
                result.values = constraintList
            }

            return result
        }

        override fun publishResults(p0: CharSequence?, result: FilterResults?) {
            filteredList = result?.values as ArrayList<User>

            notifyDataSetChanged()
        }
    }
}
