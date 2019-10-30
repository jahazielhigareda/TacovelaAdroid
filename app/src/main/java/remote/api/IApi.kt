package remote.api

import remote.Models.Login.Credentials
import remote.Models.Login.ResponseBasic
import remote.Models.Login.UserRegister
import remote.Models.Login.UserRegisterResponse
import retrofit2.Call
import retrofit2.http.*

interface IApi {
    @POST("user/login")
    fun LoginUser(@Body credentials: Credentials): Call<ResponseBasic>

    @POST("user/post")
    fun RegisterUser(@Body register: UserRegister): Call<UserRegisterResponse>
}