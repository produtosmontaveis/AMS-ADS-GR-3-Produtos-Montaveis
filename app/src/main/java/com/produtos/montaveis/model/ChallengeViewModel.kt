package com.produtos.montaveis.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.produtos.montaveis.data.ChallengeData
import com.produtos.montaveis.model.Challenge
import com.produtos.montaveis.network.ChallengeApi
import kotlinx.coroutines.launch

class ChallengeViewModel : ViewModel() {

    private val _challenges = MutableLiveData<List<Challenge>>()
    val challenges: LiveData<List<Challenge>> = _challenges

    private val _challenge = MutableLiveData<Challenge>()
    val challenge: LiveData<Challenge> = _challenge

    init {
        getChallengeData()
    }

    private fun getChallengeData() {
        viewModelScope.launch {
            try {
                // Saving http requests from the server with some pre-populated data
                // _challenges.value = ChallengeApi.retrofitService.getChallenges(1)
                _challenges.value = ChallengeData.challenges
            } catch (e: Exception) {
                println(e.message)
                _challenges.value = listOf()
            }
        }
    }

    fun onChallengeClicked(challenge: Challenge) {
        _challenge.value = challenge
    }
}