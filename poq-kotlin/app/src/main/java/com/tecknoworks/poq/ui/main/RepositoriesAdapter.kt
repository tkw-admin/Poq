package com.tecknoworks.poq.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tecknoworks.poq.R
import com.tecknoworks.poq.api.model.Repository

/**
 * Created by Mihai Ionescu on 2020-01-31.
 */
class RepositoriesAdapter : RecyclerView.Adapter<RepositoryViewHolder>() {

    private var repositories = ArrayList<Repository>()

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        val repository = repositories[position]
        holder.populate(repository)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.repository_item_row, parent, false)
        return RepositoryViewHolder(view)
    }

    override fun getItemCount(): Int = repositories.size

    fun setRepositories(repositories: List<Repository>) {
        this.repositories.clear()
        this.repositories.addAll(repositories)
    }

}