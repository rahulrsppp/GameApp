package com.rahul.gameapp.ui

import android.app.AlertDialog
import android.app.Application
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.rahul.gameapp.MyApplication
import com.rahul.gameapp.R
import com.rahul.gameapp.databinding.ActivityMainBinding
import com.rahul.gameapp.utils.Event
import com.rahul.gameapp.utils.ViewModelFactory

class MainActivity : AppCompatActivity(){

    lateinit var activityMainBinding :  ActivityMainBinding

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this, ViewModelFactory(MyApplication.applicationContext() as Application)).get(MainViewModel::class.java)

        activityMainBinding.mainViewModel = viewModel

        observerData()
    }

    private fun observerData() {

        viewModel.gameOverEvent.observe(this, Observer {
            val i  = it.getDataIfNotHandled()
            val message = resources.getString(R.string.your_score_is)+" " + i
            val builder = AlertDialog.Builder(this)
            builder.setTitle(R.string.dialogTitle)
            builder.setMessage(message)
            builder.setIcon(android.R.drawable.ic_dialog_alert)

            //performing positive action
            builder.setPositiveButton("RESTART") { dialogInterface, which ->
                dialogInterface.dismiss()
                viewModel.retry()
            }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
        })

        viewModel.colorUpdate.observe(this, Observer {

            val i  = it.getDataIfNotHandled()
            when(i){
                0 ->{  //red
                    activityMainBinding.llRed.setBackgroundColor(ResourcesCompat.getColor(resources, R.color.gray,null))
                    activityMainBinding.llBlue.setBackgroundColor(ResourcesCompat.getColor(resources,R.color.colorPrimaryDark,null))
                    activityMainBinding.llYellow.setBackgroundColor(ResourcesCompat.getColor(resources,R.color.yellow,null))
                    activityMainBinding.llGreen.setBackgroundColor(ResourcesCompat.getColor(resources,R.color.green, null))
                }
                1 -> {  // blue
                    activityMainBinding.llRed.setBackgroundColor(ResourcesCompat.getColor(resources, R.color.red,null))
                    activityMainBinding.llBlue.setBackgroundColor(ResourcesCompat.getColor(resources, R.color.gray,null))
                    activityMainBinding.llYellow.setBackgroundColor(ResourcesCompat.getColor(resources,R.color.yellow,null))
                    activityMainBinding.llGreen.setBackgroundColor(ResourcesCompat.getColor(resources,R.color.green, null))
                }
                2 -> {  // yellow
                    activityMainBinding.llRed.setBackgroundColor(ResourcesCompat.getColor(resources, R.color.red,null))
                    activityMainBinding.llBlue.setBackgroundColor(ResourcesCompat.getColor(resources,R.color.colorPrimaryDark,null))
                    activityMainBinding.llYellow.setBackgroundColor(ResourcesCompat.getColor(resources,R.color.gray,null))
                    activityMainBinding.llGreen.setBackgroundColor(ResourcesCompat.getColor(resources,R.color.green, null))
                }
                3 -> {  // green
                    activityMainBinding.llRed.setBackgroundColor(ResourcesCompat.getColor(resources, R.color.red,null))
                    activityMainBinding.llBlue.setBackgroundColor(ResourcesCompat.getColor(resources,R.color.colorPrimaryDark,null))
                    activityMainBinding.llYellow.setBackgroundColor(ResourcesCompat.getColor(resources,R.color.yellow,null))
                    activityMainBinding.llGreen.setBackgroundColor(ResourcesCompat.getColor(resources,R.color.gray, null))
                }
                else -> {
                    activityMainBinding.llRed.setBackgroundColor( ResourcesCompat.getColor(resources, R.color.red,null))
                    activityMainBinding.llBlue.setBackgroundColor(ResourcesCompat.getColor(resources,R.color.colorPrimaryDark,null))
                    activityMainBinding.llYellow.setBackgroundColor(ResourcesCompat.getColor(resources,R.color.yellow,null))
                    activityMainBinding.llGreen.setBackgroundColor(ResourcesCompat.getColor(resources,R.color.green, null))
                }
            }

        })
    }


}
