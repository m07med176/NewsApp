package iti.workshop.newApp.states

sealed class RegisterState{
    class Success(data:Boolean):RegisterState()
    class Failure(val error:Throwable):RegisterState()
    object Loading : RegisterState()
}
