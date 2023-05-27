
class Repository(
    val shared: SharedManager,
    val local: ILocalDataSource,
    val remote: IRemoteDataSource
) : IRepository {
    override fun login(username: String, password: String) {
        TODO("Not yet implemented")
    }

    override fun signUp() {
        TODO("Not yet implemented")
    }

    override fun logout() {
        TODO("Not yet implemented")
    }


    fun savedLoginData(user:User){
        shared.saveUser(user)
    }
}