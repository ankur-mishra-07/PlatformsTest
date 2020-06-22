package com.platformstest.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.platformstest.R
import com.platformstest.data.models.Row
import com.platformstest.databinding.ItemLayoutBinding

class PostAdapter : RecyclerView.Adapter<PostAdapter.ViewHolder>() {
    private var baseList: List<Row>? = null
    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        i: Int
    ): ViewHolder {
        val postListItemBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(viewGroup.context),
            R.layout.item_layout, viewGroup, false
        )
        return ViewHolder(postListItemBinding as ItemLayoutBinding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val currentItem: Row = baseList!![i]
        viewHolder.peopleListItemBinding.post = currentItem
    }

    override fun getItemCount(): Int {
        return if (baseList != null) {
            baseList!!.size
        } else {
            0
        }
    }

    fun setPeopleList(post: List<Row>?) {
        this.baseList = post
        notifyDataSetChanged()
    }

    inner class ViewHolder(postListItemBinding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(postListItemBinding.root) {
        val peopleListItemBinding: ItemLayoutBinding = postListItemBinding
    }
}