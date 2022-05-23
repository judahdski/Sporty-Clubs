package com.d3if0002.sportyclubs.ui.clubdetail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.d3if0002.sportyclubs.R
import com.d3if0002.sportyclubs.databinding.FragmentDetailClubBinding
import com.d3if0002.sportyclubs.db.ClubDb
import com.d3if0002.sportyclubs.db.ClubEntity

class DetailClubFragment : Fragment() {

    companion object {
        const val URL = "https://www.google.com/search?q="
    }

    private val viewModel: DetailClubViewModel by lazy {
        val db = ClubDb.getInstance(requireActivity().applicationContext)
        val factory = DetailClubViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[DetailClubViewModel::class.java]
    }

    private lateinit var binding: FragmentDetailClubBinding
    private val args: DetailClubFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailClubBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val clubId = args.club
        viewModel.getClubFromDB(clubId).observe(viewLifecycleOwner) {
            bindClubData(it, view)
        }
    }

    private fun bindClubData(club: ClubEntity, view: View) {
        val linkGoogle: String = URL + club.fullName

        with(binding) {
            julukan.text = club.nickname
            namaLengkap.text = club.fullName
            deskripsi.text = club.description
            goBrowsingButton.text = getString(R.string.button_text, club.fullName)
            goBrowsingButton.setOnClickListener {
                val queryUrl: Uri = (Uri.parse(linkGoogle))
                val intent = Intent(Intent.ACTION_VIEW, queryUrl)
                requireContext().startActivities(arrayOf(intent))
            }

//            using glide for loading image from internet
            val url: Uri = Uri.parse(club.imageLink)
            Glide.with(view)
                .load(url)
                .into(imageView)
        }
    }
}