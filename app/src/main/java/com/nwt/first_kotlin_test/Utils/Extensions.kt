package com.nwt.first_kotlin_test.Utils

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun AppCompatActivity.toast(message : String) {
    Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.toast(message : String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}