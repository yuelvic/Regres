package io.github.regres.data.remote

import io.github.regres.data.entities.Resource
import io.github.regres.data.entities.Result
import io.github.regres.data.entities.User
import io.reactivex.Observable
import retrofit2.http.*

interface ReqresApi {

    @GET("unknown")
    fun getResources(): Observable<Result<Resource>>

    @GET("users")
    fun getUsers(@Query("page") page: Int): Observable<Result<User>>

    @POST("users")
    fun addUser(@Body user: User)

    @DELETE("users/{id}")
    fun deleteUser(@Path("id") id: Int)


}