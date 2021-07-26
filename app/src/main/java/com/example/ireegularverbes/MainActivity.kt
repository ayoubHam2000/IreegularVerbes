package com.example.ireegularverbes

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ireegularverbes.Objects.IrregularServices
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.InputStream

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

    }

    private fun initView(){
        IrregularServices.load(getText())
        Submit.setOnClickListener {
            if(Min.text.trim().isNotEmpty() && Max.text.trim().isNotEmpty()){
                val min = Min.text.toString().toInt()
                val max = Max.text.toString().toInt() - 1
                IrregularServices.selectTask(min, max)
                val intent = Intent(this, Practice::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this, "Text is Empty", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getText() : String{
        val file = this.assets.open("irregularVerbs")
        val size = file.available()
        val buffer = ByteArray(size)
        file.read(buffer)
        return String(buffer)
    }

}