package com.track4good.hackathon.data.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.track4good.hackathon.data.AuthRemoteDataSource
import com.track4good.hackathon.domain.entity.ResultData
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowViaChannel
import javax.inject.Inject

class AuthRemoteDataSourceImpl @Inject constructor(private val auth: FirebaseAuth) :
    AuthRemoteDataSource {
    var firebaseStoreDatabase: FirebaseFirestore = Firebase.firestore

    override suspend fun register(
        userMail: String,
        userPassword: String
    ): Flow<ResultData<Unit>> {
        return flowViaChannel { flowChannel ->
            auth.signInWithEmailAndPassword(userMail, userPassword)
                .addOnSuccessListener {
                    flowChannel.sendBlocking(ResultData.Success())
                }

                .addOnFailureListener {
                    flowChannel.sendBlocking(ResultData.Failed(it.message))
                }

                .addOnCanceledListener {
                    flowChannel.sendBlocking(ResultData.Failed())
                }
        }
    }


}