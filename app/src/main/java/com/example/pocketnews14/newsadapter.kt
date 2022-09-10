package com.example.pocketnews14


import android.media.Image
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pocketnews14.R

class newsadapter (

) : RecyclerView.Adapter<newsadapter.viewholder>(){
    var newslist: ArrayList<news> = arrayListOf<news>()

    inner class viewholder (newsarticle : View) : RecyclerView.ViewHolder(newsarticle) {
        var title: TextView = newsarticle.findViewById(R.id.tvnewstitle)
        var image: ImageView = newsarticle.findViewById(R.id.ivnews)
    }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.arcticle,parent,false)
            val vh = viewholder(view)


            return vh
        }

        override fun onBindViewHolder(holder: viewholder, position: Int) {
            holder.apply {
                title.text = newslist[position].title
                Glide.with(holder.itemView.context).load(newslist[position].image).to(holder.image)
            }
        }
     fun updatenews(updatenews : ArrayList<news>){
         newslist.clear()
         newslist.addAll(updatenews)

         notifyDataSetChanged()
     }



    override fun getItemCount(): Int {
        return newslist.size
    }
}