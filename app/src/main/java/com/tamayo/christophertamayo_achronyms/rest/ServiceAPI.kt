package com.tamayo.christophertamayo_achronyms.rest

import com.tamayo.christophertamayo_achronyms.data.model.AcronymsItem
import com.tamayo.christophertamayo_achronyms.data.model.Lfs
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceAPI {

    @GET(ACRONYMS)
    suspend fun getAcronyms(
        @Query("sf") sf: String?
    ):Response<List<AcronymsItem>>


    /*
    http://www.nactem.ac.uk/software/acromine/dictionary.py?sf=HMM
     */

    companion object{
        const val BASE_PATH = "http://www.nactem.ac.uk/software/acromine/"
        private const val ACRONYMS = "dictionary.py"
    }

}

