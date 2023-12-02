package com.dicoding.gepengiemerchandise

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvMerch: RecyclerView
    private lateinit var svMerch: SearchView
    private val list = ArrayList<Merch>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        svMerch = findViewById(R.id.sv_merch)
        svMerch.clearFocus()
        svMerch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)

                return true
            }
        })

        rvMerch = findViewById(R.id.rv_merch)
        rvMerch.setHasFixedSize(true)
        list.addAll(getListMerch())
        showRecyclerList()

    }

    private fun getListMerch(): ArrayList<Merch> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listMerch = ArrayList<Merch>()
        for (i in dataName.indices) {
            val merch = Merch(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listMerch.add(merch)
        }

        dataPhoto.recycle()
        return listMerch
    }

    private fun showRecyclerList() {
        rvMerch.layoutManager = LinearLayoutManager(this)
        val listMerchAdapter = ListMerchAdapter(list)
        listMerchAdapter.setOnItemClickListener { position ->
            val intent = Intent(this@MainActivity, MerchDetail::class.java)
            intent.putExtra("merchName", list[position].name)
            intent.putExtra("merchDescription", list[position].description)
            intent.putExtra(
                "merchExtraDescription",
                resources.getStringArray(R.array.merchExtraDescription)[position]
            )
            startActivity(intent)
        }

        rvMerch.adapter = listMerchAdapter
    }

    private fun filterList(newText: String?) {
        val filteredList = ArrayList<Merch>()

        for (merch in list) {
            if (newText.isNullOrBlank() || merch.name.contains(newText, ignoreCase = true)) {
                filteredList.add(merch)
            }
        }

        fun openAboutActivity(view: View) {
            val intent = Intent(this, About::class.java)
            startActivity(intent)
        }

        val listMerchAdapter = ListMerchAdapter(filteredList)
        rvMerch.adapter = listMerchAdapter
    }
}
