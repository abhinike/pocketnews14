package com.example.pocketnews14

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.example.pocketnews14.ui.main.MySingleton
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

private lateinit var madapter: newsadapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val recyclerView : RecyclerView = findViewById(R.id.rcfragmain)

        recyclerView.layoutManager = LinearLayoutManager(this)
         fetchdata()
        val madapter = newsadapter()
        recyclerView.adapter = madapter


    }

        fun fetchdata() {
            val url = "https://newsapi.org/v2/top-headlines?country=in&category=business&apiKey=ac7b8bf7d27e4616b86b4dd319702525"
            val jsonObjectRequest = JsonObjectRequest(
                Request.Method.GET, url, null,
                { response ->
                    val it = JSONObject()
                    val jsonobjectarray = it.getJSONArray("article")
                    var newsarray = ArrayList<news>()
                    for (i in 0 until jsonobjectarray.length()) {
                        val jsonObject = jsonobjectarray.getJSONObject(i)
                        val news = news(
                            jsonObject.getString("urlToImage"),
                            jsonObject.getString("title")
                        )
                        newsarray.add(news)
                    }

                },
                { error ->
                    // TODO: Handle error
                }
            )

// Access the RequestQueue through your singleton class.
            MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)

        }

}
