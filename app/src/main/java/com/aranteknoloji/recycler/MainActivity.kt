package com.aranteknoloji.recycler

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.aran.custom.recycler.CircleLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setRecyclerView()
    }

    private fun setRecyclerView() = with(main_list) {
        adapter = MainRecyclerAdapter().apply { setData(data) }
        layoutManager = CircleLayoutManager()
//            LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
    }

    private val data = listOf(
        "Apple",
        "Banana",
        "Orange",
        "Kiwi",
        "Water Malon",
        "Avacado",
        "Berry",
        "Strawberry",
        "Juice"
    )
}
