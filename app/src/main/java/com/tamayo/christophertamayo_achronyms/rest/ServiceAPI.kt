package com.tamayo.christophertamayo_achronyms.rest

import com.tamayo.christophertamayo_achronyms.data.model.Lf
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceAPI {

    @GET(ACRONYMS)
    suspend fun getAcronyms(
        @Query("sf") sf: String? = null
    ):Response<List<Lf>>


    /*
    http://www.nactem.ac.uk/software/acromine/dictionary.py?sf=HMM
     */

    companion object{
        const val BASE_PATH = "http://www.nactem.ac.uk/software/acromine/"
        private const val ACRONYMS = "dictionary.py"
    }

}

