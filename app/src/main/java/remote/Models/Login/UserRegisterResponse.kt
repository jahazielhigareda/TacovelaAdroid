package remote.Models.Login

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class UserRegisterResponse {
    @SerializedName("success")
    @Expose
    var success: Boolean? = null
    @SerializedName("errors")
    @Expose
    var errors: List<String>? = null
    @SerializedName("data")
    @Expose
    var data: UserRegister? = null
}