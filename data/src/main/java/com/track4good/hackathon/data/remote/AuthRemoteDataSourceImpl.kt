package com.track4good.hackathon.data.remote

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.track4good.hackathon.data.AuthRemoteDataSource
import com.track4good.hackathon.domain.entity.ResultData
import com.track4good.hackathon.domain.entity.UserType
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
            auth.createUserWithEmailAndPassword(userMail, userPassword)
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

    override suspend fun login(userMail: String, userPassword: String): Flow<ResultData<Unit>> {
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

    override suspend fun initializeUserToFirestore(
        fullName: String,
        birthDate: String,
        hesCode: String,
        userType: String,
        userMail: String,
        userPassword: String
    ): Flow<ResultData<Unit>> {
        val db = Firebase.firestore
        val docData = hashMapOf(
            "fullName" to fullName,
            "birthDate" to birthDate,
            "hesCode" to hesCode,
            "userType" to userType,
            "userMail" to userMail,
            "userPassword" to userPassword
        )
        return flowViaChannel { flowChannel ->
            db.collection("user").document().set(docData)
                .addOnSuccessListener { Log.d("AuthRemoteDataSourceImp", "DocumentSnapshot successfully written!") }
                .addOnFailureListener { e -> Log.w("AuthRemoteDataSourceImp", "Error writing document", e) }
        }
    }
}