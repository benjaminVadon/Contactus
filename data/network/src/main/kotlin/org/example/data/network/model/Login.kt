package org.example.data.network.model

data class Login(
    val md5: String,
    val password: String,
    val salt: String,
    val sha1: String,
    val sha256: String,
    val username: String,
    val uuid: String
) {
    companion object {
        internal val Sample = Login(
            md5 = "LoginMd5",
            password = "LoginPassword",
            salt = "LoginSalt",
            sha1 = "LoginSha1",
            sha256 = "LoginSha256",
            username = "LoginUsername",
            uuid = "LoginUuid",
        )
    }
}