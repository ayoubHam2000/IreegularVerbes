package com.example.ireegularverbes

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.ireegularverbes.Class.Irregular
import com.example.ireegularverbes.Objects.IrregularServices
import kotlinx.android.synthetic.main.activity_practice.*
import java.util.*
import kotlin.concurrent.thread

class Practice : AppCompatActivity() {

    private var selectedItem : Irregular? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice)

        intFun()
        initButtons()

    }

    private fun intFun(){
        selectedItem = IrregularServices.selectOne()
        if(selectedItem == null){
            finish()
        }else{
            verbName.text = selectedItem!!.theVerb
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initButtons(){

        inputName.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                validate()
            }
            false
        }

        show.setOnClickListener {
            solution.text = "${selectedItem!!.ps} || ${selectedItem!!.pp}"
        }

    }

    private fun validate(){
        val shake = AnimationUtils.loadAnimation(this, R.anim.vabrate)
        val items = inputName.text.toString().toLowerCase(Locale.ROOT).split(" ")
        if(items.count() == 2 || items.count() == 1){
            var ps = ""
            var pp = ""
            if(items.count() == 1){
                ps = items[0]
                pp = ps
            }else{
                ps = items[0]
                pp = items[1]
            }
            println("=> |$ps| |$pp| ;; |${selectedItem!!.ps}| |${selectedItem!!.pp}|")
            if(ps == selectedItem!!.ps && pp == selectedItem!!.pp){
                selectedItem = IrregularServices.selectOne()
                if(selectedItem == null){
                    finish()
                }else{
                    verbName.text = selectedItem!!.theVerb
                    inputName.setText("")
                    solution.text = ""
                }
            }else{
                Toast.makeText(this, "Not True", Toast.LENGTH_SHORT).show()
                inputName.startAnimation(shake)
            }
        }else{
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show()
        }
        thread {
            Thread.sleep(300)
            showKey()
        }
    }

    private fun showKey(){
        val mainHandler =  Handler(this.mainLooper);

        val myRunnable =  Runnable {
            showKeyboardTo()
        }
        mainHandler.post(myRunnable);
    }

    private fun showKeyboardTo(){
        val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm!!.showSoftInput(inputName, InputMethodManager.SHOW_IMPLICIT)
    }

}