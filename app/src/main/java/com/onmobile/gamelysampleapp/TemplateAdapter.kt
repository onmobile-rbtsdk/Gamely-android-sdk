package com.onmobile.gamelysampleapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView

class TemplateAdapter(
    private val templateList: List<MyTemplate>,
    private val listener: ITemplateSelectedListener,
) :
    RecyclerView.Adapter<TemplateAdapter.TemplateViewHolder>() {

    inner class TemplateViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.txt_title)
        val bgImage: AppCompatImageView = view.findViewById(R.id.bg_image)
        val listItemLayout: RelativeLayout = view.findViewById(R.id.list_item_layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TemplateViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_template_item, parent, false)
        return TemplateViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TemplateViewHolder, position: Int) {
        if (templateList[position].background != null) {
            holder.bgImage.setImageResource(templateList[position].background!!)
        } else {
            holder.bgImage.setBackgroundColor(Color.parseColor("#FF6200EE"))
        }
        holder.title.text = templateList[position].name
        holder.listItemLayout.setOnClickListener {
            listener.onTemplateSelected(templateList[position])
        }
    }

    override fun getItemCount(): Int {
        return templateList.size
    }


    interface ITemplateSelectedListener {
        fun onTemplateSelected(template: MyTemplate)
    }
}

