package com.zm.data_.mappers

import com.zm.data_.model.api.TransactionDataModel
import com.zm.data_.model.api.login.LoginResponse
import com.zm.data_.model.api.profile.ProfileResponse
import com.zm.data_.model.api.refresh.RefreshResponse
import com.zm.domain.model.Profile
import com.zm.domain.model.SessionData
import com.zm.domain.model.Transaction
import com.zm.domain.model.X


fun LoginResponse.toSessionData(): SessionData {
    return SessionData(this.expiration.toLong(), token)
}

fun ProfileResponse.toProfile(): Profile {
    val account = this.info.account
    val user = this.info.profiles.first()
    return Profile(account.email, account.accountId, user.firstName, user.lastName, user.avatarUrl)
}

fun RefreshResponse.toSessionData(): SessionData {
    return SessionData(this.expiration.toLong(), token)
}

fun TransactionDataModel.toTransaction(): Transaction {
    val x = X(this.x.size, this.x.time)
    return Transaction(x)
}
