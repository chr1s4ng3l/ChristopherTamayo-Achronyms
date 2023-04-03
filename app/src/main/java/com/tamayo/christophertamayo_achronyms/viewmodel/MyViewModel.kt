package com.tamayo.christophertamayo_achronyms.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tamayo.christophertamayo_achronyms.data.model.Lf
import com.tamayo.christophertamayo_achronyms.rest.MyRepositoryImpl
import com.tamayo.christophertamayo_achronyms.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val myRepository: MyRepositoryImpl,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _acronym: MutableLiveData<UIState<List<Lf>>> = MutableLiveData(UIState.LOADING)
    val acronym: LiveData<UIState<List<Lf>>> get() = _acronym


    fun getAcronym(tag: String? = null){
        tag?.let {
            viewModelScope.launch(ioDispatcher) {
                myRepository.getAllAcronyms(tag).collect{
                    _acronym.postValue(it)
                }
            }
        }
    }

}