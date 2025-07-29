package com.example.readwritedatabase

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.readwritedatabase.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    lateinit var dataBaseReference: DatabaseReference
    lateinit var mainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        mainBinding= ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(mainBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        mainBinding.button.setOnClickListener {
            val name = mainBinding.etName.text.toString().trim()
            val email = mainBinding.etEmail.text.toString().trim()
            val uniqueId = mainBinding.etUniqueId.text.toString().trim()

            if (name.isEmpty() || email.isEmpty() || uniqueId.isEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val user = User(name, email, uniqueId)

            dataBaseReference = FirebaseDatabase.getInstance().getReference("Users")
            dataBaseReference.child(uniqueId).setValue(user)
                .addOnSuccessListener {
                    val intent = Intent(this, MainActivity2::class.java)
                    startActivity(intent)
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Failed to save", Toast.LENGTH_SHORT).show()
                }
        }

    }
}