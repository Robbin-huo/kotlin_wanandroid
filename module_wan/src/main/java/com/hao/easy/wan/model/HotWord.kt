package com.hao.easy.wan.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Yang Shihao
 * @Date 2020/7/20
 */
@Entity
data class HotWord(
    @PrimaryKey(autoGenerate = true) var id: Int?,
    val name: String
)