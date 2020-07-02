package com.example.cue

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import java.util.*


class CoverFlowAdapter(private val mContext: Context) : BaseAdapter() {
    private var mData: ArrayList<GameEntity> = ArrayList<GameEntity>(0)
    fun setData(data: ArrayList<GameEntity>) {
        mData = data
    }

    override fun getCount(): Int {
        return mData.size
    }

    override fun getItem(pos: Int): Any {
        return mData[pos]
    }

    override fun getItemId(pos: Int): Long {
        return pos.toLong()
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View? {
        var rowView: View? =null
        rowView = convertView
        if (rowView == null) {

            val inflater =
                mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater


            rowView = inflater.inflate(R.layout.item_coverflow, parent, false)

            //parent.addView(rowView, 100, 100);
            val viewHolder = ViewHolder()
            viewHolder.text = rowView.findViewById<View>(R.id.label) as TextView
            viewHolder.image = rowView
                .findViewById<View>(R.id.image) as ImageView
            rowView.tag = viewHolder
        }
        val scalingFactor = 2f //

        if (rowView != null) {
            rowView.scaleX = scalingFactor
        }
        if (rowView != null) {
            rowView.scaleY = scalingFactor
        }
        val holder = rowView?.tag as ViewHolder
        holder.image!!.setImageResource(mData[position].imageResId)
        holder.text?.setText(mData[position].titleResId)
        return rowView
    }

    internal class ViewHolder {
        var text: TextView? = null
        var image: ImageView? = null
    }

}