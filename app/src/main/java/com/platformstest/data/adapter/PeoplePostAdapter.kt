package com.platformstest.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.platformstest.R
import com.platformstest.data.models.FetcherModelItem
import com.platformstest.databinding.ItemLayoutBinding

class PeoplePostAdapter : RecyclerView.Adapter<PeoplePostAdapter.EmployeeViewHolder>() {
    private var peopleList: List<FetcherModelItem>? = null
    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        i: Int
    ): EmployeeViewHolder {
        val employeeListItemBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(viewGroup.context),
            R.layout.item_layout, viewGroup, false
        )
        return EmployeeViewHolder(employeeListItemBinding as ItemLayoutBinding)
    }

    override fun onBindViewHolder(employeeViewHolder: EmployeeViewHolder, i: Int) {
        val currentItem: FetcherModelItem = peopleList!![i]
        employeeViewHolder.peopleListItemBinding.people = currentItem
    }

    override fun getItemCount(): Int {
        return if (peopleList != null) {
            peopleList!!.size
        } else {
            0
        }
    }

    fun setPeopleList(employees: List<FetcherModelItem>?) {
        this.peopleList = employees
        notifyDataSetChanged()
    }

    inner class EmployeeViewHolder(employeetListItemBinding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(employeetListItemBinding.root) {
        val peopleListItemBinding: ItemLayoutBinding = employeetListItemBinding
    }
}