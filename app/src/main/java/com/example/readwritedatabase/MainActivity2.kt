package com.example.readwritedatabase

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.readwritedatabase.databinding.ActivityMain2Binding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class MainActivity2 : AppCompatActivity() {
    lateinit var databaseReference: DatabaseReference
    lateinit var main2Binding: ActivityMain2Binding
    companion object{
        const val Key1="com.example.readwritedatabase.MainActivity3.name"
        const val Key2="com.example.readwritedatabase.MainActivity3.email"
        const val Key3="com.example.readwritedatabase.MainActivity3.uniqueId"



    }
    override fun onCreate(savedInstanceState: Bundle?) {
        main2Binding= ActivityMain2Binding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(main2Binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        main2Binding.btnSignIn.setOnClickListener {
            val userUniqueId=main2Binding.etUserNameId.text.toString()
            if (userUniqueId.isNotEmpty()){
            readData(userUniqueId)
        }else{
                Toast.makeText(this,"Please enter Valid ID", Toast.LENGTH_SHORT).show()
            }
    }


}

    fun readData(userUniqueid: String){
        databaseReference= FirebaseDatabase.getInstance().getReference("Users")
        databaseReference.child(userUniqueid).get().addOnSuccessListener {

            if (it.exists()){
                val name= it.child("name").value
                val email =it.child("email").value
                val uniqueId=it.child("uniqueId").value


                val intentWelcome= Intent(this, MainActivity3::class.java)
                intentWelcome.putExtra(Key1,name.toString())
                intentWelcome.putExtra(Key2,email.toString())
                intentWelcome.putExtra(Key3,uniqueId.toString())



                startActivity(intentWelcome)

            } else{
                Toast.makeText(this,"User Does not Exist", Toast.LENGTH_SHORT).show()
            }

        }.addOnFailureListener {
            Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show()
        }
    }
}