package kg.izapp.youtubeparser2.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders

abstract class BaseActivity<T:ViewModel>(@LayoutRes val layoutId: Int,val vmClass: Class<T>): AppCompatActivity() {

    lateinit var viewModel: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        viewModel = ViewModelProviders.of(this).get(vmClass)
        showToast()
    }

    private fun showToast(){
        Toast.makeText(this,"TEST",Toast.LENGTH_LONG).show()
    }
}