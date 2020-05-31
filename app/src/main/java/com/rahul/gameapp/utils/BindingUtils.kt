package com.rahul.gameapp.utils

import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("addText")
fun TextView.addText(data : String){
    text = data
}