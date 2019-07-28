package com.nwt.first_kotlin_test

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.nwt.first_kotlin_test.Adapters.PopularMoviesAdapter
import com.nwt.first_kotlin_test.Delegates.ClickMovieDetail
import com.nwt.first_kotlin_test.data.viewmodels.AppViewModel
import com.nwt.first_kotlin_test.vos.MovieVO
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity(),ClickMovieDetail {

    override fun onTap(movieVO: MovieVO?) {
            //Timber.d("id : %d",movieVO?.movieId)
        val intent  = Intent(this,MovieDetailActivity::class.java)
        intent.putExtra("movie_id",movieVO?.movieId)
        startActivity(intent)
    }

//    fun showLoading(){
//        loadingDialog = ProgressDialog.show(this@MainActivity,"Loading","loading data")
//        loadingDialog.show()
//    }
//
//    fun hideLoading(){
//        loadingDialog.hide()
//    }

 lateinit var appViewModel : AppViewModel

 //lateinit var loadingDialog : ProgressDialog

 lateinit var popularMoviesAdapter: PopularMoviesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       appViewModel = ViewModelProviders.of(this).get(AppViewModel::class.java)

     popularMoviesAdapter = PopularMoviesAdapter(this,this)

     appViewModel.
         getPopular().
         subscribeOn(Schedulers.io())?.
         observeOn(AndroidSchedulers.mainThread())?.
         subscribe({ it-> popularMoviesAdapter.setNewData(it.results)
                    Timber.d("list size : %d",it.results.size)},
             { t ->Timber.d("error : %s",t.localizedMessage) })

     popular_movies_rv.setHasFixedSize(true)

     popular_movies_rv.adapter = popularMoviesAdapter

    }

}
