package com.tecknoworks.poq.ui.main

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tecknoworks.poq.MainActivity
import com.tecknoworks.poq.R
import com.tecknoworks.poq.data.RepositoriesViewModel
import kotlinx.android.synthetic.main.main_fragment.*
import javax.inject.Inject

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    @Inject
    lateinit var viewModel: RepositoriesViewModel

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var repositoriesAdapter: RepositoriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupDaggerComponent()
        setupRepositoriesView()

        retryButton.setOnClickListener {
            viewModel.getRepositoryList()
            errorView.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
        }
        reloadButton.setOnClickListener {
            viewModel.getRepositoryList()
            errorView.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
        }

        viewModel.getRepositoryList()
        viewModel.repositoriesLiveData.observe(this.viewLifecycleOwner, Observer {
            progressBar.visibility = View.INVISIBLE
            if (it == null) {
                errorView.visibility = View.VISIBLE
            } else {
                errorView.visibility = View.GONE
                repositoriesAdapter.setRepositories(it)
                repositoriesAdapter.notifyDataSetChanged()
            }
        })
    }

    private fun setupDaggerComponent() {
        (activity as MainActivity).repositoriesComponent.inject(this)
    }

    private fun setupRepositoriesView() {
        linearLayoutManager = LinearLayoutManager(context)
        repositoriesView.layoutManager = linearLayoutManager
        repositoriesAdapter = RepositoriesAdapter()
        repositoriesView.adapter = repositoriesAdapter
    }

}
