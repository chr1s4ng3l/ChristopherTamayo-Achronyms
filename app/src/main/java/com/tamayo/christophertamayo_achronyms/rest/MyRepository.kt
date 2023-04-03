package com.tamayo.christophertamayo_achronyms.rest

import com.tamayo.christophertamayo_achronyms.data.model.Lf
import com.tamayo.christophertamayo_achronyms.utils.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface MyRepository {

    suspend fun getAllAcronyms(tag: String): Flow<UIState<List<Lf>>>

}

class MyRepositoryImpl @Inject constructor(private val serviceAPI: ServiceAPI) : MyRepository {

    override suspend fun getAllAcronyms(tag: String): Flow<UIState<List<Lf>>> = flow {
        emit(UIState.LOADING)

        try {

            val response = serviceAPI.getAcronyms(tag)

            if (response.isSuccessful) {
                response.body()?.let {
                    emit(UIState.SUCCESS(it))
                } ?: throw Exception("Response was null")
            } else {
                throw Exception(response.errorBody().toString())
            }

        } catch (e: Exception) {
            emit(UIState.ERROR(e))
        }

    }

}