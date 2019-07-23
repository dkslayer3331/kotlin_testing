package com.nwt.first_kotlin_test.vos

import com.google.gson.annotations.SerializedName

data class CrewVO(
    @SerializedName("name") val name : String = "",
    @SerializedName("job") val job : String = "",
    @SerializedName("department") val department : String = "",
    @SerializedName("profile_path") val profile_path : String = ""
)