package com.nwt.first_kotlin_test.data.Repository.remote

import com.nwt.first_kotlin_test.vos.CastVO
import com.nwt.first_kotlin_test.vos.MovieListVO
import com.nwt.first_kotlin_test.vos.MovieVO
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Path


interface MoviesAPI {

    @GET("movie/upcoming")
    fun getUpcomingMovies(@Query("api_key") apiKey : String) : Observable<MovieListVO>

    @GET("movie/{id}")
    fun getMovieDetail(@Path("id") movie_id: Long, @Query("api_key") apiKey: String, @Query("append_to_response") word: String): Observable<MovieVO>

    @GET("person/{id}")
    fun getCastDetail(@Path("id") cast_id: Long, @Query("api_key") apiKey: String, @Query("append_to_response") movie_credits: String): Observable<CastVO>

    @GET("search/movie")
    fun getSearchedMovies(@Query("api_key") apiKey: String, @Query("query") query: String): Observable<MovieListVO>

    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("api_key") apiKey: String): Observable<MovieListVO>

    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String): Observable<MovieListVO>

}