package kg.izapp.youtubeparser2.utills

import android.content.Context
import android.view.View
import android.widget.Toast

fun Context.showToast(text: String){
    Toast.makeText(this,text,Toast.LENGTH_LONG).show()
}

fun View.visible(){
    this.visibility = View.VISIBLE
}




