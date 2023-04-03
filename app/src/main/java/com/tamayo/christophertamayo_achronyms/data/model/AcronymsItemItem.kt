package com.tamayo.christophertamayo_achronyms.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AcronymsItemItem(
    @Json(name = "lfs")
    val lfs: List<Lf?>? = null,
    @Json(name = "sf")
    val sf: String? = null
)