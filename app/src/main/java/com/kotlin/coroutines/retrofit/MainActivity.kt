package com.kotlin.coroutines.retrofit

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.println
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.android.Main
import kotlinx.coroutines.launch
import retrofit2.HttpException


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_main)

        val service = RetrofitFactory.makeRetrofitService()
        GlobalScope.launch(Dispatchers.Main) {
            val request = service.getUserDetails()
            try {
                val response = request.await()
                // Do something with the response.body()
                println(Log.DEBUG,"==========>>>>>>> "+response.title, response.userId)
                response.title.toast(this@MainActivity)
            } catch (e: HttpException) {
                "ERROR ====> " + e.code().toast(this@MainActivity)
            } catch (e: Throwable) {
                "Ooops: Something else went wrong".toast(this@MainActivity)
            }
        }
    }

    fun Any.toast(context: Context, duration: Int = Toast.LENGTH_SHORT) {
        return Toast.makeText(context, this.toString(), duration).show()
    }

}
