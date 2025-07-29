package com.example.readwritedatabase

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.readwritedatabase.databinding.ActivityMain3Binding

class MainActivity3 : AppCompatActivity() {
    lateinit var main3Binding: ActivityMain3Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        main3Binding= ActivityMain3Binding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(main3Binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val name=intent.getStringExtra(MainActivity2.Key1)
        val email=intent.getStringExtra(MainActivity2.Key2)
        val uniqueId=intent.getStringExtra(MainActivity2.Key3)



        main3Binding.textView.text="Welcome $name"
        main3Binding.tvMail.text="Mail: $email"
        main3Binding.tvId.text="UniqueId: $uniqueId"





    }
}