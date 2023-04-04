package com.tamayo.christophertamayo_achronyms.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AcronymsItem(
    @Json(name = "lfs")
    val lfs: List<Lfs>? = null,
    @Json(name = "sf")
    val sf: String? = null
)