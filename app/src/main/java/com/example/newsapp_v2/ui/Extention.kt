package com.example.newsapp_v2.ui

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import androidx.fragment.app.Fragment

fun Fragment.showDialog(
        message:String?=null,
        posActionName:String?=null,
        posAction:DialogInterface.OnClickListener?=null ,
        negActionName:String?=null ,
        negAction:DialogInterface.OnClickListener?=null,
    ):AlertDialog{
        val dialogBuilder= AlertDialog.Builder(context)
        dialogBuilder.setMessage(message)
    if(posActionName != null){
        dialogBuilder.setPositiveButton(posActionName , posAction)
    }
    if(negActionName!=null )
        dialogBuilder.setNegativeButton(negActionName , negAction)
    return dialogBuilder.show()
}