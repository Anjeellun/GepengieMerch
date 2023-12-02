package com.dicoding.gepengiemerchandise

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MerchDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_merch_detail)

        val merchName = intent.getStringExtra("merchName")
        val merchDescription = intent.getStringExtra("merchDescription")
        val merchPhoto = intent.getIntExtra("merchPhoto", 0)
        val merchExtraDescription = intent.getStringExtra("merchExtraDescription")

        val ivMerch = findViewById<ImageView>(R.id.iv_merch)
        val detailtvMerch = findViewById<TextView>(R.id.detailtv_merch)
        val textView = findViewById<TextView>(R.id.textView)
        val extraText = findViewById<TextView>(R.id.extra_Text)

        ivMerch.setImageResource(merchPhoto)
        detailtvMerch.text = merchName
        textView.text = merchDescription
        extraText.text = merchExtraDescription

    }

    fun openAbout(view: View) {
        val intent = Intent(this, About::class.java)
        startActivity(intent)
    }

}