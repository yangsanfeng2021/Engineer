package cn.ysf.ratingbar.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cn.ysf.ratingbar.AndRatingBar
import cn.ysf.ratingbar.R

class RatingBarAdapter(private val mData: List<String>) : RecyclerView.Adapter<RatingBarAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false))
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = mData[position]
        holder.andRatingBar.rating = (position % 6).toFloat()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name)
        var andRatingBar: AndRatingBar = itemView.findViewById(R.id.andRatingBar)
    }
}