package com.hao.easy.wan.ui.adapter

import android.widget.ImageView
import com.hao.easy.base.adapter.BaseNormalAdapter
import com.hao.easy.base.adapter.ViewHolder
import com.hao.easy.base.extensions.load
import com.hao.easy.wan.R
import com.hao.easy.wan.model.Ad
import javax.inject.Inject

/**
 * @author Yang Shihao
 * @date 2018/12/3
 */
class BannerAdapter @Inject constructor() : BaseNormalAdapter<Ad>(R.layout.wan_item_banner) {

    override fun bindViewHolder(holder: ViewHolder, item: Ad, position: Int) {
        (holder.itemView as ImageView).load(item.imagePath)
    }
}