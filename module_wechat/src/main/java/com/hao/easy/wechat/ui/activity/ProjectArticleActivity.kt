package com.hao.easy.wechat.ui.activity

import android.content.Context
import android.view.View
import com.hao.easy.base.ui.BaseListActivity
import com.hao.easy.base.ui.WebActivity
import com.hao.easy.base.ui.WebWithImageActivity
import com.hao.easy.wechat.R
import com.hao.easy.wechat.di.component
import com.hao.easy.wechat.model.Article
import com.hao.easy.wechat.model.ProjectType
import com.hao.easy.wechat.ui.adapter.ProjectArticleAdapter
import com.hao.easy.wechat.viewmodel.ProjectArticleViewModel
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class ProjectArticleActivity : BaseListActivity<Article, ProjectArticleViewModel>() {

    @Inject
    lateinit var adapter: ProjectArticleAdapter

    companion object {
        private const val TYPE = "TYPE"
        fun start(context: Context, projectType: ProjectType) {
            context.startActivity<ProjectArticleActivity>(Pair(TYPE, projectType))
        }
    }

    override fun initInject() {
        component().inject(this)
    }

    override fun initData() {
        var type = intent.getParcelableExtra<ProjectType>(TYPE)
        type?.apply {
            title = name?.replace("amp;", "")
            viewModel.typeId = id
        }

        super.initData()
    }

    override fun adapter() = adapter

    override fun itemClicked(view: View, item: Article, position: Int) {
        when (view.id) {
            R.id.ivLink, R.id.tvLink -> {
                WebActivity.start(this, item.title, item.projectLink)
            }
            R.id.ivFav -> {
                if (item.collect) {
                    viewModel.cancelCollect(item, position)
                } else {
                    viewModel.collect(item, position)
                }
            }
            else -> WebWithImageActivity.start(this, item.title, item.link, item.envelopePic)
        }
    }
}