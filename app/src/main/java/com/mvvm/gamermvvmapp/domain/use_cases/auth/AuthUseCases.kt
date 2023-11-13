package com.mvvm.gamermvvmapp.domain.use_cases.auth
//ESTA ES UNA CLASE LA CUAL LLAMARA A LOS CASOS DE USO
data class AuthUseCases (
    val getCurrentUser: GetCurrentUser,
    val login: Login,
    val logOut: LogOut
)