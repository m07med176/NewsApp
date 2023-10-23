package iti.workshop.newApp.utils

object ValidationReg {
    fun validateData(password:String,email: String,confPassword:String):Boolean{
        if (validateEmail(email) && validatePassword(password,confPassword)){
            return true
        }else{
            return false
        }
    }
    fun validateEmail(email:String):Boolean{
        if (email.contains("@")&&email.endsWith(".com")){
            return true
        }else{
            return false
        }
    }
    fun validatePassword(password: String,confPassword: String):Boolean{
        if (password.equals(confPassword)&&password.length>=8){
            return true
        }else{
            return false
        }
    }
}