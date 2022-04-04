package br.com.alexf.thirtyDaysOfCompose.model

import br.com.alexf.thirtyDaysOfCompose.R
import java.util.*

private const val DEFAULT_AVATAR = R.drawable.alex_avatar

class Author(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val avatar: Int = DEFAULT_AVATAR
)
