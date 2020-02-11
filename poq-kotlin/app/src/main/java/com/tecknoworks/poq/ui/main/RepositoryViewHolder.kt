package com.tecknoworks.poq.ui.main

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.tecknoworks.poq.api.model.RepositoryDTO
import com.tecknoworks.poq.data.model.PoqRepository
import kotlinx.android.synthetic.main.repository_item_row.view.*

/**
 * Created by Mihai Ionescu on 2020-01-31.
 */
class RepositoryViewHolder constructor(v: View) : RecyclerView.ViewHolder(v) {

    private var view: View = v
    private var repository: PoqRepository? = null

    fun populate(repository: PoqRepository) {
        this.repository = repository
        view.repositoryName.text = repository.name
        view.repositoryDescription.text = repository.description
    }

}