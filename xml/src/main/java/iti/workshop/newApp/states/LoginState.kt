package iti.workshop.newApp.states

sealed class LoginState{
    class Success(val data: Boolean):LoginState()
    class Failure(val error:Throwable):LoginState()
    object Loading : LoginState()
}
