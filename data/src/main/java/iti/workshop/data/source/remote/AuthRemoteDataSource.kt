package iti.workshop.data.source.remote


import iti.workshop.data.source.remote.models.login.Login
import iti.workshop.data.source.remote.models.login.LoginBody
import iti.workshop.data.source.remote.models.regieter.RegisterBody
import iti.workshop.data.source.remote.models.regieter.RegisterResponse
import iti.workshop.data.source.remote.retrofit.AuthApi

class AuthRemoteDataSource(private val api: AuthApi) : IAuthRemoteDataSource {
    override suspend fun register(body: RegisterBody): RegisterResponse {
        return api.register(body)
    }

    override suspend fun loginUser(body: LoginBody): Login {
        return api.loginUser(body)
    }
}