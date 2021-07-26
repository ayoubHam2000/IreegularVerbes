package com.example.ireegularverbes.Objects

import com.example.ireegularverbes.Class.Irregular
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

object IrregularServices{

    private val irregular = ArrayList<Irregular>()
    private val task = ArrayList<Irregular>()
    private var isLoaded = false

    fun selectOne() : Irregular?{
        return if(task.count() == 0){
            null
        }else{
            val rand = Random.nextInt(0, task.count())
            val item = task[rand]
            task.removeAt(rand)
            item
        }
    }

    fun load(data : String){
        if(!isLoaded){
            val info = data.toLowerCase(Locale.ROOT).split("\n")
            for (i in info){
                val item = i.split(" ")
                irregular.add(Irregular(item[0].trim(), item[1].trim(), item[2].trim()))
            }
            isLoaded = true
        }
    }

    fun selectTask(a : Int, b : Int){
        task.clear()
        for(i in max(0, a)..min(b, irregular.count() - 1)){
            task.add((irregular[i]))
        }
    }

    private fun min(a : Int, b : Int) : Int{
        return if (a > b) b else a
    }

    private fun max(a : Int, b : Int) : Int{
        return if (a > b) a else b
    }

}